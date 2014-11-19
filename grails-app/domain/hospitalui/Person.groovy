package hospitalui

class Person {
	
	String userId;
	String firstName;
	String lastName;
	String password;
	
    static constraints = {
		userId unique: true;
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
