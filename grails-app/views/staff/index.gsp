
<%@ page import="hospitalui.Staff" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'staff.label', default: 'Staff')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-staff" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-staff" class="content scaffold-list" role="main">
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div id="staffProfile">
			<h1>Staff Profile</h1>
				<table>
					<thead>
						<tr>
							<th>${message(code: 'staff.userId.label', default: 'User Id')}</th>
							<th>${message(code: 'staff.firstName.label', default: 'First Name')}</th>			
							<th>${message(code: 'staff.lastName.label', default: 'Last Name')}</th>		
							<th>${message(code: 'staff.password.label', default: 'Password')}</th>
						
						</tr>
					</thead>
					<tbody>
						<tr class="">
							<td><g:link action="show" id="${staffInstance.id}">${fieldValue(bean: staffInstance, field: "id")}</g:link></td>
							<td>${fieldValue(bean: staffInstance, field: "firstName")}</td>
							<td>${fieldValue(bean: staffInstance, field: "lastName")}</td>
							<td>${fieldValue(bean: staffInstance, field: "password")}</td>
						</tr>
					</tbody>
				</table>
				<table>
					<g:each in="${staffDoctorListInstance}" status="i" var="staffInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></td>
						<td><g:link action="show" id="${staffInstance.id}">WORKS FOR ${fieldValue(bean: staffInstance, field: "doctor.id")}</g:link></td>
						<td><g:link controller="appointment" action="doctorAppointments" params="[doctorId:"${staffInstance.doctor.id}"]">SET APPOINTMENTS FOR ${fieldValue(bean: staffInstance, field: "doctor.id")}</g:link></td>
					</tr>
					</g:each>
				</table>
			</div>
			<!-- end of staffProfile -->
			<div class="pagination"></div>
			<div id="listOfPatients">
				<h1>List of patients</h1>
				<table>
					<thead>
						<tr>
							<th>${message(code: 'patient.firstName.label', default: 'First Name')}</th>			
							<th>${message(code: 'patient.lastName.label', default: 'Last Name')}</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${patientListInstance}" status="i" var="patientInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:link action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "firstName")}</g:link></td>
							<td><g:link action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "lastName")}</g:link></td>
						</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<!-- end of listOfPatients -->
			<div class="pagination"></div>
		</div>
	</body>
</html>
