package phonenetworksimulator

class MessagingDevice {
	String phoneNumber
	
	static constraints = {
	phoneNumber(blank:false)
	}
	
	
	static hasMany = [messages: Message]
	String toString(){
	return "${phoneNumber}"
	}
	
}
