package phonenetworksimulator

import org.springframework.dao.DataIntegrityViolationException

class MessageController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index() {
		redirect(action: "list", params: params)
	}
	
	def send() {
		def messageInstance = new Message(params)
		if (messageInstance.save(flush: true, failOnError:true)) {
		} else {render view:'/message/phone', model:[myPhoneNumber:params.source, section:'sent' , sentMessages:Message.findAllBySource	(params.source), allNumbers:allNumbers()]

			render text:'failed'
		}    
	}

	def phone(){
		if(params.myPhoneNumber) {
			def phone = Phone.findByPhoneNumber(params.myPhoneNumber)
			return [myPhoneNumber:phone.phoneNumber, section:params.section ,inboxMessages:phone.inboxMessages, sentMessages:phone.sentMessages, allNumbers:allNumbers()]
		} else {
			return [myPhoneNumber:Phone.find().phoneNumber, allNumbers:allNumbers()]		
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
			redirect(action: "phone", params:[myPhoneNumber:params.myPhoneNumber])
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'message.label', default: 'Message'), params.id])
			redirect(action: "phone", params:[myPhoneNumber:params.myPhoneNumber])
		}
	}
}
