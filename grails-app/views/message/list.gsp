
<%@ page import="phonenetworksimulator.Message" %>
<!doctype html>
<html>
	<head> <link rel="stylesheet" href="\${resource(dir: 'css', file: 'scaffold.css')}"/>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-message" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="isRead" title="${message(code: 'message.isRead.label', default: 'Is Read')}" />
					
						<th><g:message code="message.messagingDevice.label" default="Messaging Device" /></th>
					
						<g:sortableColumn property="recepient" title="${message(code: 'message.recepient.label', default: 'Recepient')}" />
					
						<g:sortableColumn property="source" title="${message(code: 'message.source.label', default: 'Source')}" />
					
						<g:sortableColumn property="text" title="${message(code: 'message.text.label', default: 'Text')}" />
					    
					</tr>
					
				</thead>
				<tbody>
				<g:each in="${messageInstanceList}" status="i" var="messageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${messageInstance.id}">${fieldValue(bean: messageInstance, field: "isRead")}</g:link></td>
					
						<td>${fieldValue(bean: messageInstance, field: "messagingDevice")}</td>
					
						<td>${fieldValue(bean: messageInstance, field: "recepient")}</td>
					
						<td>${fieldValue(bean: messageInstance, field: "source")}</td>
					
						<td>${fieldValue(bean: messageInstance, field: "text")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<table>
			<tr>
			
			<div class="pagination">
				<!--g:paginate total="${messageInstanceTotal}" /-->
			</div>
		</div>
	</body>
</html>
