<h3> Compose Message</h3>
<g:form controller="message" action="send">
	<g:hiddenField name="source" value="${phoneNumber}"/>
	<div class="fieldcontain">
		<g:textField name="recipient"/>
	</div>
	<div class="fieldcontain">
		<g:textArea name="text"/>
	</div>
	<g:submitButton value="Send" name="sendButton" />
</g:form>
