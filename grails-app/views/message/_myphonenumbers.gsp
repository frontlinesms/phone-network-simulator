<div id="myphonenumbers">
	<g:each in="${allNumbers}" var="number">
		<g:if test="${phoneNumber == number}">
			<div class="chosen">
		</g:if>
		<g:else>
			<div>
		</g:else>
			<g:link controller="message" action="phone" params="${[phoneNumber:number]}">${number}</g:link>
		</div>	
	</g:each>  
    <p>Create New: <g:radio name="myGroup" value="1"/>Phone
<g:radio name="myGroup" value="2" checked="true"/>Modem </p>
	<g:form controller="phone" action="save" >
		<p>Phone Number:<g:textField name="phoneNumber" value = "${phoneInstance?.phoneNumber}" /></p>
		URL:            <g:textField name="url" />
		<p><g:submitButton value="Add" name="addButton"/></p>
	</g:form>	
</div>
