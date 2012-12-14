import phonenetworksimulator.*

class BootStrap {
	def init = { servletContext ->
   
		new Phone(phoneNumber:"+254721345678").save(failOnError:true, flush:true)
		new Phone(phoneNumber:"+254723456789").save(failOnError:true, flush:true)
    	def number = new Message(recepient:"+254723456789", source:"+254721345678",text:"My first Message").save(flush:true)    
    }
    
    def destroy = {
    }
}
