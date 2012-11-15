import phonenetworksimulator.*

class BootStrap {

    def init = { servletContext ->
   
   def device = new MessagingDevice(
    
    phoneNumber:"+254721345678")
    
    device.save(failOnError:true, flush:true)
    println "Saved Number"
    if(device.hasErrors()){
    println device.errors
    }
    def number = new Message(
   
    recepient:"+254723456789",
    source:"+254723345678",
    text:"My first Message")
    
    device.addToMessages(number)
    
    device.save(flush:true)
    println "Just Saved Device"
    
    if(number.hasErrors()){
     println number.errors
    }
    
    
   
    }
    
    def destroy = {
    }
}
