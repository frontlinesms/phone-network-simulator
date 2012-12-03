package phonenetworksimulator

import org.springframework.dao.DataIntegrityViolationException

class MessageController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index() {
		redirect(action: "list", params: params)
	}
	
	def send() {
	
		def messageInstance = new Message(params)
		
	 	if(messageInstance.save(flush: true, failOnError:true)){
		//Create a new messaging device if its Not among the devices
		[messageInstance.recepient, messageInstance.source].each {
			def phone = MessagingDevice.findByPhoneNumber(it) ?: new MessagingDevice(phoneNumber:it).save(failOnError:true)
		}
		render view:'/message/phone', model:[phoneNumber:params.source, section:'sent',sentMessages:Message.findAllBySource(params.source), allNumbers:allNumbers()]
	}
	}
	def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [messagingDeviceInstanceList: MessagingDevice.list(params), messagingDeviceInstanceTotal: MessagingDevice.count()]
    }

	def phone(){
		if(params.phoneNumber) {
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
	
	
		def messageInstance = Message.get(params.id)
		
		// render error if no message found
		if (!messageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
			redirect(action: "phone")
		} else {
			// get the device that we are deleting from
			//specify phoneNumber so as to delete the device by phoneNumber
			def device = MessagingDevice.findByPhoneNumber(params.phoneNumber)
			
			// invoke deleteFromDevice on domain object, which returns true if successful
			if (messageInstance.deleteFromDevice(device)) {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), params.id])
				redirect(action: "phone", params:[phoneNumber:params.phoneNumber])
			}
		}
	}
}
