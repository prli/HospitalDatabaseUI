package hospitalui

class Staff extends Person{

	static hasMany = [doctors: Doctor];
	
    static constraints = {
    }
}
