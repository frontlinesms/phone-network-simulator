package phonenetworksimulator

class PhoneController {
	
	 
def save(){
   def phoneInstance = new Phone(params)

     try {
      phoneInstance.save(flush: true,failOnError:true)
      redirect(controller:'message', action:'phone', params:[phoneNumber:params.phoneNumber])
      } catch(Exception e){
      redirect(controller:'message', action:'phone', params:[phoneNumber:params.phoneNumber])
      println "The Param is Null"
}
}
}
