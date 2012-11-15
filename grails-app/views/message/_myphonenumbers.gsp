<div id="myphonenumbers">
	<g:each in="${allNumbers}" var="myPhoneNumber">
		<div>
			<g:link controller="message" action="phone" params="${[myPhoneNumber:myPhoneNumber]}">${myPhoneNumber}</g:link>
		</div>	
	</g:each>
</div>
