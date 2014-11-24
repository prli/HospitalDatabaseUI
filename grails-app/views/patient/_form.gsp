<%@ page import="hospitalui.Patient" %>



<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'userId', 'error')} required">
	<label for="userId">
		<g:message code="patient.userId.label" default="User Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userId" required="" value="${patientInstance?.userId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'ohip', 'error')} required">
	<label for="ohip">
		<g:message code="patient.ohip.label" default="Ohip" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ohip" required="" value="${patientInstance?.ohip}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'sin', 'error')} required">
	<label for="sin">
		<g:message code="patient.sin.label" default="Sin" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sin" required="" value="${patientInstance?.sin}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'homePhone', 'error')} ">
	<label for="homePhone">
		<g:message code="patient.homePhone.label" default="Home Phone" />
		
	</label>
	<g:textField name="homePhone" value="${patientInstance?.homePhone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'healthCondition', 'error')} ">
	<label for="healthCondition">
		<g:message code="patient.healthCondition.label" default="Health Condition" />
		
	</label>
	<g:textField name="healthCondition" value="${patientInstance?.healthCondition}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'lastVisitedDate', 'error')} ">
	<label for="lastVisitedDate">
		<g:message code="patient.lastVisitedDate.label" default="Last Visited Date" />
		
	</label>
	<g:datePicker name="lastVisitedDate" precision="day"  value="${patientInstance?.lastVisitedDate}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'numOfVisits', 'error')} required">
	<label for="numOfVisits">
		<g:message code="patient.numOfVisits.label" default="Num Of Visits" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numOfVisits" type="number" value="${patientInstance.numOfVisits}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'doctor', 'error')} required">
	<label for="doctor">
		<g:message code="patient.doctor.label" default="Doctor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="doctor" name="doctor.id" from="${hospitalui.Doctor.list()}" optionKey="id" required="" value="${patientInstance?.doctor?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="patient.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${patientInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="patient.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${patientInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="patient.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${patientInstance?.password}"/>

</div>

