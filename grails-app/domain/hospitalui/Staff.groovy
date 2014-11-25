package hospitalui

class Staff extends Person{

	String doctorId;
	
    static constraints = {
		doctors: nullable:true;
    }
	
	static mapping = {
		version false
		table 'Staff'
		id column: 'UserId'
		doctorId column: 'doctorId'
	}
}
