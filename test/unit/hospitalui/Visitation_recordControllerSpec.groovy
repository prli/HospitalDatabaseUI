package hospitalui



import grails.test.mixin.*
import spock.lang.*

@TestFor(Visitation_recordController)
@Mock(Visitation_record)
class Visitation_recordControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.visitation_recordInstanceList
            model.visitation_recordInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.visitation_recordInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def visitation_record = new Visitation_record()
            visitation_record.validate()
            controller.save(visitation_record)

        then:"The create view is rendered again with the correct model"
            model.visitation_recordInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            visitation_record = new Visitation_record(params)

            controller.save(visitation_record)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/visitation_record/show/1'
            controller.flash.message != null
            Visitation_record.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def visitation_record = new Visitation_record(params)
            controller.show(visitation_record)

        then:"A model is populated containing the domain instance"
            model.visitation_recordInstance == visitation_record
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def visitation_record = new Visitation_record(params)
            controller.edit(visitation_record)

        then:"A model is populated containing the domain instance"
            model.visitation_recordInstance == visitation_record
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/visitation_record/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def visitation_record = new Visitation_record()
            visitation_record.validate()
            controller.update(visitation_record)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.visitation_recordInstance == visitation_record

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            visitation_record = new Visitation_record(params).save(flush: true)
            controller.update(visitation_record)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/visitation_record/show/$visitation_record.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/visitation_record/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def visitation_record = new Visitation_record(params).save(flush: true)

        then:"It exists"
            Visitation_record.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(visitation_record)

        then:"The instance is deleted"
            Visitation_record.count() == 0
            response.redirectedUrl == '/visitation_record/index'
            flash.message != null
    }
}
