<div>
	<g:link controller="message" action="phone" params="${[phoneNumber:phoneNumber, section:'inbox']}">Inbox</g:link>
	<g:link controller="message" action="phone" params="${[phoneNumber:phoneNumber, section:'sent']}">Sent</g:link>
	<g:link controller="message" action="phone" params="${[phoneNumber:phoneNumber, section:'compose']}">Compose</g:link>
</div>
