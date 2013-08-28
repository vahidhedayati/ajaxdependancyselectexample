package ajaxdependancyselectexample



import org.junit.*
import grails.test.mixin.*

@TestFor(MyCityController)
@Mock(MyCity)
class MyCityControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/myCity/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.myCityInstanceList.size() == 0
        assert model.myCityInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.myCityInstance != null
    }

    void testSave() {
        controller.save()

        assert model.myCityInstance != null
        assert view == '/myCity/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/myCity/show/1'
        assert controller.flash.message != null
        assert MyCity.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/myCity/list'

        populateValidParams(params)
        def myCity = new MyCity(params)

        assert myCity.save() != null

        params.id = myCity.id

        def model = controller.show()

        assert model.myCityInstance == myCity
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/myCity/list'

        populateValidParams(params)
        def myCity = new MyCity(params)

        assert myCity.save() != null

        params.id = myCity.id

        def model = controller.edit()

        assert model.myCityInstance == myCity
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/myCity/list'

        response.reset()

        populateValidParams(params)
        def myCity = new MyCity(params)

        assert myCity.save() != null

        // test invalid parameters in update
        params.id = myCity.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/myCity/edit"
        assert model.myCityInstance != null

        myCity.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/myCity/show/$myCity.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        myCity.clearErrors()

        populateValidParams(params)
        params.id = myCity.id
        params.version = -1
        controller.update()

        assert view == "/myCity/edit"
        assert model.myCityInstance != null
        assert model.myCityInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/myCity/list'

        response.reset()

        populateValidParams(params)
        def myCity = new MyCity(params)

        assert myCity.save() != null
        assert MyCity.count() == 1

        params.id = myCity.id

        controller.delete()

        assert MyCity.count() == 0
        assert MyCity.get(myCity.id) == null
        assert response.redirectedUrl == '/myCity/list'
    }
}
