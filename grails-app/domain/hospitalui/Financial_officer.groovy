package hospitalui

class Financial_officer extends Person{

    static constraints = {
    }
	
	static mapping = {
		version false
		table 'Financial_Officer'
		id column: 'UserId'
	}
}
