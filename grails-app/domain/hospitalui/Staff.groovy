package hospitalui

class Staff extends Person{

	String doctorId;
	
    static constraints = {
		doctors: nullable:true;
    }
	
	static mapping = {
		version false
		table 'Staff'
		id column: 'UserId', name: 'userId', generator: 'assigned'
		doctorId column: 'doctorId'
	}
}
