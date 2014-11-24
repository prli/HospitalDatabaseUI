package hospitalui

class Patient extends Person{

	String ohip;
	String sin;
	String homePhone;
	String healthCondition;
	Date lastVisitedDate;
	int numOfVisits;
	
	String doctorId;
	
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
		doctorId column: 'DoctorId'
	}
}
