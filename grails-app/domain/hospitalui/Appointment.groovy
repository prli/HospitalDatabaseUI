package hospitalui

import java.util.Formatter.DateTime

class Appointment implements Serializable{
	
	String id
	String patientId
	Date startTime
	int duration
	String status
	
    static constraints = {
    }
	
	static mapping = {
		version false
		table 'Appointment'
		id composite: ['patientId', 'startTime']
		patientId column: 'PatientId'
		startTime column: 'Date_Time'
		duration column: 'Duration'
		status column: 'Status'
	}
}
