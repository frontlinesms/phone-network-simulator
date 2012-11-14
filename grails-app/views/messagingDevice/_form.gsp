<%@ page import="phonenetworksimulator.MessagingDevice" %>



<div class="fieldcontain ${hasErrors(bean: messagingDeviceInstance, field: 'messages', 'error')} ">
	<label for="messages">
		<g:message code="messagingDevice.messages.label" default="Messages" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${messagingDeviceInstance?.messages?}" var="m">
    <li><g:link controller="message" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="message" action="create" params="['messagingDevice.id': messagingDeviceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'message.label', default: 'Message')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: messagingDeviceInstance, field: 'phoneNumber', 'error')} ">
	<label for="phoneNumber">
		<g:message code="messagingDevice.phoneNumber.label" default="Phone Number" />
		
	</label>
	<g:textField name="phoneNumber" value="${messagingDeviceInstance?.phoneNumber}"/>
</div>

