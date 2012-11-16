<h3>Inbox</h3>
<g:if test="${inboxMessages}">
	<g:each in='${inboxMessages}' var="message">
		<div>Sent from : ${message.source} # ${message.text}</div>
	</g:each>
</g:if>
