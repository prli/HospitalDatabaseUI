
<%@ page import="hospitalui.Financial_officer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'financial_officer.label', default: 'Financial_officer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-financial_officer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-financial_officer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="firstName" title="${message(code: 'financial_officer.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'financial_officer.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'financial_officer.password.label', default: 'Password')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${financial_officerInstanceList}" status="i" var="financial_officerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${financial_officerInstance.id}">${fieldValue(bean: financial_officerInstance, field: "firstName")}</g:link></td>
					
						<td>${fieldValue(bean: financial_officerInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: financial_officerInstance, field: "password")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${financial_officerInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
