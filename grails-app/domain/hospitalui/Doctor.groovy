package hospitalui

class Doctor extends Person{
	
    static constraints = {
    }
	
	static mapping = {
		version false
		table 'Doctor'
		id column: 'UserId'
	}
	
}
