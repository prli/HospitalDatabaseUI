package hospitalui

class Person {
	
	String id
	String firstName;
	String lastName;
	String password;
	
    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
		version false
		table 'Person'
		id column: 'UserId', generator: 'assigned'
		firstName column: 'FirstName'
		lastName column: 'LastName'
		password column: 'Password'
	}
}
