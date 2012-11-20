<div id="myphone">
	<g:if test="${phoneNumber}">
		My number is : ${phoneNumber}
		<g:render template="/message/tabs"/>
		<g:if test="${section == 'sent'}">
			<g:render template="/message/sent"/>	
		</g:if>
		<g:elseif test="${section == 'compose'}">
			<g:render template="/message/compose"/>
		</g:elseif>
		<g:else>
			<g:render template="/message/inbox"/>
		</g:else>
	</g:if>
</div>
