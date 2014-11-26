
<%@ page import="hospitalui.Permission" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'permission.label', default: 'Permission')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-permission" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-permission" class="content scaffold-list" role="main">
			<h1>Permissions</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>			
						<th><g:message code="permission.grantee.label" default="Grantee" /></th>
					
						<th><g:message code="permission.patient.label" default="Patient" /></th>
						
						<th>Modify Access</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${permissionInstanceList}" status="i" var="permissionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: permissionInstance, field: "grantee.id")}</td>
						<td>${fieldValue(bean: permissionInstance, field: "patient.id")}</td>
						<g:form url="[resource:permissionInstance, action:'removePermission']" method="DELETE">
							<g:hiddenField name="patientId" value="${permissionInstance.patient.id}" />
							<g:hiddenField name="granteeId" value="${permissionInstance.grantee.id}" />
							<td><g:actionSubmit class="delete" action="removePermission" value="Revoke" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></td>
						</g:form>
					
					</tr>
				</g:each>
				</tbody>
			</table>

		</div>
	</body>
</html>
