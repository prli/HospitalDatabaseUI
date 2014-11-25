
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
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
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
					
						<td><g:link action="show" id="${visitation_recordInstance.id}">${fieldValue(bean: visitation_recordInstance, field: "comments")}</g:link></td>
					
						<td>${fieldValue(bean: visitation_recordInstance, field: "costOfVisit")}</td>
					
						<td><g:formatDate date="${visitation_recordInstance.dateOfVisit}" /></td>
					
						<td>${fieldValue(bean: visitation_recordInstance, field: "diagnosis")}</td>
					
						<td>${fieldValue(bean: visitation_recordInstance, field: "doctor")}</td>
					
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
