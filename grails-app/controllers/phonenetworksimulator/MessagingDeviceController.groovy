package phonenetworksimulator

import org.springframework.dao.DataIntegrityViolationException

class MessagingDeviceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [messagingDeviceInstanceList: MessagingDevice.list(params), messagingDeviceInstanceTotal: MessagingDevice.count()]
    }

    def create() {
        [messagingDeviceInstance: new MessagingDevice(params)]
    }

    def save() {
        def messagingDeviceInstance = new MessagingDevice(params)
        if (!messagingDeviceInstance.save(flush: true)) {
            render(view: "create", model: [messagingDeviceInstance: messagingDeviceInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), messagingDeviceInstance.id])
        redirect(action: "show", id: messagingDeviceInstance.id)
    }

    def show() {
        def messagingDeviceInstance = MessagingDevice.get(params.id)
        if (!messagingDeviceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), params.id])
            redirect(action: "list")
            return
        }

        [messagingDeviceInstance: messagingDeviceInstance]
    }

    def edit() {
        def messagingDeviceInstance = MessagingDevice.get(params.id)
        if (!messagingDeviceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), params.id])
            redirect(action: "list")
            return
        }

        [messagingDeviceInstance: messagingDeviceInstance]
    }

    def update() {
        def messagingDeviceInstance = MessagingDevice.get(params.id)
        if (!messagingDeviceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (messagingDeviceInstance.version > version) {
                messagingDeviceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'messagingDevice.label', default: 'MessagingDevice')] as Object[],
                          "Another user has updated this MessagingDevice while you were editing")
                render(view: "edit", model: [messagingDeviceInstance: messagingDeviceInstance])
                return
            }
        }

        messagingDeviceInstance.properties = params

        if (!messagingDeviceInstance.save(flush: true)) {
            render(view: "edit", model: [messagingDeviceInstance: messagingDeviceInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), messagingDeviceInstance.id])
        redirect(action: "show", id: messagingDeviceInstance.id)
    }

    def delete() {
        def messagingDeviceInstance = MessagingDevice.get(params.id)
        if (!messagingDeviceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), params.id])
            redirect(action: "list")
            return
        }

        try {
            messagingDeviceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'messagingDevice.label', default: 'MessagingDevice'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
