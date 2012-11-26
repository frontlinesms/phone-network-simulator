package phonenetworksimulator

import org.springframework.dao.DataIntegrityViolationException

class MessageController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index() {
		redirect(action: "list", params: params)
	}
	
	def send() {
	
		def messageInstance = new Message(params)
		
		
		//msgDev.addToMessages(message).save(failOnError:true)
	 	messageInstance.save(flush: true, failOnError:true)

		def phone = MessagingDevice.findByPhoneNumber(params.recepient) ?: new MessagingDevice(phoneNumber:params.recepient).save(failOnError:true)
		//render view:'/message/phone', model:[phoneNumber:msgDev*.phoneNumber, inboxMessage:msgDev*.text , sentMessages:msgDev.sentMessages*.text, allNumbers:allNumbers()]
		render view:'/message/phone', model:[phoneNumber:params.source, section:'sent',sentMessages:Message.findAllBySource(params.source), allNumbers:allNumbers()]
	
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
			return [phoneNumber:MessagingDevice.list()[0].phoneNumber, allNumbers:allNumbers()]		
		}
	}																												

	private def allNumbers(){
		return MessagingDevice.findAll()*.phoneNumber
	}    

	def delete() {
		def messageInstance = Message.get(params.id)
		if (!messageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
			redirect(action: "phone")
			return
		}

		try {
			messageInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), params.id])
			redirect(action: "phone", params:[phoneNumber:params.phoneNumber])
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'message.label', default: 'Message'), params.id])
			redirect(action: "phone", params:[phoneNumber:params.phoneNumber])
		}
	}
}
