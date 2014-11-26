
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
				<li><g:link controller="patient" class="create" action="create">New Patient</g:link></li>
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
							<th>${message(code: 'staff.userID.label', default: 'User Id')}</th>
							<th>${message(code: 'staff.firstName.label', default: 'First Name')}</th>			
							<th>${message(code: 'staff.lastName.label', default: 'Last Name')}</th>		
							<th>${message(code: 'staff.password.label', default: 'Password')}</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr class="">
							<g:form url="[resource:staffInstance, action:'editStaff']">
								<g:hiddenField name="staffId" value="${staffInstance.id }"/>
								<td>${fieldValue(bean: staffInstance, field: 'id')}</td>
								<td><g:field type="text" name="firstName" value="${fieldValue(bean: staffInstance, field: 'firstName')}"/></td>
								<td><g:field type="text" name="lastName" value="${fieldValue(bean: staffInstance, field: 'lastName')}"/></td>
								<td><g:field type="password" name="password" value="${fieldValue(bean: staffInstance, field: 'password')}"/></td>
								<td><g:actionSubmit controller="staff" class="edit" action="editStaff" value="Save edit"/></td>
							</g:form>
						</tr>
					</tbody>
				</table>
				<table>
					<g:each in="${staffDoctorListInstance}" status="i" var="doctorInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:link class="delete" action="removeDoctor" params="[doctorId:"${doctorInstance.id}", staffId:"${staffInstance.id }"]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >${message(code: 'default.button.delete.label', default: 'Delete')}</</g:link></td>
<%--							<td><g:link controller="doctor" action="show" id="${doctorInstance.id}">WORKS FOR ${fieldValue(bean: doctorInstance, field: "id")}</g:link></td>--%>
														<td>WORKS FOR ${fieldValue(bean: doctorInstance, field: "id")}</td>
							
							<td><g:link controller="appointment" action="doctorAppointments" params="[doctorId:"${doctorInstance.id}"]">SET APPOINTMENTS FOR ${fieldValue(bean: doctorInstance, field: "id")}</g:link></td>
						</tr>
					</g:each>
						<tr>
							<td>
								<g:form url="[resource:staffInstance, action:'addDoctor']" method="POST">
									<g:hiddenField name="staffId" value="${staffInstance.id }"/>
									<g:select id="doctor" name="doctorId" from="${hospitalui.Doctor.list()}" optionKey="id" required="" value="" class="many-to-one"/>
									<g:actionSubmit controller="staff" class="create" action="addDoctor" value="${message(code: 'default.button.addDoctor.label', default: 'Add doctor')}"/>
								</g:form>
							</td>
						</tr>
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
							<td></td>
						</tr>
					</thead>
					<tbody>
						<g:each in="${patientListInstance}" status="i" var="patientInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:link controller="patient" action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "firstName")}</g:link></td>
							<td><g:link controller="patient" action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "lastName")}</g:link></td>
							<td><g:link controller="visitation_record" action="showForPatient" params ="[patientId: "${patientInstance.id}"]">RECORDS</g:link></td>
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
