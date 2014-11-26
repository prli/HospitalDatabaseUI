
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
			<h1>Allowed doctors</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th></th>
						<th></th>
						<th><g:message code="permission.grantee.label" default="Grantee" /></th>
					
						<th><g:message code="permission.patient.label" default="Patient" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${permissionInstanceList}" status="i" var="permissionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<g:form url="[resource:permissionInstance, action:'delete']" method="DELETE">
							<g:hiddenField name="patient" value="${permissionInstance.patient}" />
							<g:hiddenField name="grantee" value="${permissionInstance.grantee}" />
							<fieldset class="buttons">
								<td><g:link class="edit" action="edit" resource="${permissionInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link></td>
								<td><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></td>
							</fieldset>
						</g:form>

						<td>${fieldValue(bean: permissionInstance, field: "grantee.id")}</td>
					
						<td>${fieldValue(bean: permissionInstance, field: "patient.id")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${permissionInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
