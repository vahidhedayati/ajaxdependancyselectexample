package ajaxdependancyselectexample



import org.junit.*
import grails.test.mixin.*

@TestFor(MyBoroughController)
@Mock(MyBorough)
class MyBoroughControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/myBorough/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.myBoroughInstanceList.size() == 0
        assert model.myBoroughInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.myBoroughInstance != null
    }

    void testSave() {
        controller.save()

        assert model.myBoroughInstance != null
        assert view == '/myBorough/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/myBorough/show/1'
        assert controller.flash.message != null
        assert MyBorough.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/myBorough/list'

        populateValidParams(params)
        def myBorough = new MyBorough(params)

        assert myBorough.save() != null

        params.id = myBorough.id

        def model = controller.show()

        assert model.myBoroughInstance == myBorough
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/myBorough/list'

        populateValidParams(params)
        def myBorough = new MyBorough(params)

        assert myBorough.save() != null

        params.id = myBorough.id

        def model = controller.edit()

        assert model.myBoroughInstance == myBorough
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/myBorough/list'

        response.reset()

        populateValidParams(params)
        def myBorough = new MyBorough(params)

        assert myBorough.save() != null

        // test invalid parameters in update
        params.id = myBorough.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/myBorough/edit"
        assert model.myBoroughInstance != null

        myBorough.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/myBorough/show/$myBorough.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        myBorough.clearErrors()

        populateValidParams(params)
        params.id = myBorough.id
        params.version = -1
        controller.update()

        assert view == "/myBorough/edit"
        assert model.myBoroughInstance != null
        assert model.myBoroughInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/myBorough/list'

        response.reset()

        populateValidParams(params)
        def myBorough = new MyBorough(params)

        assert myBorough.save() != null
        assert MyBorough.count() == 1

        params.id = myBorough.id

        controller.delete()

        assert MyBorough.count() == 0
        assert MyBorough.get(myBorough.id) == null
        assert response.redirectedUrl == '/myBorough/list'
    }
}
