
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
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-appointment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="duration" title="${message(code: 'appointment.duration.label', default: 'Duration')}" />
					
						<th><g:message code="appointment.patient.label" default="Patient" /></th>
					
						<g:sortableColumn property="startTime" title="${message(code: 'appointment.startTime.label', default: 'Start Time')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'appointment.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${appointmentInstanceList}" status="i" var="appointmentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${appointmentInstance.id}">${fieldValue(bean: appointmentInstance, field: "duration")}</g:link></td>
					
						<td>${fieldValue(bean: appointmentInstance, field: "patient")}</td>
					
						<td>${fieldValue(bean: appointmentInstance, field: "startTime")}</td>
					
						<td>${fieldValue(bean: appointmentInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${appointmentInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
