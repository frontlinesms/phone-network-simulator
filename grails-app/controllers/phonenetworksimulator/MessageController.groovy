package phonenetworksimulator

import org.springframework.dao.DataIntegrityViolationException

class MessageController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST", send:"POST"]
	
	def index() {
		redirect(action: "phone", params: params)
	}
	
	def back(){
	  def messageInstance = Message.get(params.id)
	  redirect action:'phone', params:[previousSection:params.previousSection, phoneNumber:params.phoneNumber]
	  
	}
	
	def send() {
		def recipients = params.recipient.split(',').collect { it.trim() }
		recipients.each { recipient ->
		def messageInstance = new Message(source: params.source, recipient:recipient ,text:params.text)
	 	   if(messageInstance.save(flush: true, failOnError:true)){
		   //Create a new messaging device if its Not among the devices
		   [messageInstance.recipient, messageInstance.source].each {
	 
			def phone=MessagingDevice.findByPhoneNumber(it) ?: new Phone(phoneNumber:it).save(failOnError:true) 
		   
			
		 } 
	   }
	 }
	 
		redirect action:'phone', params:[phoneNumber:params.source, section:'sent']
	
	}
	
	def readMessage(){
	   def messageInstance = Message.get(params.messageId)
	   messageInstance?.isRead = true
	   messageInstance?.save()
	   render view:'/message/phone' , model:[message:messageInstance, previousSection:params.previousSection, phoneNumber:params.phoneNumber, section:'message']
	
	
	}
	
	def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [messagingDeviceInstanceList: MessagingDevice.list(params), messagingDeviceInstanceTotal: MessagingDevice.count()]
    }
    
	def phone(){
		    if(params.phoneNumber) {
			//this section renders the messages of a phone device depending on the section specified
			def phone = MessagingDevice.findByPhoneNumber(params.phoneNumber)
			return [phoneNumber:phone.phoneNumber, section:params.section ,inboxMessages:phone.inboxMessages, sentMessages:phone.sentMessages, allDevices:allDevices()]
		 } 
		 
		else if(params.url) {
		def modem= Modem.findByUrl(params.url)
		return[url:modem.url,section:params.section ,inboxMessages:modem.inboxMessages, sentMessages:modem.sentMessages,allDevices:allDevices()]
		 }
		else{
		return[allDevices:allDevices()]
	   }
	}																												

	private def allDevices(){
		return MessagingDevice.getAll();
	} 
	
	
	def delete() {
               
		def messageIds = [params.id].flatten().collect{ (it.trim() as Long) }
		println "messageIds value : ${messageIds}"
		println "messageIds are : ${messageIds.getClass().getName()}"

		def messageInstance
		def flashMessages = ""
		messageIds.each { messageId->
			messageInstance = Message.get(messageId)

			// render error if no message found
			if (!messageInstance) {
				flashMessages += message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), messageInstance.id]) + ","
			} else {
				// get the device that we are deleting from
				//specify phoneNumber so as to delete the device by phoneNumber
				def device = MessagingDevice.findByPhoneNumber(params.phoneNumber)
				
				// invoke deleteFromDevice on domain object, which returns true if successful
				if (messageInstance.deleteFromDevice(device)) {
					flashMessages += message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), messageInstance.id]) + ","
				}
			}
		}
		flash.message = flashMessages
		redirect(action: "phone", params:[phoneNumber:params.phoneNumber, section:params.section])
	}
}
