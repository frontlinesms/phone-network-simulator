package phonenetworksimulator

class Message {
	static constraints={
		recepient(blank:false)
		source(blank:false)
		text(blank:false)
	}

	String recepient
	String source
	String text
	
}
