
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
			<h1>Financial Officer Profile</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div id="FOProfile">
				<table>
				<thead>
						<tr>
							<th>${message(code: 'financial_officer.userId.label', default: 'User Id')}</th>
						
							<th>${message(code: 'financial_officer.firstName.label', default: 'First Name')}</th>
						
							<th>${message(code: 'financial_officer.lastName.label', default: 'Last Name')}</th>
						
							<th>${message(code: 'financial_officer.password.label', default: 'Password')}</th>
						
						</tr>
					</thead>
					<tbody>
						<tr class="">
							<td><g:link action="show" id="${financialOfficerInstance.id}">${financialOfficerInstance.id}</g:link></td>
							<td>${financialOfficerInstance.firstName}</td>
							<td>${financialOfficerInstance.lastName}</td>
							<td>${financialOfficerInstance.password}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- end of FOProfile -->
			<div class="pagination"></div>
			<div id="searchDoctor">
				<h1>Search Doctor</h1>
				<table>
					<g:formRemote name="searchDoctor" url="[controller: 'financial_officer', action:'searchDoctor']">
					    <tr><td><g:field type="text" name="doctorId" placeholder="Search by doctor user id"/></td></tr>
					    <tr><td><g:field type="text" name="firstName" placeholder="Search by doctor first name"/></td></tr>
					    <tr><td><g:field type="text" name="lastName" placeholder="Search by doctor last name"/></td></tr>
					    <tr><td><g:submitButton name="search" value="search" /></td></tr>
					</g:formRemote>
				</table>
			</div>
			<!-- end of searchDoctor -->
			<div id="listOfDoctor">
				<h1>List of Doctors</h1>
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
							<g:each in="${doctorInstanceList}" status="i" var="doctorInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							
								<td><g:link controller="visitation_record" action="index" id="${doctorInstance.id}">${doctorInstance.id}</g:link></td>
								
								<td>${doctorInstance.firstName}</td>
		
								<td>${doctorInstance.lastName}</td>
							
								<td>${doctorInstance.password}</td>
							
								<td>${doctorInstance.revenue}</td>
							
							</tr>
							</g:each>
						</tbody>
					</table>
				<div class="pagination"></div>
			</div>
			<!-- end of listOfDoctor -->
			<div class="pagination"></div>
		</div>
	</body>
</html>
