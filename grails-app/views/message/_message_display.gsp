<g:form params="[phoneNumber:phoneNumber, section:'message']">
<h3>Message</h3>
<p>${message.text}</p>
<g:link controller="message" action= "back" params="[previousSection:'inbox', phoneNumber:phoneNumber]" >Back</g:link>
</g:form>