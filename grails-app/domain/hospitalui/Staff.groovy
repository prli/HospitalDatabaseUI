package hospitalui

class Staff extends Person{

	Doctor doctor;
	static hasMany = [doctors: Doctor];
	
    static constraints = {
		doctors: nullable:true;
    }
	
	static mapping = {
		version false
		table 'Staff'
		id column: 'UserId', name: 'userId', generator: 'assigned'
		doctor column: 'doctorId'
	}
}
