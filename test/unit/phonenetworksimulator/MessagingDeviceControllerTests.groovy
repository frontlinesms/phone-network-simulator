package phonenetworksimulator



import org.junit.*
import grails.test.mixin.*

@TestFor(MessagingDeviceController)
@Mock(MessagingDevice)
class MessagingDeviceControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/messagingDevice/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.messagingDeviceInstanceList.size() == 0
        assert model.messagingDeviceInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.messagingDeviceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.messagingDeviceInstance != null
        assert view == '/messagingDevice/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/messagingDevice/show/1'
        assert controller.flash.message != null
        assert MessagingDevice.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/messagingDevice/list'


        populateValidParams(params)
        def messagingDevice = new MessagingDevice(params)

        assert messagingDevice.save() != null

        params.id = messagingDevice.id

        def model = controller.show()

        assert model.messagingDeviceInstance == messagingDevice
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/messagingDevice/list'


        populateValidParams(params)
        def messagingDevice = new MessagingDevice(params)

        assert messagingDevice.save() != null

        params.id = messagingDevice.id

        def model = controller.edit()

        assert model.messagingDeviceInstance == messagingDevice
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/messagingDevice/list'

        response.reset()


        populateValidParams(params)
        def messagingDevice = new MessagingDevice(params)

        assert messagingDevice.save() != null

        // test invalid parameters in update
        params.id = messagingDevice.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/messagingDevice/edit"
        assert model.messagingDeviceInstance != null

        messagingDevice.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/messagingDevice/show/$messagingDevice.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        messagingDevice.clearErrors()

        populateValidParams(params)
        params.id = messagingDevice.id
        params.version = -1
        controller.update()

        assert view == "/messagingDevice/edit"
        assert model.messagingDeviceInstance != null
        assert model.messagingDeviceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/messagingDevice/list'

        response.reset()

        populateValidParams(params)
        def messagingDevice = new MessagingDevice(params)

        assert messagingDevice.save() != null
        assert MessagingDevice.count() == 1

        params.id = messagingDevice.id

        controller.delete()

        assert MessagingDevice.count() == 0
        assert MessagingDevice.get(messagingDevice.id) == null
        assert response.redirectedUrl == '/messagingDevice/list'
    }
}
