package hospitalui

class Patient extends Person{

	String OHIP;
	String SIN;
	String homePhone;
	String healthCondition;
	Date lastVisitedDate;
	int numOfVisits;
	Doctor doctorId;
	
    static constraints = {
    }
}
