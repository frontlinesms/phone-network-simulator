package phonenetworksimulator

class PhoneController {
	
	 
	def save(){
	//if param is Null to remove Validationsave Exception
		if(params.phoneNumber) {
			def phoneInstance = new Phone(params)
			phoneInstance.save(flush: true,failOnError:true)
			redirect(controller:'message', action:'phone', params:[phoneNumber:params.phoneNumber])
		} 
		else {
			flash.message = "Phone Number cannot be null"
			redirect(controller:'message', action:'phone', params:[phoneNumber:params.phoneNumber])
		}
		
	}
}
