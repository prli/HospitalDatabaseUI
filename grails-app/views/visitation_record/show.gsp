
<%@ page import="hospitalui.Visitation_record" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'visitation_record.label', default: 'Visitation_record')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-visitation_record" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-visitation_record" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list visitation_record">
			
				<g:if test="${visitation_recordInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="visitation_record.comments.label" default="Comments" /></span>
					
						<span class="property-value" aria-labelledby="comments-label"><g:fieldValue bean="${visitation_recordInstance}" field="comments"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.costOfVisit}">
				<li class="fieldcontain">
					<span id="costOfVisit-label" class="property-label"><g:message code="visitation_record.costOfVisit.label" default="Cost Of Visit" /></span>
					
						<span class="property-value" aria-labelledby="costOfVisit-label"><g:fieldValue bean="${visitation_recordInstance}" field="costOfVisit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.dateOfVisit}">
				<li class="fieldcontain">
					<span id="dateOfVisit-label" class="property-label"><g:message code="visitation_record.dateOfVisit.label" default="Date Of Visit" /></span>
					
						<span class="property-value" aria-labelledby="dateOfVisit-label"><g:formatDate date="${visitation_recordInstance?.dateOfVisit}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.diagnosis}">
				<li class="fieldcontain">
					<span id="diagnosis-label" class="property-label"><g:message code="visitation_record.diagnosis.label" default="Diagnosis" /></span>
					
						<span class="property-value" aria-labelledby="diagnosis-label"><g:fieldValue bean="${visitation_recordInstance}" field="diagnosis"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.doctor}">
				<li class="fieldcontain">
					<span id="doctor-label" class="property-label"><g:message code="visitation_record.doctor.label" default="Doctor" /></span>
					
						<span class="property-value" aria-labelledby="doctor-label"><g:link controller="doctor" action="show" id="${visitation_recordInstance?.doctor?.id}">${visitation_recordInstance?.doctor?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.lengthOfVisit}">
				<li class="fieldcontain">
					<span id="lengthOfVisit-label" class="property-label"><g:message code="visitation_record.lengthOfVisit.label" default="Length Of Visit" /></span>
					
						<span class="property-value" aria-labelledby="lengthOfVisit-label"><g:fieldValue bean="${visitation_recordInstance}" field="lengthOfVisit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.patient}">
				<li class="fieldcontain">
					<span id="patient-label" class="property-label"><g:message code="visitation_record.patient.label" default="Patient" /></span>
					
						<span class="property-value" aria-labelledby="patient-label"><g:link controller="patient" action="show" id="${visitation_recordInstance?.patient?.id}">${visitation_recordInstance?.patient?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.prescription}">
				<li class="fieldcontain">
					<span id="prescription-label" class="property-label"><g:message code="visitation_record.prescription.label" default="Prescription" /></span>
					
						<span class="property-value" aria-labelledby="prescription-label"><g:fieldValue bean="${visitation_recordInstance}" field="prescription"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.procedures}">
				<li class="fieldcontain">
					<span id="procedures-label" class="property-label"><g:message code="visitation_record.procedures.label" default="Procedures" /></span>
					
						<span class="property-value" aria-labelledby="procedures-label"><g:fieldValue bean="${visitation_recordInstance}" field="procedures"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitation_recordInstance?.scheduleOfTreatment}">
				<li class="fieldcontain">
					<span id="scheduleOfTreatment-label" class="property-label"><g:message code="visitation_record.scheduleOfTreatment.label" default="Schedule Of Treatment" /></span>
					
						<span class="property-value" aria-labelledby="scheduleOfTreatment-label"><g:fieldValue bean="${visitation_recordInstance}" field="scheduleOfTreatment"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:visitation_recordInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${visitation_recordInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
