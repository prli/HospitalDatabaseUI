
<%@ page import="hospitalui.Appointment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'appointment.label', default: 'Appointment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-appointment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-appointment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list appointment">
			
				<g:if test="${appointmentInstance?.duration}">
				<li class="fieldcontain">
					<span id="duration-label" class="property-label"><g:message code="appointment.duration.label" default="Duration" /></span>
					
						<span class="property-value" aria-labelledby="duration-label"><g:fieldValue bean="${appointmentInstance}" field="duration"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${appointmentInstance?.patient}">
				<li class="fieldcontain">
					<span id="patient-label" class="property-label"><g:message code="appointment.patient.label" default="Patient" /></span>
					
						<span class="property-value" aria-labelledby="patient-label"><g:link controller="patient" action="show" id="${appointmentInstance?.patient?.id}">${appointmentInstance?.patient?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${appointmentInstance?.startTime}">
				<li class="fieldcontain">
					<span id="startTime-label" class="property-label"><g:message code="appointment.startTime.label" default="Start Time" /></span>
					
						<span class="property-value" aria-labelledby="startTime-label"><g:formatDate date="${appointmentInstance?.startTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${appointmentInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="appointment.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${appointmentInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:appointmentInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${appointmentInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
