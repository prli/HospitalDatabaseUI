package hospitalui

class Patient extends Person{

	String ohip;
	String sin;
	String homePhone;
	String healthCondition;
	Date lastVisitedDate;
	int numOfVisits;
	
	static belongsTo = [doctor: Doctor]
	
    static constraints = {
		ohip unique:true;
		sin unique:true;
		homePhone nullable:true;
		healthCondition nullable:true;
		lastVisitedDate nullable:true;
		numOfVisits nullable:true;
    }
}
