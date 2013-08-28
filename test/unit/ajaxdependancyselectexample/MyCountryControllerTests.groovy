package ajaxdependancyselectexample



import org.junit.*
import grails.test.mixin.*

@TestFor(MyCountryController)
@Mock(MyCountry)
class MyCountryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/myCountry/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.myCountryInstanceList.size() == 0
        assert model.myCountryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.myCountryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.myCountryInstance != null
        assert view == '/myCountry/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/myCountry/show/1'
        assert controller.flash.message != null
        assert MyCountry.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/myCountry/list'

        populateValidParams(params)
        def myCountry = new MyCountry(params)

        assert myCountry.save() != null

        params.id = myCountry.id

        def model = controller.show()

        assert model.myCountryInstance == myCountry
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/myCountry/list'

        populateValidParams(params)
        def myCountry = new MyCountry(params)

        assert myCountry.save() != null

        params.id = myCountry.id

        def model = controller.edit()

        assert model.myCountryInstance == myCountry
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/myCountry/list'

        response.reset()

        populateValidParams(params)
        def myCountry = new MyCountry(params)

        assert myCountry.save() != null

        // test invalid parameters in update
        params.id = myCountry.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/myCountry/edit"
        assert model.myCountryInstance != null

        myCountry.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/myCountry/show/$myCountry.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        myCountry.clearErrors()

        populateValidParams(params)
        params.id = myCountry.id
        params.version = -1
        controller.update()

        assert view == "/myCountry/edit"
        assert model.myCountryInstance != null
        assert model.myCountryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/myCountry/list'

        response.reset()

        populateValidParams(params)
        def myCountry = new MyCountry(params)

        assert myCountry.save() != null
        assert MyCountry.count() == 1

        params.id = myCountry.id

        controller.delete()

        assert MyCountry.count() == 0
        assert MyCountry.get(myCountry.id) == null
        assert response.redirectedUrl == '/myCountry/list'
    }
}
