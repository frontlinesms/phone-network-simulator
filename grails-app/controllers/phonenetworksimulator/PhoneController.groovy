package phonenetworksimulator

class PhoneController {
	
	def save() {
		//TODO implement me
		def phoneInstance = new Phone(params)
		println "params are $params"
		 if (phoneInstance.save(flush: true,failOnError:true)) {
            redirect(controller:'message', action:'phone', params:[phoneNumber:params.phoneNumber])
        }
        else{
             render text: 'Failed to save'    
        }

}
}