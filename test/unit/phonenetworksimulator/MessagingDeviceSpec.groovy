package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(MessagingDevice)
@Mock(Message)
class MessagingDeviceSpec extends Specification {
	def "Testing Relation of messaging device with messages" (){
	  when:
		def msgDev = new MessagingDevice(phoneNumber:"+254123232").save(failOnError:true)
		def msg = new Message(recepient:"+25412121",source: "+254123232" ,text:"My text").save(failOnError:true)
	  then:
		msgDev.sentMessages.size() == 1
	}
}
