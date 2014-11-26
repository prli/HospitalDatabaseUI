package hospitalui

class LoginController {

    def index() { }
	
	def loginSubmit(){
		
		println params.userID
		println params.password
		
		LoginDAOImpl dao = new LoginDAOImpl()
		
		def person = dao.getPersonTypeFromCredentials(params.userID, params.password)
		
		
		if(person)
		{
			if(person instanceof Doctor)
			{
				Doctor d = (Doctor)person
				redirect(controller:"Doctor", view:"", params:[userID: d.id])
			}
			else if(person instanceof Patient)
			{
				Patient p = (Patient)person
				redirect(controller:"Patient", view:"", params:[userID: p.id])
			}
			else if(person instanceof Financial_officer)
			{
				Financial_officer f = (Financial_officer)person
				redirect(controller:"Financial_officer", view:"", params:[userID: f.id])
			}
			else if(person instanceof Staff)
			{
				Staff s = (Staff)person
				redirect(controller:"Staff", view:"", params:[userID: s.id])
			}
			else
			{
				// throw
			}	
		}
		else
		{
			flash.message = "Incorrect username or password"
			redirect(uri:"/", params:[error:"error"])
		}
	}
}
