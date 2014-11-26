
<%@ page import="hospitalui.Patient" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-patient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="main" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div id="patientProfile">
			<h1>Patient Profile</h1>
				<table>
					<thead>
							<tr>
								<th><g:message code="person.userID.label" default="User Id" /></th>
								<th><g:message code="person.firstName.label" default="First Name" /></th>
								<th><g:message code="person.lastName.label" default="Last Name" /></th>
							</tr>
					</thead>
					<tbody>
					<tr>
						<td><g:link action="show" id="${patientInstance.id}"><g:fieldValue bean="${patientInstance}" field="id" /></g:link></td>
						<td><g:fieldValue bean="${patientInstance}" field="firstName" /></td>
						<td><g:fieldValue bean="${patientInstance}" field="lastName" /></td>
					</tr>
					</tbody>
				</table>
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="ohip" title="${message(code: 'patient.ohip.label', default: 'Ohip')}" />
						
							<g:sortableColumn property="sin" title="${message(code: 'patient.sin.label', default: 'Sin')}" />
						
							<g:sortableColumn property="homePhone" title="${message(code: 'patient.homePhone.label', default: 'Home Phone')}" />
						
							<g:sortableColumn property="healthCondition" title="${message(code: 'patient.healthCondition.label', default: 'Health Condition')}" />
						
							<g:sortableColumn property="lastVisitedDate" title="${message(code: 'patient.lastVisitedDate.label', default: 'Last Visited Date')}" />
						
							<g:sortableColumn property="numOfVisits" title="${message(code: 'patient.numOfVisits.label', default: 'Num Of Visits')}" />
						
							<th><g:message code="patient.doctor.label" default="Doctor" /></th>
	
						</tr>
					</thead>
					<tbody>
						<tr class="">
												
							<td><g:fieldValue bean="${patientInstance}" field="ohip" /></td>
						
							<td><g:fieldValue bean="${patientInstance}" field="sin" /></td>
						
							<td><g:fieldValue bean="${patientInstance}" field="homePhone" /></td>
						
							<td><g:fieldValue bean="${patientInstance}" field="healthCondition" /></td>
						
							<td><g:formatDate date="${patientInstance.lastVisitedDate}" /></td>
						
							<td><g:fieldValue bean="${patientInstance}" field="numOfVisits" /></td>
							
							<td><g:link controller="doctor" action="show" id="${patientInstance.doctor.id}"><g:fieldValue bean="${patientInstance}" field="doctor.id" /></g:link></td>
						
						</tr>
					</tbody>
				</table>
			</div>
			<!-- end of patient profile -->
			<div class="pagination"></div>
			<div id="patientRecords">
			<h1>Patient Records</h1>
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="comments" title="${message(code: 'visitation_record.comments.label', default: 'Comments')}" />
						
							<g:sortableColumn property="costOfVisit" title="${message(code: 'visitation_record.costOfVisit.label', default: 'Cost Of Visit')}" />
						
							<g:sortableColumn property="dateOfVisit" title="${message(code: 'visitation_record.dateOfVisit.label', default: 'Date Of Visit')}" />
						
							<g:sortableColumn property="diagnosis" title="${message(code: 'visitation_record.diagnosis.label', default: 'Diagnosis')}" />
						
							<th><g:message code="visitation_record.doctor.label" default="Doctor" /></th>
						
							<g:sortableColumn property="lengthOfVisit" title="${message(code: 'visitation_record.lengthOfVisit.label', default: 'Length Of Visit')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${visitation_recordInstanceList}" status="i" var="visitation_recordInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td>${fieldValue(bean: visitation_recordInstance, field: "comments")}</td>
						
							<td>${fieldValue(bean: visitation_recordInstance, field: "costOfVisit")}</td>
						
							<td>
								<g:link action="show" params="[patient: "${visitation_recordInstance.patient}", dateOfVisit: "${visitation_recordInstance.dateOfVisit}"]">
									<g:formatDate date="${visitation_recordInstance.dateOfVisit}" />
								</g:link>
							</td>
						
							<td>${fieldValue(bean: visitation_recordInstance, field: "diagnosis")}</td>
						
							<td>${fieldValue(bean: visitation_recordInstance, field: "doctor.id")}</td>
						
							<td>${fieldValue(bean: visitation_recordInstance, field: "lengthOfVisit")}</td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
			</div>
			<!-- end of patientRecords -->
			<div class="pagination"></div>
			<div id="patientAppointments">
				<h1>Patient Appointments</h1>
			</div>
			<!-- end of patientAppointments -->
		</div>
	</body>
</html>
