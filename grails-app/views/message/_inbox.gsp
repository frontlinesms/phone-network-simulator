<h3>Inbox</h3>
<g:form params="[phoneNumber:phoneNumber, section:'inbox']">
<table>
<g:if test="${inboxMessages}">
	<g:each in='${inboxMessages}' var="message">
		<div>
		<g:checkBox name="id" value="${message.id}" checked="false" />
		<g:link class= "${message.isRead ? 'read' : 'unread'}">Sent from : ${message.source} # ${message.text}</g:link>
		
		</div>
	</g:each>
</g:if>
</div>
<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
</table>
</g:form>
