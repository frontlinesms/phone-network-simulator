package phonenetworksimulator

class Message {
	String to
	String from
	String text
	Boolean read
	
	static belongsTo = [messagingDevice: MessagingDevice]
	static constraints = {
	}

	
}
