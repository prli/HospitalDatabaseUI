<%@ page import="hospitalui.Permission" %>



<div class="fieldcontain ${hasErrors(bean: permissionInstance, field: 'grantee', 'error')} required">
	<label for="grantee">
		<g:message code="permission.grantee.label" default="Grantee" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="grantee" name="grantee.id" from="${hospitalui.Doctor.list()}" optionKey="id" required="" value="${permissionInstance?.grantee?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: permissionInstance, field: 'patient', 'error')} required">
	<label for="patient">
		<g:message code="permission.patient.label" default="Patient" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="patient" name="patient.id" from="${hospitalui.Patient.list()}" optionKey="id" required="" value="${permissionInstance?.patient?.id}" class="many-to-one"/>

</div>

