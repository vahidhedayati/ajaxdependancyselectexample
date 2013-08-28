package ajaxdependancyselectexample



import org.junit.*
import grails.test.mixin.*

@TestFor(MyContinentController)
@Mock(MyContinent)
class MyContinentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/myContinent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.myContinentInstanceList.size() == 0
        assert model.myContinentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.myContinentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.myContinentInstance != null
        assert view == '/myContinent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/myContinent/show/1'
        assert controller.flash.message != null
        assert MyContinent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/myContinent/list'

        populateValidParams(params)
        def myContinent = new MyContinent(params)

        assert myContinent.save() != null

        params.id = myContinent.id

        def model = controller.show()

        assert model.myContinentInstance == myContinent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/myContinent/list'

        populateValidParams(params)
        def myContinent = new MyContinent(params)

        assert myContinent.save() != null

        params.id = myContinent.id

        def model = controller.edit()

        assert model.myContinentInstance == myContinent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/myContinent/list'

        response.reset()

        populateValidParams(params)
        def myContinent = new MyContinent(params)

        assert myContinent.save() != null

        // test invalid parameters in update
        params.id = myContinent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/myContinent/edit"
        assert model.myContinentInstance != null

        myContinent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/myContinent/show/$myContinent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        myContinent.clearErrors()

        populateValidParams(params)
        params.id = myContinent.id
        params.version = -1
        controller.update()

        assert view == "/myContinent/edit"
        assert model.myContinentInstance != null
        assert model.myContinentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/myContinent/list'

        response.reset()

        populateValidParams(params)
        def myContinent = new MyContinent(params)

        assert myContinent.save() != null
        assert MyContinent.count() == 1

        params.id = myContinent.id

        controller.delete()

        assert MyContinent.count() == 0
        assert MyContinent.get(myContinent.id) == null
        assert response.redirectedUrl == '/myContinent/list'
    }
}
