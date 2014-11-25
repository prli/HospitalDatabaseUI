
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
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="firstName" title="${message(code: 'doctor.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'doctor.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'doctor.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="revenue" title="${message(code: 'doctor.revenue.label', default: 'Revenue')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${doctorInstanceList}" status="i" var="doctorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${doctorInstance.id}">${fieldValue(bean: doctorInstance, field: "firstName")}</g:link></td>
					
						<td>${fieldValue(bean: doctorInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: doctorInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: doctorInstance, field: "revenue")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${doctorInstanceCount ?: 0}" />
			</div>
			<div id="searchPatient">
			<h1>Search Patient</h1>
				<table>
					<g:formRemote name="searchPatient"
					              url="[controller: 'doctor', action:'searchPatient']">
					    <tr><td>OHIP: <input name="ohip" type="text" /></td></tr>
					    <tr><td>SIN: <input name="sin" type="text" /></td></tr>
					    <tr><td><g:submitButton name="search" value="search" /></td></tr>
					</g:formRemote>
				</table>
			</div>
			<div id="listOfPatient">
				<table>
					<thead>
							<tr>
							
								<g:sortableColumn property="ohip" title="${message(code: 'patient.ohip.label', default: 'Ohip')}" />
							
								<g:sortableColumn property="sin" title="${message(code: 'patient.sin.label', default: 'Sin')}" />
							
								<g:sortableColumn property="homePhone" title="${message(code: 'patient.homePhone.label', default: 'Home Phone')}" />
							
								<g:sortableColumn property="healthCondition" title="${message(code: 'patient.healthCondition.label', default: 'Health Condition')}" />
							
								<g:sortableColumn property="lastVisitedDate" title="${message(code: 'patient.lastVisitedDate.label', default: 'Last Visited Date')}" />
							
								<g:sortableColumn property="numOfVisits" title="${message(code: 'patient.numOfVisits.label', default: 'Num Of Visits')}" />
							
							</tr>
					</thead>
					<tbody>
						<g:each in="${patientInstanceList}" status="i" var="patientInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							
								<td><g:link action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "ohip")}</g:link></td>
							
								<td>${fieldValue(bean: patientInstance, field: "sin")}</td>
							
								<td>${fieldValue(bean: patientInstance, field: "homePhone")}</td>
							
								<td>${fieldValue(bean: patientInstance, field: "healthCondition")}</td>
							
								<td><g:formatDate date="${patientInstance.lastVisitedDate}" /></td>
							
								<td>${fieldValue(bean: patientInstance, field: "numOfVisits")}</td>
							
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>
