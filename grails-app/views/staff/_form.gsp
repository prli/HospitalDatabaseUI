<%@ page import="hospitalui.Staff" %>



<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'userId', 'error')} required">
	<label for="userId">
		<g:message code="staff.userId.label" default="User Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userId" required="" value="${staffInstance?.userId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'doctors', 'error')} ">
	<label for="doctors">
		<g:message code="staff.doctors.label" default="Doctors" />
		
	</label>
	<g:select name="doctors" from="${hospitalui.Doctor.list()}" multiple="multiple" optionKey="id" size="5" value="${staffInstance?.doctors*.id}" class="many-to-many"/>

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

