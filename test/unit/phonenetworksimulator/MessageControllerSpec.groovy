package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MessageController)
@Mock(Message)
class MessageControllerSpec extends Specification{
	
	def "index action redirects to the message list"() {
		when:
			controller.index()

		then:
			controller.response.redirectedUrl == "/message/list"
		}

	def "list action returns a list of messages"() {
		setup:
			def messageInstance = new Message(recepient: "+254721596767", source: "+254721596778", text:"ldmkvnskn").save(flush:true, failOnError:true)
		when:
			def model = controller.list()
		then:
			model.messageInstanceList == [messageInstance]
			model.messageInstanceTotal == 1
	}

	def "sending a message should return the lists of send messages"() {
		setup:
			def m1 = new Message(source:"456789", recepient:"6789", text:"test").save()
			def m2 = new Message(source:"456789", recepient:"6789", text:"test 2").save()
		when:
			controller.params.source = "456789"
			controller.params.recepient = "6789"
			controller.params.text = "bla"
			controller.send()
		then:
			model.sentMessages.size() == 3
			model.myPhoneNumber == "456789"
	}
}