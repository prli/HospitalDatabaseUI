package hospitalui



import grails.test.mixin.*
import spock.lang.*

@TestFor(Financial_officerController)
@Mock(Financial_officer)
class Financial_officerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.financial_officerInstanceList
            model.financial_officerInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.financial_officerInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def financial_officer = new Financial_officer()
            financial_officer.validate()
            controller.save(financial_officer)

        then:"The create view is rendered again with the correct model"
            model.financial_officerInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            financial_officer = new Financial_officer(params)

            controller.save(financial_officer)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/financial_officer/show/1'
            controller.flash.message != null
            Financial_officer.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def financial_officer = new Financial_officer(params)
            controller.show(financial_officer)

        then:"A model is populated containing the domain instance"
            model.financial_officerInstance == financial_officer
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def financial_officer = new Financial_officer(params)
            controller.edit(financial_officer)

        then:"A model is populated containing the domain instance"
            model.financial_officerInstance == financial_officer
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/financial_officer/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def financial_officer = new Financial_officer()
            financial_officer.validate()
            controller.update(financial_officer)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.financial_officerInstance == financial_officer

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            financial_officer = new Financial_officer(params).save(flush: true)
            controller.update(financial_officer)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/financial_officer/show/$financial_officer.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/financial_officer/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def financial_officer = new Financial_officer(params).save(flush: true)

        then:"It exists"
            Financial_officer.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(financial_officer)

        then:"The instance is deleted"
            Financial_officer.count() == 0
            response.redirectedUrl == '/financial_officer/index'
            flash.message != null
    }
}
