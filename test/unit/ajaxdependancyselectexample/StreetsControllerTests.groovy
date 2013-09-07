package ajaxdependancyselectexample



import org.junit.*
import grails.test.mixin.*

@TestFor(StreetsController)
@Mock(Streets)
class StreetsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/streets/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.streetsInstanceList.size() == 0
        assert model.streetsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.streetsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.streetsInstance != null
        assert view == '/streets/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/streets/show/1'
        assert controller.flash.message != null
        assert Streets.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/streets/list'

        populateValidParams(params)
        def streets = new Streets(params)

        assert streets.save() != null

        params.id = streets.id

        def model = controller.show()

        assert model.streetsInstance == streets
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/streets/list'

        populateValidParams(params)
        def streets = new Streets(params)

        assert streets.save() != null

        params.id = streets.id

        def model = controller.edit()

        assert model.streetsInstance == streets
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/streets/list'

        response.reset()

        populateValidParams(params)
        def streets = new Streets(params)

        assert streets.save() != null

        // test invalid parameters in update
        params.id = streets.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/streets/edit"
        assert model.streetsInstance != null

        streets.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/streets/show/$streets.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        streets.clearErrors()

        populateValidParams(params)
        params.id = streets.id
        params.version = -1
        controller.update()

        assert view == "/streets/edit"
        assert model.streetsInstance != null
        assert model.streetsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/streets/list'

        response.reset()

        populateValidParams(params)
        def streets = new Streets(params)

        assert streets.save() != null
        assert Streets.count() == 1

        params.id = streets.id

        controller.delete()

        assert Streets.count() == 0
        assert Streets.get(streets.id) == null
        assert response.redirectedUrl == '/streets/list'
    }
}
