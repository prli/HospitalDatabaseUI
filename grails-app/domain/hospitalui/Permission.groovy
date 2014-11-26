package hospitalui

class Permission implements Serializable{
	
	String id
	Doctor grantee
	Patient patient
	
    static constraints = {
    }
	
	static mapping = {
		version false
		id composite: ['grantee', 'patient']
		grantee column: "GranteeId"
		patient column: "PatientId"
	}
}
