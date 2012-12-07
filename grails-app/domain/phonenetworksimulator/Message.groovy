package phonenetworksimulator
import phonenetworksimulator.*

class Message {

    String recepient
	String source
	String text
	
	Boolean deletedAtSource = false
	Boolean deletedAtDestination = false
	Boolean isRead=false
	
	static constraints={
		recepient(blank:false)
		source(blank:false)
		text(blank:false)
		
    }
	static mapping = {
		version false
	}


  

	def deleteFromDevice(device) {
	     
      if(device.phoneNumber == this.source) {
            // deleting from sent
			// update the boolean flag
			// use this. cause it is an object of message
			this.deletedAtSource = true 
			println "Deleting FROM SOURCE" + device.phoneNumber
			
		    
		 }
		 
		else if (device.phoneNumber == this.recepient) { // deleting from recipients inbox
			// update the boolean flag
			this.deletedAtDestination = true
			println "Deleting from Destination" + device.phoneNumber
			
			
		}
		
		if (this.deletedAtSource && this.deletedAtDestination){// both boolean flags are true (ie the message has been deleted on both phones)
			println "Deleting the message"
			this.delete() // actually delete this object
		}
		return true
	    
	}
}
