<%@ page import="hospitalui.Visitation_record" %>



<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'comments', 'error')} required">
	<label for="comments">
		<g:message code="visitation_record.comments.label" default="Comments" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="comments" required="" value="${visitation_recordInstance?.comments}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'costOfVisit', 'error')} required">
	<label for="costOfVisit">
		<g:message code="visitation_record.costOfVisit.label" default="Cost Of Visit" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="costOfVisit" value="${fieldValue(bean: visitation_recordInstance, field: 'costOfVisit')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'dateOfVisit', 'error')} required">
	<label for="dateOfVisit">
		<g:message code="visitation_record.dateOfVisit.label" default="Date Of Visit" />
		<span class="required-indicator">*</span>
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'diagnosis', 'error')} required">
	<label for="diagnosis">
		<g:message code="visitation_record.diagnosis.label" default="Diagnosis" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="diagnosis" required="" value="${visitation_recordInstance?.diagnosis}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'doctor', 'error')} required">
	<label for="doctor">
		<g:message code="visitation_record.doctor.label" default="Doctor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="doctor" name="doctor.id" from="${hospitalui.Doctor.list()}" optionKey="id" required="" value="${visitation_recordInstance?.doctor?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'lengthOfVisit', 'error')} required">
	<label for="lengthOfVisit">
		<g:message code="visitation_record.lengthOfVisit.label" default="Length Of Visit" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="lengthOfVisit" value="${fieldValue(bean: visitation_recordInstance, field: 'lengthOfVisit')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'patient', 'error')} required">
	<label for="patient">
		<g:message code="visitation_record.patient.label" default="Patient" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="patient" name="patient.id" from="${hospitalui.Patient.list()}" optionKey="id" required="" value="${visitation_recordInstance?.patient?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'prescription', 'error')} required">
	<label for="prescription">
		<g:message code="visitation_record.prescription.label" default="Prescription" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prescription" required="" value="${visitation_recordInstance?.prescription}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'procedures', 'error')} required">
	<label for="procedures">
		<g:message code="visitation_record.procedures.label" default="Procedures" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="procedures" required="" value="${visitation_recordInstance?.procedures}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitation_recordInstance, field: 'scheduleOfTreatment', 'error')} required">
	<label for="scheduleOfTreatment">
		<g:message code="visitation_record.scheduleOfTreatment.label" default="Schedule Of Treatment" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="scheduleOfTreatment" required="" value="${visitation_recordInstance?.scheduleOfTreatment}"/>

</div>

