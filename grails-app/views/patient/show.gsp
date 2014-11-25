
<%@ page import="hospitalui.Patient" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-patient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-patient" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list patient">
			
				<g:if test="${patientInstance?.ohip}">
				<li class="fieldcontain">
					<span id="ohip-label" class="property-label"><g:message code="patient.ohip.label" default="Ohip" /></span>
					
						<span class="property-value" aria-labelledby="ohip-label"><g:fieldValue bean="${patientInstance}" field="ohip"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.sin}">
				<li class="fieldcontain">
					<span id="sin-label" class="property-label"><g:message code="patient.sin.label" default="Sin" /></span>
					
						<span class="property-value" aria-labelledby="sin-label"><g:fieldValue bean="${patientInstance}" field="sin"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.homePhone}">
				<li class="fieldcontain">
					<span id="homePhone-label" class="property-label"><g:message code="patient.homePhone.label" default="Home Phone" /></span>
					
						<span class="property-value" aria-labelledby="homePhone-label"><g:fieldValue bean="${patientInstance}" field="homePhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.healthCondition}">
				<li class="fieldcontain">
					<span id="healthCondition-label" class="property-label"><g:message code="patient.healthCondition.label" default="Health Condition" /></span>
					
						<span class="property-value" aria-labelledby="healthCondition-label"><g:fieldValue bean="${patientInstance}" field="healthCondition"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.lastVisitedDate}">
				<li class="fieldcontain">
					<span id="lastVisitedDate-label" class="property-label"><g:message code="patient.lastVisitedDate.label" default="Last Visited Date" /></span>
					
						<span class="property-value" aria-labelledby="lastVisitedDate-label"><g:formatDate date="${patientInstance?.lastVisitedDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.numOfVisits}">
				<li class="fieldcontain">
					<span id="numOfVisits-label" class="property-label"><g:message code="patient.numOfVisits.label" default="Num Of Visits" /></span>
					
						<span class="property-value" aria-labelledby="numOfVisits-label"><g:fieldValue bean="${patientInstance}" field="numOfVisits"/></span>
					
				</li>
				</g:if>
				<g:if test="${patientInstance?.doctor}">
				<li class="fieldcontain">
					<span id="doctor-label" class="property-label"><g:message code="patient.doctor.label" default="Doctor" /></span>
					
						<span class="property-value" aria-labelledby="doctor-label"><g:link controller="doctor" action="show" id="${patientInstance?.doctor?.id}">${patientInstance?.doctor?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="patient.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${patientInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="patient.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${patientInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="patient.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${patientInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:patientInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${patientInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
