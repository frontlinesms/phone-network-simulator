class UrlMappings {

	static mappings = {
	
	"/"(controller:"message", action:"phone")
	"/phone/$phoneNumber"(controller:"message", action:"phone")
	"/phone/$phoneNumber/$section/unread"(controller:'message',action:'phone' ){}
	"/phone/$source/send/"(controller:'message', action:'send'){}
	"/phone/$phoneNumber/inbox/read"(controller:'message',action:'readMessage'){}

	"/$controller/$action?/$id?"{

	constraints {
				// apply constraints here
			}
		} 

		
		"500"(view:'/error')
	}
}
