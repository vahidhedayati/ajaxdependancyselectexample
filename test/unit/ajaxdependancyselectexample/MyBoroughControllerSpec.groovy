package ajaxdependancyselectexample



import grails.test.mixin.*
import spock.lang.*

@TestFor(MyBoroughController)
@Mock(MyBorough)
class MyBoroughControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.myBoroughInstanceList
            model.myBoroughInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.myBoroughInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def myBorough = new MyBorough()
            myBorough.validate()
            controller.save(myBorough)

        then:"The create view is rendered again with the correct model"
            model.myBoroughInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            myBorough = new MyBorough(params)

            controller.save(myBorough)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/myBorough/show/1'
            controller.flash.message != null
            MyBorough.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def myBorough = new MyBorough(params)
            controller.show(myBorough)

        then:"A model is populated containing the domain instance"
            model.myBoroughInstance == myBorough
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def myBorough = new MyBorough(params)
            controller.edit(myBorough)

        then:"A model is populated containing the domain instance"
            model.myBoroughInstance == myBorough
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/myBorough/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def myBorough = new MyBorough()
            myBorough.validate()
            controller.update(myBorough)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.myBoroughInstance == myBorough

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            myBorough = new MyBorough(params).save(flush: true)
            controller.update(myBorough)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/myBorough/show/$myBorough.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/myBorough/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def myBorough = new MyBorough(params).save(flush: true)

        then:"It exists"
            MyBorough.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(myBorough)

        then:"The instance is deleted"
            MyBorough.count() == 0
            response.redirectedUrl == '/myBorough/index'
            flash.message != null
    }
}
