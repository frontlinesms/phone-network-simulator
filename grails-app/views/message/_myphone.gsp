<g:if test="${myPhoneNumber}">
	My number is : ${myPhoneNumber}
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
