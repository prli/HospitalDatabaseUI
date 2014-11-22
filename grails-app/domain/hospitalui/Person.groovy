package hospitalui

class Person {
	
	String userId;
	String firstName;
	String lastName;
	String password;
	
    static constraints = {
		userId unique:true
    }
	
	static mapping = {
		tablePerHierarchy false
		version false
		table 'Person'
		id column: 'UserId', name: 'userId'
		userId column: 'UserId'
		firstName column: 'FirstName'
		lastName column: 'LastName'
		password column: 'Password'
	}
}
