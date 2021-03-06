
<%@ page import="phonenetworksimulator.Message" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-message" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list message">
			
						
				<g:if test="${messageInstance?.messagingDevice}">
				<li class="fieldcontain">
					<span id="messagingDevice-label" class="property-label"><g:message code="message.messagingDevice.label" default="Messaging Device" /></span>
					
						<span class="property-value" aria-labelledby="messagingDevice-label"><g:link controller="messagingDevice" action="show" id="${messageInstance?.messagingDevice?.id}">${messageInstance?.messagingDevice?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${messageInstance?.recepient}">
				<li class="fieldcontain">
					<span id="recepient-label" class="property-label"><g:message code="message.recepient.label" default="Recepient" /></span>
					
						<span class="property-value" aria-labelledby="recepient-label"><g:fieldValue bean="${messageInstance}" field="recepient"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${messageInstance?.source}">
				<li class="fieldcontain">
					<span id="source-label" class="property-label"><g:message code="message.source.label" default="Source" /></span>
					
						<span class="property-value" aria-labelledby="source-label"><g:fieldValue bean="${messageInstance}" field="source"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${messageInstance?.text}">
				<li class="fieldcontain">
					<span id="text-label" class="property-label"><g:message code="message.text.label" default="Text" /></span>
					
						<span class="property-value" aria-labelledby="text-label"><g:fieldValue bean="${messageInstance}" field="text"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${messageInstance?.id}" />
					<g:link class="edit" action="edit" id="${messageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
