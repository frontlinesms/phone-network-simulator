package phonenetworksimulator

class Message {

static constraints={
    recepient(blank:false)
    source(blank:false)
    text(blank:false)
    isRead()
	}
	String recepient
	String source
	String text
	Boolean isRead
	
	static belongsTo = [messagingDevice: MessagingDevice]
    String toString(){
    return "${recepient}"    
    }	
  
	
}
