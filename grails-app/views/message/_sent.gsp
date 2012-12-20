<h3>Sent</h3>
<g:form params="[phoneNumber:phoneNumber, url:url,section:'sent']">
<g:if test="${sentMessages}">
	<g:each in='${sentMessages}' var="message">
		<div><g:checkBox name="id" value="${message.id}" checked="false" />Sent to : ${message.recipient} # ${message.text}</div>
	</g:each>
</g:if>
<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />

</g:form>
