<%@page import="phonenetworksimulator.*"%>
<div id="myphonenumbers">
	<g:each in="${allDevices}" var="device">
		<div class="${phoneNumber == device.phoneNumber ? 'chosen' : ''}">
			<g:link class="${device instanceof Phone ? 'phone' : 'modem'}" controller="message" action="phone" params="${[phoneNumber:device.phoneNumber]}">${device.phoneNumber} ${device instanceof Modem ? ' : '+device.url : ''}</g:link>
		</div>	
	</g:each>  
<g:form controller="phone" action="save" >
	<script type="text/javascript"> 
	function disableField(){
	   if(document.getElementById('phoneNumber_radio').checked==true){
	   document.getElementById('url').disabled='true';
	   document.getElementById('url').value='Disabled';
	  }
	}
	</script>
	
	 <p>Create New:<g:radio name="deviceType" value="phone" onChange= "disableField()" id = "phoneNumber_radio" checked="true"/>Phone<g:radio name="deviceType" value="modem" onChange = "disableField()" id="url_radio"/>Modem
		<p>Phone Number:<g:textField name="phoneNumber" value = "${phoneInstance?.phoneNumber}" /></p>
		URL:<g:textField name="url" value="${modemInstance?.url}" />
		<p><g:submitButton value="Add" name="addButton"/></p>
	</g:form>	
</div>
