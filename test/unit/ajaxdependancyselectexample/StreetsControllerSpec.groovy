package ajaxdependancyselectexample



import grails.test.mixin.*
import spock.lang.*

@TestFor(StreetsController)
@Mock(Streets)
class StreetsControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.streetsInstanceList
            model.streetsInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.streetsInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def streets = new Streets()
            streets.validate()
            controller.save(streets)

        then:"The create view is rendered again with the correct model"
            model.streetsInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            streets = new Streets(params)

            controller.save(streets)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/streets/show/1'
            controller.flash.message != null
            Streets.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def streets = new Streets(params)
            controller.show(streets)

        then:"A model is populated containing the domain instance"
            model.streetsInstance == streets
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def streets = new Streets(params)
            controller.edit(streets)

        then:"A model is populated containing the domain instance"
            model.streetsInstance == streets
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/streets/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def streets = new Streets()
            streets.validate()
            controller.update(streets)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.streetsInstance == streets

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            streets = new Streets(params).save(flush: true)
            controller.update(streets)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/streets/show/$streets.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/streets/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def streets = new Streets(params).save(flush: true)

        then:"It exists"
            Streets.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(streets)

        then:"The instance is deleted"
            Streets.count() == 0
            response.redirectedUrl == '/streets/index'
            flash.message != null
    }
}
