package hospitalui



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DoctorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		def userId = "DennisSo"
		def patients = Patient.findAllByDoctor(Doctor.findById(userId))
		def doctor = Doctor.findById(userId)
        render(view: "index", model: [patientInstanceCount: patients.size() , doctorInstance: doctor, patientInstanceList: patients])
    }
	
	def searchPatient(){
		DoctorDAOImpl dao = new DoctorDAOImpl()
		def userId = "DennisSo"
		def doctor = Doctor.findById(userId)
		String ohip = params.ohip
		String sin = params.sin
		String homePhone = params.homePhone
		String healthCondition = params.healthCondition
		String lastVisitedDate = params.lastVisitedDate
		String numOfVisits = params.numOfVisits
		def patients = dao.getPatient(userId, homePhone, lastVisitedDate, sin, numOfVisits, ohip, healthCondition);
		render(view: "index", model: [doctorInstanceCount: Doctor.count(), doctorInstanceList: Doctor.list(params), patientInstanceList: patients, doctorInstance: doctor])
	}

    def show(Doctor doctorInstance) {
        respond doctorInstance
    }

    def create() {
        respond new Doctor(params)
    }

    @Transactional
    def save(Doctor doctorInstance) {
		doctorInstance.id = doctorInstance.firstName + doctorInstance.lastName
        if (doctorInstance == null) {
            notFound()
            return
        }

        if (doctorInstance.hasErrors()) {
            respond doctorInstance.errors, view:'create'
            return
        }

        doctorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'doctor.label', default: 'Doctor'), doctorInstance.id])
                redirect doctorInstance
            }
            '*' { respond doctorInstance, [status: CREATED] }
        }
    }

    def edit(Doctor doctorInstance) {
        respond doctorInstance
    }

    @Transactional
    def update(Doctor doctorInstance) {
        if (doctorInstance == null) {
            notFound()
            return
        }

        if (doctorInstance.hasErrors()) {
            respond doctorInstance.errors, view:'edit'
            return
        }

        doctorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Doctor.label', default: 'Doctor'), doctorInstance.id])
                redirect doctorInstance
            }
            '*'{ respond doctorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Doctor doctorInstance) {

        if (doctorInstance == null) {
            notFound()
            return
        }

        doctorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Doctor.label', default: 'Doctor'), doctorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'doctor.label', default: 'Doctor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
