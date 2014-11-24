
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
		<div id="list-patient" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
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
			<div class="pagination">
				<g:paginate total="${patientInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
