package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(MessagingDevice)
@Mock(Message)
class MessagingDeviceSpec extends Specification {

def "Testing Relation" (){
  
  when:
    
    def msgDev = new MessagingDevice(phoneNumber:"+254123232")
    msgDev.save()
    def msg = new Message(recepient:"+25412121",source:+23232323,text:"My text",isRead:true)
    msgDev.addToMessages(msg)
    msgDev.save(flush:true)
    
  then:
  	msgDev.count() == 1
  
}

}