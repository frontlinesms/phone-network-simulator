package phonenetworksimulator

class MessagingDevice {
	String phoneNumber
	static hasMany = [messages: Message]
	
	static constraints = {
		phoneNumber(blank:false)
	}
	
	def getInboxMessages() {
		messages.findAll { it.recepient == this.phoneNumber}
	}
	
	def getSentMessages() {
		messages.findAll { it.source == this.phoneNumber}
	}
		
}
