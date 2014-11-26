
<%@ page import="hospitalui.Visitation_record" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'visitation_record.label', default: 'Visitation_record')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-visitation_record" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-visitation_record" class="content scaffold-list" role="main">
			<h1>Visitation Records</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<tr>
					<g:form name="searchByDoctor" url="[controller: 'visitation_record', action:'searchByDoctor']">
						<g:hiddenField name="doctorId" value="${doctorInstance.id}" />
						<th>Start Date</th>
						<th><g:datePicker name="StartDate" value="${new Date()}"
						              precision="day" years="${2005..2105}"/></th>
		                <th>End Date</th>
						<th><g:datePicker name="EndDate" value="${new Date().plus(14)}"
						              precision="day" years="${2005..2105}"/></th>
						<tr><td><g:submitButton name="search" value="search"/></td></tr>
<%--		                <tr><td><g:submitButton name="search" value="search" id="${doctorInstance.id}"/></td></tr>--%>
		            </g:form>
				</tr>
			</table>
			<table>
			<thead>
					<tr>
<%--					<th></th>--%>
						<g:sortableColumn property="patient" title="${message(code: 'visitation_record.patient.label', default: 'Patient')}" />
					
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
<%--						<td><g:link controller="visitation_record" action="show" params="[patientId:"${visitation_recordInstance.patient.id }", dateOfVisit:"${visitation_recordInstance.dateOfVisit }"]">EDIT</g:link></td>--%>
						<td><g:link controller="patient" action="show" id="${visitation_recordInstance.patient.id}">${fieldValue(bean: visitation_recordInstance, field: "patient.id")}</g:link></td>
						<td>${fieldValue(bean: visitation_recordInstance, field: "comments")}</td>
					
						<td>${fieldValue(bean: visitation_recordInstance, field: "costOfVisit")}</td>
					
						<td><g:formatDate date="${visitation_recordInstance.dateOfVisit}" /></td>
					
						<td>${fieldValue(bean: visitation_recordInstance, field: "diagnosis")}</td>
					
						<td>${fieldValue(bean: visitation_recordInstance, field: "doctor.id")}</td>
					
						<td>${fieldValue(bean: visitation_recordInstance, field: "lengthOfVisit")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${visitation_recordInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
