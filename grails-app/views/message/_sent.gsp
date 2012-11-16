<h3>Sent</h3>
<g:if test="${sentMessages}">
	<g:each in='${sentMessages}' var="message">
		<div>Sent to : ${message.recepient} # ${message.text}</div>
	</g:each>
</g:if>
