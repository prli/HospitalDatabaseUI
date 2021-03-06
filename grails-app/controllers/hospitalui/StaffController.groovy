package hospitalui



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StaffController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	String userID
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		userID = params.userID
		def staff = Staff.findById(userID)
		PatientDAOImpl dao = new PatientDAOImpl()
		def staffDoctorList = dao.getDoctorListByStaff(userID)
		def patientListInstance = dao.getPatientListByStaff(userID)
        render(view:"index", model:[staffInstance: staff, staffDoctorListInstance: staffDoctorList, patientListInstance: patientListInstance])
    }

	def addDoctor(){
		String doctorId = params.doctorId
		String staffId = params.staffId
		DoctorDAOImpl dao = new DoctorDAOImpl()
		try {
			boolean success = dao.addDoctorForStaff(doctorId, staffId)
			flash.message = "added"
		} catch (Exception e) {
			flash.message = e.message
		}
		redirect(action:"index", params:[userID:staffId])
	}
	
	@Transactional
	def removeDoctor(){
		String doctorId = params.doctorId
		String staffId = params.staffId
		DoctorDAOImpl dao = new DoctorDAOImpl()
		try {
			boolean success = dao.removeDoctorForStaff(doctorId, staffId)
			flash.message = "deleted"
		} catch (Exception e) {
			flash.message = e.message
		}
		redirect(action:"index", params:[userID:staffId])
	}
	
    def show(Staff staffInstance) {
        respond staffInstance
    }

    def create() {
        respond new Staff(params)
    }

    @Transactional
    def save(Staff staffInstance) {
		staffInstance.id = staffInstance.firstName + staffInstance.lastName
		staffInstance.isActive = true
        if (staffInstance == null) {
            notFound()
            return
        }

        if (staffInstance.hasErrors()) {
            respond staffInstance.errors, view:'create'
            return
        }

        staffInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'staff.label', default: 'Staff'), staffInstance.id])
                redirect staffInstance
            }
            '*' { respond staffInstance, [status: CREATED] }
        }
    }
	
	@Transactional
	def editStaff() {
		String staffId = params.staffId
		println staffId
		Staff staff = Person.findById(staffId)
		staff.firstName = params.firstName
		staff.lastName = params.lastName
		staff.password = params.password
		staff.save flush:true
		flash.message = "changed"
		redirect(action:"index", params:[userID:staffId])
	}
	
    def edit(Staff staffInstance) {
        respond staffInstance
    }

    @Transactional
    def update(Staff staffInstance) {
        if (staffInstance == null) {
            notFound()
            return
        }

        if (staffInstance.hasErrors()) {
            respond staffInstance.errors, view:'edit'
            return
        }

        staffInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Staff.label', default: 'Staff'), staffInstance.id])
                redirect staffInstance
            }
            '*'{ respond staffInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Staff staffInstance) {
		staffInstance.isActive = false
        if (staffInstance == null) {
            notFound()
            return
        }

        staffInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Staff.label', default: 'Staff'), staffInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'staff.label', default: 'Staff'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
