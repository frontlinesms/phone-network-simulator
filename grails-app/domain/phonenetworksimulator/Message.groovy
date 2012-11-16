package phonenetworksimulator

class Message {
	static constraints={
		recepient(blank:false)
		source(blank:false)
		text(blank:false)
	}

	static mapping = {
		version false
	}

	String recepient
	String source
	String text
	
}
