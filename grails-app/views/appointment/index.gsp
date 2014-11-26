
<%@ page import="hospitalui.Appointment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'appointment.label', default: 'Appointment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-appointment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create" params="[doctorId:"${doctorInstance?.id }"]"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-appointment" class="content scaffold-list" role="main">
			<h1>Scheduled Appointments</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div id="scheduledAppointments">
			<table>
			<thead>
					<tr>

						<th><g:message code="appointment.patient.label" default="Patient" /></th>
						
						<g:sortableColumn property="startTime" title="${message(code: 'appointment.startTime.label', default: 'Start Time')}" />
						
						<g:sortableColumn property="duration" title="${message(code: 'appointment.duration.label', default: 'Duration')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'appointment.status.label', default: 'Status')}" />
						
						<th>Modify Appointment</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${futureAppointmentInstanceList}" status="i" var="appointmentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
						<td>${fieldValue(bean: appointmentInstance, field: "patient.id")}</td>
						
						<td><g:formatDate date="${appointmentInstance.startTime}" /></td>
						
						<td>${fieldValue(bean: appointmentInstance, field: "duration")}</td>
					
						<td>${fieldValue(bean: appointmentInstance, field: "status")}</td>
						
						<g:form url="[resource:appointmentInstance, action:'removeAppointment']" method="DELETE">
							<g:hiddenField name="patientId" value="${appointmentInstance.patient.id}" />
							<g:hiddenField name="startTime" value="${appointmentInstance.startTime}" />
							<g:hiddenField name="duration" value="${appointmentInstance.duration}" />
							<g:hiddenField name="status" value="${appointmentInstance.status}" />
							<td><g:actionSubmit class="delete" action="removeAppointment" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" onclick="return confirm('${message(code: 'default.button.cancel.confirm.message', default: 'Are you sure?')}');" /></td>
						</g:form>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<!-- end of futureAppointment -->
			<div class="pagination"></div>
		</div>
	</body>
</html>
