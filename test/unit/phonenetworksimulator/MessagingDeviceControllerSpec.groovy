package phonenetworksimulator

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MessagingDeviceController)
class MessagingDeviceControllerSpec extends Specification {

	def "test"() {
            
            when:
            controller.index()

            then:
            assertEquals "/message/list", controller.response.redirectedUrl
    }
    def "create action"() {
       setup:
   
       controller.params.phoneNumber = phoneNumber
   

       when:
        def model = controller.create()

       then:
        model.messageInstance != null
        model.messageInstance.phoneNumber == phoneNumber
    

       where:
         phoneNumber = "+254721596767"
   
}


	
}