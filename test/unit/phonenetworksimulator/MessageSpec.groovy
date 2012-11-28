package phonenetworksimulator

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
			true  | ""             | ""                 | ""                | true
	}
	
	
	
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
