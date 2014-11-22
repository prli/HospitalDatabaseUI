package hospitalui

class Staff extends Person{

	static hasMany = [doctors: Doctor];
	
    static constraints = {
		doctors: nullable:true;
    }
	
	static mapping = {
		version false
		table 'Staff'
		id column: 'UserId'
	}
}
