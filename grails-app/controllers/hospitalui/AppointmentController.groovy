package hospitalui



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional(readOnly = true)
class AppointmentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Appointment.list(params), model:[appointmentInstanceCount: Appointment.count()];
    }
	
	def doctorAppointments(){
		String doctorId = params.doctorId
		Doctor doctor = Doctor.findById(doctorId)
		DoctorDAOImpl dao = new DoctorDAOImpl()
		def appointments = dao.getFutureAppointments(doctorId)
		render(view: "index", model:[futureAppointmentInstanceList: appointments, doctorInstance: doctor])
	}

    def show(Appointment appointmentInstance) {
        respond appointmentInstance
    }

    def create(String doctorId) {
		Doctor doctor = Doctor.findById(doctorId)
		def patientInstanceList = Patient.findAllByDoctor(doctor)
        render(view: "create", model: [patientInstanceList: patientInstanceList])
    }
	
	@Transactional
	def removeAppointment(){
		String patientId = params.patientId
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-d")
		Date startTime = formatter.parse(params.startTime)
		String duration = params.duration
		String status = params.status
		Patient patient = Patient.findById(patientId)
		String doctorId = patient.doctor.id
		Appointment appointmentInstance = Appointment.findByPatientAndStartTime(patient, startTime)
		appointmentInstance.delete flush:true
		DoctorDAOImpl dao = new DoctorDAOImpl()
		def appointments = dao.getFutureAppointments(doctorId)
		redirect(action: "doctorAppointments", params: [doctorId: doctorId])
	}

    @Transactional
    def save(Appointment appointmentInstance) {
		appointmentInstance.id = ""
        if (appointmentInstance == null) {
            notFound()
            return
        }

        if (appointmentInstance.hasErrors()) {
            respond appointmentInstance.errors, view:'create'
            return
        }

        appointmentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'appointment.label', default: 'Appointment'), appointmentInstance.id])
				Patient patient = Patient.findById(appointmentInstance.patient.id)
				String doctorId = patient.doctor.id
				redirect (action: "doctorAppointments", params: [doctorId: doctorId])

            }
            '*' { respond appointmentInstance, [status: CREATED] }
        }
    }

    def edit(Appointment appointmentInstance) {
        respond appointmentInstance
    }

    @Transactional
    def update(Appointment appointmentInstance) {
        if (appointmentInstance == null) {
            notFound()
            return
        }

        if (appointmentInstance.hasErrors()) {
            respond appointmentInstance.errors, view:'edit'
            return
        }

        appointmentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Appointment.label', default: 'Appointment'), appointmentInstance.id])
                redirect appointmentInstance
            }
            '*'{ respond appointmentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Appointment appointmentInstance) {

        if (appointmentInstance == null) {
            notFound()
            return
        }

        appointmentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Appointment.label', default: 'Appointment'), appointmentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'appointment.label', default: 'Appointment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
