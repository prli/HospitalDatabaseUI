<%@ page import="hospitalui.Financial_officer" %>



<div class="fieldcontain ${hasErrors(bean: financial_officerInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="financial_officer.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${financial_officerInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: financial_officerInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="financial_officer.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${financial_officerInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: financial_officerInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="financial_officer.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" value="${fieldValue(bean: financial_officerInstance, field: 'password')}"/>

</div>

