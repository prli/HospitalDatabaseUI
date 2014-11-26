package hospitalui



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Financial_officerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	String userID

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		userID = params.userID
		def financialOfficer = Financial_officer.findById(userID)
		def doctors = Doctor.list(params)
        render(view: "index", model: [financialOfficerInstance: financialOfficer, doctorInstanceList: doctors, doctorInstanceList: Doctor.list(params)])
    }
	
	def searchDoctor(){
		Financial_OfficerDAOImpl dao = new Financial_OfficerDAOImpl()
		String doctorId = params.doctorId;
		String firstName = params.firstName;
		String lastName = params.lastName;
		def financialOfficer = Financial_officer.findById(userID)
		//def doctors = dao.getDoctor();
		def doctors = dao.getDoctorFromInput(doctorId, firstName, lastName);
		render(view: "index", model: [doctorInstanceCount: Doctor.count(), doctorInstanceList: Doctor.list(params), doctorInstanceList: doctors, financialOfficerInstance: financialOfficer])
	}

    def show(Financial_officer financial_officerInstance) {
        respond financial_officerInstance
    }

    def create() {
        respond new Financial_officer(params)
    }

    @Transactional
    def save(Financial_officer financial_officerInstance) {
		financial_officerInstance.id = financial_officerInstance.firstName + financial_officerInstance.lastName
        if (financial_officerInstance == null) {
            notFound()
            return
        }

        if (financial_officerInstance.hasErrors()) {
            respond financial_officerInstance.errors, view:'create'
            return
        }

        financial_officerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'financial_officer.label', default: 'Financial_officer'), financial_officerInstance.id])
                redirect financial_officerInstance
            }
            '*' { respond financial_officerInstance, [status: CREATED] }
        }
    }

    def edit(Financial_officer financial_officerInstance) {
        respond financial_officerInstance
    }

    @Transactional
    def update(Financial_officer financial_officerInstance) {
        if (financial_officerInstance == null) {
            notFound()
            return
        }

        if (financial_officerInstance.hasErrors()) {
            respond financial_officerInstance.errors, view:'edit'
            return
        }

        financial_officerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Financial_officer.label', default: 'Financial_officer'), financial_officerInstance.id])
                redirect financial_officerInstance
            }
            '*'{ respond financial_officerInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Financial_officer financial_officerInstance) {

        if (financial_officerInstance == null) {
            notFound()
            return
        }

        financial_officerInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Financial_officer.label', default: 'Financial_officer'), financial_officerInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'financial_officer.label', default: 'Financial_officer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
