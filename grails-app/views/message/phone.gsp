
<%@ page import="phonenetworksimulator.Message" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<div><h3>My Phone</h3></div>
		<table>
			<tr>
				<td><g:render template="/message/myphonenumbers"/></td>
				<td><g:render template="/message/myphone"/>
</td>		
			</tr>		
		</table>
	</body>
</html>
