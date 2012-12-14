package phonenetworksimulator
import phonenetworksimulator.*

abstract class MessagingDevice {
	String phoneNumber
	Boolean isModem = false

	
	static constraints = {
		phoneNumber(blank:false)
	}
	
	//Returns the Messages not Deleted in Inbox
	def getInboxMessages() {
		Message.findAllByDeletedAtDestinationAndRecepient(false,this.phoneNumber)
		
	}
	def getSentMessages() {
		Message.findAllByDeletedAtSourceAndSource(false, this.phoneNumber)
		
	}
		
}
