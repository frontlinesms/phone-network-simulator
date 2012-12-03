package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(MessageController)
@Mock(Message)

class MessageControllerSpecif extends Specification {

def "deleting a message actually removes the message from the database"() {
		setup:
			  def m1= new Message(source:"+2323232", recepient:12345, text:"test delete function").save(flush:true, failOnError:true)
			  def m2= new Message(source:"+2542323", recepient:32412, text:"test delete function").save(flush:true, failOnError:true)
		when:
			   controller.params.id = "${m1.id}"
			   controller.delete()
		then:
			Message.count() == 1
			Message.findBySource("+2542323")
			!Message.findBySource("+2323232")
			  

}

}
