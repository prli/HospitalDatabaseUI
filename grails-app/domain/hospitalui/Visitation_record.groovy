package hospitalui

import java.util.Formatter.DateTime

class Visitation_record {

	Patient patient
	Doctor doctor
	String procedures
	String diagnosis
	String prescription
	String scheduleOfTreatment
	double costOfVisit
	double lengthOfVisit
	String comments
	DateTime dateOfVisit
	
    static constraints = {
    }
	
	static mapping = {
		version false
		table 'Visitation_Record'
		patient column: 'Patient'
		doctor column: 'doctorId'
		procedures column: 'Procecures'
		diagnosis column: 'Diagnosis'
		prescription column: 'Prescription'
		scheduleOfTreatment column: 'Schedule_of_treatment'
		costOfVisit column: 'Cost_of_visit'
		lengthOfVisit column: 'Length_of_visit'
		comments column: 'Comments'
		dateOfVisit column: 'Date_of_visit'
	}
}
