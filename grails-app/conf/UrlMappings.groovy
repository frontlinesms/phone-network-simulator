class UrlMappings {

	static mappings = {
	
	"/"(controller:"message", action:"phone")
	  "/modem/$phoneNumber/inbox/unread"(controller:'message',action:'phone' ){}
	  "/modem/$source/send/"(controller:'message', action:'send'){}
	  "/modem/$phoneNumber/inbox/read"(controller:'message',action:'readMessage'){}

	"/$controller/$action?/$id?"{

	constraints {
				// apply constraints here
			}
		} 

		
		"500"(view:'/error')
	}
}
