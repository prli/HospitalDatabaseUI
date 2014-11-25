<%@ page import="hospitalui.Doctor" %>

<div class="fieldcontain ${hasErrors(bean: doctorInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="doctor.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${doctorInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: doctorInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="doctor.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${doctorInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: doctorInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="doctor.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${doctorInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: doctorInstance, field: 'revenue', 'error')} required">
	<label for="revenue">
		<g:message code="doctor.revenue.label" default="Revenue" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="revenue" value="${fieldValue(bean: doctorInstance, field: 'revenue')}" required=""/>

</div>

