package phone.network.simulator

import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Message)
class MessageSpec extends Specification {

	@Unroll
	def "length of text is less than 140"() {
		given:
			def m = new Message(from:from, to:to, text:text,read:read)
		expect:
			m.validate() == valid
		where:
			valid | from           | to                 | text              | read
			true  | "+254722450790"| ""                 | "i love this day" | true
			true  | ""             | "+254723896336"    | "i love this day" | true
			true  | ""             | "+254723896336"    | ""                | true
			true  | "+254723896336"| ""                 | ""                | true
			true  | ""| ""                 | ""                | true
	}
}