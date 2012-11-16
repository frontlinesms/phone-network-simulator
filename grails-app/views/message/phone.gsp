
<%@ page import="phonenetworksimulator.Message" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div><h3>My Phone</h3></div>
		<table>
			<tr>
				<td><g:render template="/message/myphonenumbers"/></td>
				<td><g:render template="/message/myphone"/></td>		
			</tr>		
		</table>
	</body>
</html>
