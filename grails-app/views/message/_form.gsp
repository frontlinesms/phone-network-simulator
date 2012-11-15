<%@ page import="phonenetworksimulator.Message" %>
<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'recepient', 'error')} ">
	<label for="recepient">
		<g:message code="message.recepient.label" default="Recepient" />
		
	</label>
	<g:textField name="recepient" value="${messageInstance?.recepient}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'source', 'error')} ">
	<label for="source">
		<g:message code="message.source.label" default="Source" />
		
	</label>
	<g:textField name="source" value="${messageInstance?.source}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="message.text.label" default="Text" />
		
	</label>
	<g:textField name="text" value="${messageInstance?.text}"/>
</div>

