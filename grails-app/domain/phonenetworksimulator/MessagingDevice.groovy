package phonenetworksimulator

class MessagingDevice {
	String phoneNumber
	
	static hasMany = [messages: Message]
	
	static constraints = {
	}
}
