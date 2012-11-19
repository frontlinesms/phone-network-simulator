<h3>Inbox</h3>
<g:form params="[myPhoneNumber:myPhoneNumber]">
<g:if test="${inboxMessages}">
	<g:each in='${inboxMessages}' var="message">
		<div><g:checkBox name="id" value="${message.id}" checked="false" />Sent from : ${message.source} # ${message.text}</div>
	</g:each>
</g:if>
<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />

</g:form>
