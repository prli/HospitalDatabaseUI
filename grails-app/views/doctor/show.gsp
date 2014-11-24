
<%@ page import="hospitalui.Doctor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'doctor.label', default: 'Doctor')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-doctor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-doctor" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list doctor">
			
				<g:if test="${doctorInstance?.userId}">
				<li class="fieldcontain">
					<span id="userId-label" class="property-label"><g:message code="doctor.userId.label" default="User Id" /></span>
					
						<span class="property-value" aria-labelledby="userId-label"><g:fieldValue bean="${doctorInstance}" field="userId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${doctorInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="doctor.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${doctorInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${doctorInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="doctor.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${doctorInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${doctorInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="doctor.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${doctorInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${doctorInstance?.revenue}">
				<li class="fieldcontain">
					<span id="revenue-label" class="property-label"><g:message code="doctor.revenue.label" default="Revenue" /></span>
					
						<span class="property-value" aria-labelledby="revenue-label"><g:fieldValue bean="${doctorInstance}" field="revenue"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:doctorInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${doctorInstance}" id="${doctorInstance.userId}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="delete" action="delete"id="${doctorInstance.userId}"><g:message code="default.button.delete.label" default="Delete" /></g:link>
					
<%--					<g:link class="edit" action="edit" resource="${doctorInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--%>
<%--					<g:actionSubmit class="delete" action="delete" id="${doctorInstance.userId}" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
