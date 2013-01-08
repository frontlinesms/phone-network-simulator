<div id="myphonenumbers">
	<g:each in="${allNumbers}" var="number">
		<g:if test="${phoneNumber == number}">
			<div class="chosen">
		</g:if>
		<g:else>
			<div>
		</g:else>
			<g:link controller="message" action="device" params="${[phoneNumber:number]}">${number}</g:link>
		</div>	
	</g:each>
	<g:form controller="device" action="save" >
		<g:textField name="phoneNumber" value = "${phoneInstance?.phoneNumber}" />
		<g:submitButton value="Add Device" name="addButton"/>
	</g:form>	
</div>
