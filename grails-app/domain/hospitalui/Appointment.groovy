package hospitalui

import java.util.Formatter.DateTime

class Appointment implements Serializable{
	
	String id
	Patient patient
	Date startTime
	int duration
	String status
	
    static constraints = {
    }
	
	static mapping = {
		version false
		table 'Appointment'
		id composite: ['patient', 'startTime']
		patient column: 'patientId'
		startTime column: 'Date_Time'
		duration column: 'Duration'
		status column: 'Status'
	}
}
