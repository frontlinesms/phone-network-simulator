package phonenetworksimulator

import org.springframework.dao.DataIntegrityViolationException

class MessageController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index() {
		redirect(action: "phone", params: params)
	}
	
	def send() {
	
		def messageInstance = new Message(params)
	 	if(messageInstance.save(flush: true, failOnError:true)){
		//Create a new messaging device if its Not among the devices
		[messageInstance.recepient, messageInstance.source].each {
			def phone = MessagingDevice.findByPhoneNumber(it) ?: new MessagingDevice(phoneNumber:it).save(failOnError:true)
		}
		redirect action:'phone', params:[phoneNumber:params.source, section:'sent']
	}
	}
	
	def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [messagingDeviceInstanceList: MessagingDevice.list(params), messagingDeviceInstanceTotal: MessagingDevice.count()]
    }
    
	def phone(){
		if(params.phoneNumber) {
			//this section renders the messages of a phone device depending on the section specified
			def phone = MessagingDevice.findByPhoneNumber(params.phoneNumber)
			return [phoneNumber:phone.phoneNumber, section:params.section ,inboxMessages:phone.inboxMessages, sentMessages:phone.sentMessages, allNumbers:allNumbers()]
		} else {
			return [allNumbers:allNumbers()]		
		}
	}																												

	private def allNumbers(){
		return MessagingDevice.findAll()*.phoneNumber
	} 
	

	def delete() {
		def messageIds = [params.id].flatten().collect{ (it.trim() as Long) }
		println "messageIds value : ${messageIds}"
		println "messageIds are : ${messageIds.getClass().getName()}"
	
		def flashMessages = ""
		def messagesToDelete = (messageIds.collect{ Message.get(it) } - null)
		messagesToDelete.each { messageInstance->

			// render error if no message found
			if (!messageInstance) {
				flashMessages += message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Messages'), messageInstance.id])+ ","
			} else {
				// get the device that we are deleting from
				//specify phoneNumber so as to delete the device by phoneNumbers
				def device = MessagingDevice.findByPhoneNumber(params.phoneNumber)
				
				// invoke deleteFromDevice on domain object, which returns true if successful
				messageInstance.deleteFromDevice(device)
			}
		}
		
		flashMessages = message(code: 'default.deleted.message', args: [messagesToDelete.size() , message(code: 'message.label', default: 'Messages')])
		flash.message = flashMessages
		redirect(action: "phone", params:[phoneNumber:params.phoneNumber, section:params.section])
	}
}


