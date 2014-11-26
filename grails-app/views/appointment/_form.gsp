<%@ page import="hospitalui.Appointment" %>



<div class="fieldcontain ${hasErrors(bean: appointmentInstance, field: 'duration', 'error')} required">
	<label for="duration">
		<g:message code="appointment.duration.label" default="Duration" />
	</label>
	<g:field name="duration" type="number" value="${appointmentInstance?.duration}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: appointmentInstance, field: 'patient', 'error')} required">
	<label for="patient">
		<g:message code="appointment.patient.label" default="Patient" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="patient" name="patient.id" from="${patientInstanceList}" optionKey="id" value="${appointmentInstance?.patient?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: appointmentInstance, field: 'startTime', 'error')} required">
	<label for="startTime">
		<g:message code="appointment.startTime.label" default="Start Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startTime" precision="day"  value="${appointmentInstance?.startTime}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: appointmentInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="appointment.status.label" default="Status" />
	</label>
	<g:textField name="status" value="${appointmentInstance?.status}"/>

</div>

