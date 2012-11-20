package phonenetworksimulator

class MessagingDevice {
	String phoneNumber
	
	static constraints = {
		phoneNumber(blank:false)
	}
	
	def getInboxMessages() {
		Message.findAllByRecepient(phoneNumber)
	}
	
	def getSentMessages() {
		Message.findAllBySource(phoneNumber)
	}
		
}
