<div id="myphonenumbers">
	<g:each in="${allNumbers}" var="number">
		<g:if test="${myPhoneNumber == number}">
			<div class="chosen">
		</g:if>
		<g:else>
			<div>
		</g:else>
			<g:link controller="message" action="phone" params="${[myPhoneNumber:number]}">${number}</g:link>
		</div>	
	</g:each>
	<g:form controller="phone" action="save" >
		<g:textField name="phoneNumber" value=""/>
		<g:submitButton value="Add Phone" name="addButton"/>
	</g:form>	
</div>
