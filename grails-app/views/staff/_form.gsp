<%@ page import="hospitalui.Staff" %>



<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'doctorId', 'error')} required">
	<label for="doctorId">
		<g:message code="staff.doctorId.label" default="Doctor Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="doctorId" required="" value="${staffInstance?.doctorId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="staff.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${staffInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="staff.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${staffInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="staff.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${staffInstance?.password}"/>

</div>

