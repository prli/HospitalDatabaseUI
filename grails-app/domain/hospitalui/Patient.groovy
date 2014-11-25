package hospitalui

class Patient extends Person{

	String ohip;
	String sin;
	String homePhone;
	String healthCondition;
	Date lastVisitedDate;
	int numOfVisits;
	
	Doctor doctor;
	
    static constraints = {
		ohip unique:true;
		sin unique:true;
		homePhone nullable:true;
		healthCondition nullable:true;
		lastVisitedDate nullable:true;
		numOfVisits nullable:true;
    }
	
	static mapping = {
		version false
		table 'Patient'
		id column: 'UserId'
		ohip column: 'OHIP'
		sin column: 'SIN'
		homePhone column: 'Phone'
		healthCondition column: 'Current_health'
		lastVisitedDate column: 'Last_visit_date'
		numOfVisits column: 'Num_of_visits'
		doctor column: 'DoctorId'
	}
	
	public void setDoctor(String doctorId){
		this.doctor = Doctor.findById(doctorId)
	}
}
