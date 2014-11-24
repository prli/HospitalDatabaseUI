package hospitalui

class Doctor extends Person{
	
	double revenue
	
    static constraints = {
    }
	
	static mapping = {
		version false
		table 'Doctor'
		id column: 'UserId'
		revenue column: 'Revenue'
	}
	
}
