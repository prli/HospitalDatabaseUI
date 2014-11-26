package hospitalui

class Staff extends Person{

	Doctor doctor;
	
    static constraints = {
		doctors: nullable:true;
    }
	
	static mapping = {
		version false
		table 'Staff'
		id column: 'UserId'
		doctor column: 'doctorId'
	}
}
