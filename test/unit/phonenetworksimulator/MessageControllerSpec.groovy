package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MessageController)
class MessageControllerSpec extends Specification{
    
    @IgnoreRest
	def "index action"() {
        when:
        	controller.index()

        then:
        	assertEquals "/message/list", controller.response.redirectedUrl
        }

	def "list action"() {
    	expect:
    		controller.list() == [messageInstanceList: [messageInstance], messageInstanceTotal: 1]
    	where:
    		messageInstance = new Message(to: "+254721596767", from: "+254721596778")
    }

	
   @IgnoreRest
   def "create action"() {
   setup:
   
    controller.params.to = to
    controller.params.from= from

    when:
    def model = controller.create()

    then:
    model.messageInstance != null
    model.messageInstance.to == to
    model.messageInstance.from == from

    where:
    to = "+254721596767"
    from = "+254721596778"
}
//@IgnoreRest
def "save action"() {
def savedMessages
setup:

controller.params.to = "+254721596767"
controller.params.from = "+254721596778"

 
 when:
controller.save()


then:
assertEquals "/message/list", controller.action.redirectedUrl
//redirectArgs.action == "show"
controller.flash.message != null
assertEquals(1, savedMessages.size())



}

def "save action: invalid message"() {
setup:

controller.params.to = to
controller.params.from = from

when:
controller.save()

then:
assertEquals "/message/create", controller.response.redirectedUrl
//renderArgs.view == "create"
//renderArgs.model.authorInstance.to == to
//renderArgs.model.authorInstance.from == from

where:
to = "+254721596767"
from = "+254721596778"

}

def "show action: existing message"() {
setup:

controller.params.id = messageInstance.id

expect:
controller.show() == [messageInstance: messageInstance]


where:
messageInstance = new Message(to: "+254721596767", from: "+254721596778")

}

def "show action: not existing message"() {
setup:

controller.params.id = 1L

when:
controller.show()

then:
redirectArgs.action == "list"
controller.flash.message != null

}


def "edit action: existing message"() {
setup:

controller.params.id = messageInstance.id

expect:
controller.edit() == [messageInstance: messageInstance]


where:
messageInstance = new Message(to: "+254721596767", from: "+254721596778")

}

def "edit action: not existing message"() {
setup:

controller.params.id = 1L

when:
controller.edit()

then:
assertEquals "/message/list", controller.response.redirectedUrl

//redirectArgs.action == "list"
//controller.flash.message != null

}

def "update action: valid message"() {
setup:

controller.params.to = "+254721596767 changed"
controller.params.from = "+254721596778 changed"
controller.params.version = messageInstance.version
controller.params.id = messageInstance.id

when:
controller.update()

then:
redirectArgs.action == "show"
controller.flash.message != null

where:
messageInstance = new Message(to: "+254721596767", from: "+254721596778", version: 1)

}

def "update action: optimistic locking"() {
setup:

controller.params.to = "+254721596767 changed"
controller.params.from = "+254721596778 changed"
//Decrease version of edited object to enforce optimistic locking validation
controller.params.version = (currentVersionInDB -1)
controller.params.id = messageInstance.id

when:
controller.update()

then:
renderArgs.view == "edit"
renderArgs.model.messageInstance == messageInstance
renderArgs.model.messageInstance.hasErrors()

where:
currentVersionInDB = 2
messageInstance = new Message(to: "+254721596767", from: "+254721596778", version: currentVersionInDB)
}





}