package hospitalui

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PermissionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Permission.list(params), model:[permissionInstanceCount: Permission.count()]
    }

    def show(Permission permissionInstance) {
        respond permissionInstance
    }
	
	def managePatientAccess(){
		def patientId = params.patientId
		def doctorId = params.doctorId
		def patient = Patient.findById(patientId)
		def doctor = Doctor.findById(doctorId)
		def allowedDoctors = Permission.findAllByPatient(patient)
		render(view: "index", model:[permissionInstanceList: allowedDoctors])
	}
	
	@Transactional
	def removePermission(){
		System.out.println("hdfdfdfdfh")
		String patientId = params.patientId
		String doctorId = params.granteeId
		Patient patient = Patient.findById(patientId)
		Doctor doctor = Doctor.findById(doctorId)
		Permission permissionInstance = Permission.findByPatientAndGrantee(patient, doctor)
		permissionInstance.delete flush:true
		def allowedDoctors = Permission.findAllByPatient(patient)
		redirect(action: "index", params: [permissionInstanceCount: Permission.count(), permissionInstanceList: allowedDoctors])
	}

    def create() {
        respond new Permission(params)
    }

    @Transactional
    def save(Permission permissionInstance) {
		permissionInstance.id = ""
        if (permissionInstance == null) {
            notFound()
            return
        }

        if (permissionInstance.hasErrors()) {
            respond permissionInstance.errors, view:'create'
            return
        }

        permissionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'permission.label', default: 'Permission'), permissionInstance.id])
                redirect permissionInstance
            }
            '*' { respond permissionInstance, [status: CREATED] }
        }
    }

    def edit(Permission permissionInstance) {
        respond permissionInstance
    }

    @Transactional
    def update(Permission permissionInstance) {
        if (permissionInstance == null) {
            notFound()
            return
        }

        if (permissionInstance.hasErrors()) {
            respond permissionInstance.errors, view:'edit'
            return
        }

        permissionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Permission.label', default: 'Permission'), permissionInstance.id])
                redirect permissionInstance
            }
            '*'{ respond permissionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Permission permissionInstance) {

        if (permissionInstance == null) {
            notFound()
            return
        }

        permissionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Permission.label', default: 'Permission'), permissionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
