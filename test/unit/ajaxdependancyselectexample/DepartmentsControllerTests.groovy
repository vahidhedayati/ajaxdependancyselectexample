package ajaxdependancyselectexample



import org.junit.*
import grails.test.mixin.*

@TestFor(DepartmentsController)
@Mock(Departments)
class DepartmentsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/departments/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.departmentsInstanceList.size() == 0
        assert model.departmentsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.departmentsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.departmentsInstance != null
        assert view == '/departments/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/departments/show/1'
        assert controller.flash.message != null
        assert Departments.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/departments/list'

        populateValidParams(params)
        def departments = new Departments(params)

        assert departments.save() != null

        params.id = departments.id

        def model = controller.show()

        assert model.departmentsInstance == departments
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/departments/list'

        populateValidParams(params)
        def departments = new Departments(params)

        assert departments.save() != null

        params.id = departments.id

        def model = controller.edit()

        assert model.departmentsInstance == departments
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/departments/list'

        response.reset()

        populateValidParams(params)
        def departments = new Departments(params)

        assert departments.save() != null

        // test invalid parameters in update
        params.id = departments.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/departments/edit"
        assert model.departmentsInstance != null

        departments.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/departments/show/$departments.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        departments.clearErrors()

        populateValidParams(params)
        params.id = departments.id
        params.version = -1
        controller.update()

        assert view == "/departments/edit"
        assert model.departmentsInstance != null
        assert model.departmentsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/departments/list'

        response.reset()

        populateValidParams(params)
        def departments = new Departments(params)

        assert departments.save() != null
        assert Departments.count() == 1

        params.id = departments.id

        controller.delete()

        assert Departments.count() == 0
        assert Departments.get(departments.id) == null
        assert response.redirectedUrl == '/departments/list'
    }
}
