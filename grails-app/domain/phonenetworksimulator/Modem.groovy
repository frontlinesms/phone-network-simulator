package phonenetworksimulator

class Modem extends MessagingDevice{
String url

	def doSend(text, from) {
		def request = url + "?text=${text}&from=${from}"
		(new URL(request)).getText()
	}

   
}
