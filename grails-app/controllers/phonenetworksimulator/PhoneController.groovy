package phonenetworksimulator

class PhoneController {
	
	def save() {
		//TODO implement me
		def phoneInstance = new Phone(params)
        if (!phoneInstance.save(flush: true)) {
            render(view: "create", model: [phoneInstance: phoneInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'messagingDevice.label', default: 'Phone'), phoneInstance.id])
        redirect(action: "show", id: phoneInstance.id)
    }

}
