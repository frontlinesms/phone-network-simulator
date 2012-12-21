package phonenetworksimulator

class Modem extends MessagingDevice{
String url

	def doSend(text,from) {
		def request = url + "?text=${urlEncode(text)}&from=${urlEncode(from)}"
		(new URL(request)).getText()
	
	}

	def urlEncode(text) {
		return java.net.URLEncoder.encode(text)
	}
   
}
