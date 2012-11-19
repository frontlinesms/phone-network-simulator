package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Phone)
@Mock(Message)
class PhoneSpec extends Specification {
	def "can contain one or more messages"() {
		when:
			def m1 = new Message(source:"4567", recepient:"567890", text:"")
			def m2 = new Message(source:"4567", recepient:"567890", text:"")
			def phone = new Phone(phoneNumber:"456789").save()
			phone.addToMessages(m1)
			phone.addToMessages(m2)
			phone.save(failOnError:true)
		then:
			phone.messages.size() == 2
			Message.count() == 2
	}
}
