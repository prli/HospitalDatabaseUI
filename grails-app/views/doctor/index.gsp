
<%@ page import="hospitalui.Doctor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'doctor.label', default: 'Doctor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-doctor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-doctor" class="content scaffold-list" role="main">
			<h1>Doctor Profile</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div id="doctorProfile">
				<table>
				<thead>
						<tr>
							<g:sortableColumn property="id" title="${message(code: 'doctor.userId.label', default: 'User Id')}" />
						
							<g:sortableColumn property="firstName" title="${message(code: 'doctor.firstName.label', default: 'First Name')}" />
						
							<g:sortableColumn property="lastName" title="${message(code: 'doctor.lastName.label', default: 'Last Name')}" />
						
							<g:sortableColumn property="password" title="${message(code: 'doctor.password.label', default: 'Password')}" />
						
							<g:sortableColumn property="revenue" title="${message(code: 'doctor.revenue.label', default: 'Revenue')}" />
						
						</tr>
					</thead>
					<tbody>
						<tr class="odd">
						
							<td><g:link action="show" id="${doctorInstance.id}">${doctorInstance.id}</g:link></td>
							
							<td>${doctorInstance.firstName}</td>
	
							<td>${doctorInstance.lastName}</td>
						
							<td>${doctorInstance.password}</td>
						
							<td>${doctorInstance.revenue}</td>
						
						</tr>
					</tbody>
				</table>
			</div>
			<!-- end of doctorProfile -->
			<div class="pagination"></div>
			<div id="searchPatient">
				<h1>Search Patient</h1>
				<table><tbody>
					<g:formRemote name="searchPatient" url="[controller: 'doctor', action:'searchPatient']">
							<tr><td><g:field type="text" name="ohip" placeholder ="Search by OHIP"/></td></tr>
							<tr><td><g:field type="text" name="sin" placeholder ="Search by SIN"/></td></tr>
					    	<tr><td><g:submitButton name="search" value="search" /></td></tr>
					</g:formRemote>
				</tbody></table>
			</div>
			<!-- end of searchPatient -->
			<div id="listOfPatient">
			<h1>List of Patients</h1>
				<table>
					<thead>
							<tr>
							
								<g:sortableColumn property="ohip" title="${message(code: 'patient.ohip.label', default: 'Ohip')}" />
							
								<g:sortableColumn property="sin" title="${message(code: 'patient.sin.label', default: 'Sin')}" />
							
								<g:sortableColumn property="homePhone" title="${message(code: 'patient.homePhone.label', default: 'Home Phone')}" />
							
								<g:sortableColumn property="healthCondition" title="${message(code: 'patient.healthCondition.label', default: 'Health Condition')}" />
							
								<g:sortableColumn property="lastVisitedDate" title="${message(code: 'patient.lastVisitedDate.label', default: 'Last Visited Date')}" />
							
								<g:sortableColumn property="numOfVisits" title="${message(code: 'patient.numOfVisits.label', default: 'Num Of Visits')}" />
								<th>Manage Access</th>
							</tr>
					</thead>
					<tbody>
						<g:each in="${patientInstanceList}" status="i" var="patientInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							
								<td><g:link controller="patient" action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "ohip")}</g:link></td>
							
								<td>${fieldValue(bean: patientInstance, field: "sin")}</td>
							
								<td>${fieldValue(bean: patientInstance, field: "homePhone")}</td>
							
								<td>${fieldValue(bean: patientInstance, field: "healthCondition")}</td>
							
								<td><g:formatDate date="${patientInstance.lastVisitedDate}" /></td>
							
								<td>${fieldValue(bean: patientInstance, field: "numOfVisits")}</td>
								
								<td>
									<g:formRemote name="modifyAccess" url="[controller: 'permission', action:'index']">
										<g:hiddenField name="patientId" value="${patientInstance.id}" />
										<g:hiddenField name="doctorId" value="${doctorInstance.id}" />
										<g:submitButton name="modifyAccess" value="modify access" />
									</g:formRemote>
								</td>
							
							</tr>
						</g:each>
					</tbody>
				</table>
				<!-- end of listOfPatient -->
				<div class="pagination"></div>
			</div>
		</div>
	</body>
</html>
