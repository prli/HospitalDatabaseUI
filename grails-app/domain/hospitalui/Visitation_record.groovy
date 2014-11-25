package hospitalui

import org.apache.commons.lang.builder.HashCodeBuilder

class Visitation_record implements Serializable{

	String id
	Patient patient
	Doctor doctor
	String procedures
	String diagnosis
	String prescription
	String scheduleOfTreatment
	double costOfVisit
	double lengthOfVisit
	String comments
	Date dateOfVisit
	
    static constraints = {
    }
	
	static mapping = {
		version false
		table 'Visitation_Record'
		id composite: ['patient', 'dateOfVisit'], generator: 'increment'
		patient column: 'PatientId'
		doctor column: 'DoctorId'
		procedures column: 'Procedures'
		diagnosis column: 'Diagnosis'
		prescription column: 'Prescription'
		scheduleOfTreatment column: 'Schedule_of_treatment'
		costOfVisit column: 'Cost_of_visit'
		lengthOfVisit column: 'Length_of_visit'
		comments column: 'Comments'
		dateOfVisit column: 'Date_of_visit'
	}
	
	boolean equals(other) {
		if (!(other instanceof Visitation_record)) {
			return false
		}

		other.patient == patient && other.dateOfVisit == dateOfVisit
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append patient
		builder.append patient
		builder.toHashCode()
	}
}
