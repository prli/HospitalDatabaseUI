
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
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="doctorId" title="${message(code: 'staff.doctorId.label', default: 'Doctor Id')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'staff.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'staff.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'staff.password.label', default: 'Password')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${staffInstanceList}" status="i" var="staffInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${staffInstance.id}">${fieldValue(bean: staffInstance, field: "doctorId")}</g:link></td>
					
						<td>${fieldValue(bean: staffInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: staffInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: staffInstance, field: "password")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${staffInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
