<div>
	<g:link controller="message" action="phone" params="${[myPhoneNumber:myPhoneNumber, section:'inbox']}">Inbox</g:link>
	<g:link controller="message" action="phone" params="${[myPhoneNumber:myPhoneNumber, section:'sent']}">Sent</g:link>
	<g:link controller="message" action="phone" params="${[myPhoneNumber:myPhoneNumber, section:'compose']}">Compose</g:link>
</div>
