package phonenetworksimulator

class PhoneController {
	
	 
	def save(){
	    //if param is Null to remove Validationsave Exception
		if(params.deviceType == 'phone') {
			def phoneInstance =new Phone(params)
			phoneInstance.save(flush: true,failOnError:true)
			redirect(controller:'message', action:'phone', params:[phoneNumber:params.phoneNumber])
		} 
		
		else if(params.deviceType == 'modem'){
		def modemInstance = new Modem(params)
		modemInstance.save(flush:true,failOnError:true)
		redirect(controller:'message', action:'phone', params:[url:params.url])
		
		}
		else {
		flash.message = "Url And PhoneNumber cannot be null"
			redirect(controller:'message', action:'phone', params:[phoneNumber:params.phoneNumber])
		}
		
	   
		
	}
}


