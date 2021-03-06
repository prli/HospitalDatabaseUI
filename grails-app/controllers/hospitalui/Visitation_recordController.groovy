package hospitalui



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional(readOnly = true)
class Visitation_recordController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)	
        render(view:"index",  model:[visitation_recordInstanceList: Visitation_record.list(params), visitation_recordInstanceCount: Visitation_record.count()])
    }
	
	def showForDoctorOrStaffOrFO(String doctorId){
		Doctor d = Doctor.findByIt(doctorId)
		def records = Visitation_record.findAllByDoctor(d)
		render(view:"index",  model:[visitation_recordInstanceList: records, visitation_recordInstanceCount: records.size()])
	}
	
	def showForPatient(){
		String patientId = params.patientId
		Patient p = Patient.findById(patientId)
		def records = Visitation_record.findAllByPatient(p)
		render(view:"index",  model:[visitation_recordInstanceList: records, visitation_recordInstanceCount: records.size()])
	}
	
    def show() {
		Patient patient = Patient.findById(params.patientId)
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date parsedUtilDate = formater.parse(params.dateOfVisit);
		java.sql.Date sqlDate= new java.sql.Date(parsedUtilDate.getTime())
		Date dateOfVisit = sqlDate;
		Visitation_record visitation_recordInstance = Visitation_record.findByPatientAndDateOfVisit(patient, dateOfVisit)
        respond visitation_recordInstance
    }

    def create() {
        respond new Visitation_record(params)
    }
	
	def FOresult(Doctor doctorInstance) {
		def records = Visitation_record.findAllByDoctor(doctorInstance)
		render(view:"FOsearch",  model:[doctorInstance: doctorInstance, visitation_recordInstanceList: records, visitation_recordInstanceCount: Visitation_record.count()])
	}
	def searchByDoctor(){
//		Financial_OfficerDAOImpl dao = new Financial_OfficerDAOImpl()
//		def docId = params.doctorId
		Doctor doc = Doctor.findById(params.doctorId)
		Date start = params.StartDate;
		Date end = params.EndDate;
//		System.out.println(end);
		def result = Visitation_record.findAllByDoctorAndDateOfVisitBetween(doc, start, end.plus(1))
		render(view:"FOsearch",  model:[doctorInstance: doc, visitation_recordInstanceList: result, visitation_recordInstanceCount: Visitation_record.count()])
//		render(view: "FOsearch", model: [doctorInstanceCount: Doctor.count(), doctorInstanceList: Doctor.list(params), doctorInstanceList: doctors, financialOfficerInstance: financialOfficer])
	}
	def DocResult(Patient patientInstance){
//		String test = toString(patientInstance.id)
		
		DoctorDAOImpl dao = new DoctorDAOImpl()
//		System.out.println(dao.getDocIdFromPatId("joker"));
		Doctor doc = Doctor.findById(dao.getDocIdFromPatId(patientInstance.id))
		
//		def test = params.doctorId
		
		def records = Visitation_record.findAllByPatientAndDoctor(patientInstance, doc)
//		def records = Visitation_record.findAllByPatient(patientInstance)
		render(view:"docSearch",  model:[visitation_recordInstanceList: records, visitation_recordInstanceCount: Visitation_record.count()])
	}

    @Transactional
    def save(Visitation_record visitation_recordInstance) {
		visitation_recordInstance.id = ''
        if (visitation_recordInstance == null) {
            notFound()
            return
        }

        if (visitation_recordInstance.hasErrors()) {
            respond visitation_recordInstance.errors, view:'create'
            return
        }

        visitation_recordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'visitation_record.label', default: 'Visitation_record'), visitation_recordInstance.id])
                redirect visitation_recordInstance
            }
            '*' { respond visitation_recordInstance, [status: CREATED] }
        }
    }

    def edit() {
		Patient patient = Patient.findById(params.patientId)
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date parsedUtilDate = formater.parse(params.dateOfVisit);
		java.sql.Date sqlDate= new java.sql.Date(parsedUtilDate.getTime())
		Date dateOfVisit = sqlDate;
		Visitation_record visitation_recordInstance = Visitation_record.findByPatientAndDateOfVisit(patient, dateOfVisit)
        respond visitation_recordInstance
    }

    @Transactional
    def update() {
		Patient patient = Patient.findById(params.patient.id)
		Date dateOfVisit = params.dateOfVisit;
		Visitation_record visitation_recordInstance = new Visitation_record(id:[patient,dateOfVisit])
		println visitation_recordInstance.id
		visitation_recordInstance = Visitation_record.findByPatientAndDateOfVisit(patient, dateOfVisit)
        if (visitation_recordInstance == null) {
            notFound()
            return
        }

        if (visitation_recordInstance.hasErrors()) {
            respond visitation_recordInstance.errors, view:'edit'
            return
        }

        visitation_recordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Visitation_record.label', default: 'Visitation_record'), visitation_recordInstance.id])
                redirect visitation_recordInstance
            }
            '*'{ respond visitation_recordInstance, [status: OK] }
        }
    }

    @Transactional
    def delete() {
		Patient patient = Patient.findById(params.patientId)
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date parsedUtilDate = formater.parse(params.dateOfVisit);
		java.sql.Date sqlDate= new java.sql.Date(parsedUtilDate.getTime())
		Date dateOfVisit = sqlDate;
		Visitation_record visitation_recordInstance = Visitation_record.findByPatientAndDateOfVisit(patient, dateOfVisit)
        if (visitation_recordInstance == null) {
            notFound()
            return
        }

        visitation_recordInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Visitation_record.label', default: 'Visitation_record'), visitation_recordInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'visitation_record.label', default: 'Visitation_record'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
