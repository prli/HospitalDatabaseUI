<%@ page import="hospitalui.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'userId', 'error')} required">
	<label for="userId">
		<g:message code="person.userId.label" default="User Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userId" required="" value="${personInstance?.userId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="person.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${personInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="person.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${personInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="person.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${personInstance?.password}"/>

</div>

