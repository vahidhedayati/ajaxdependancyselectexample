package ajaxdependancyselectexample



import grails.test.mixin.*
import spock.lang.*

@TestFor(DocumentsController)
@Mock(Documents)
class DocumentsControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.documentsInstanceList
            model.documentsInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.documentsInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def documents = new Documents()
            documents.validate()
            controller.save(documents)

        then:"The create view is rendered again with the correct model"
            model.documentsInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            documents = new Documents(params)

            controller.save(documents)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/documents/show/1'
            controller.flash.message != null
            Documents.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def documents = new Documents(params)
            controller.show(documents)

        then:"A model is populated containing the domain instance"
            model.documentsInstance == documents
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def documents = new Documents(params)
            controller.edit(documents)

        then:"A model is populated containing the domain instance"
            model.documentsInstance == documents
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/documents/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def documents = new Documents()
            documents.validate()
            controller.update(documents)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.documentsInstance == documents

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            documents = new Documents(params).save(flush: true)
            controller.update(documents)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/documents/show/$documents.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/documents/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def documents = new Documents(params).save(flush: true)

        then:"It exists"
            Documents.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(documents)

        then:"The instance is deleted"
            Documents.count() == 0
            response.redirectedUrl == '/documents/index'
            flash.message != null
    }
}
