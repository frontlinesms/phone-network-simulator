package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(PhoneController)
@Mock(Phone)
class PhoneControllerSpec extends Specification {
	def "can add a new phone device"() {
		when:
			controller.params.phoneNumber = "456789"
			controller.save()
		then:
			Phone.count() == 1
			//* operator collects the phoneNumber property from all the phone devices into a list
			Phone.list()*.phoneNumber == ["456789"]
	}
}
