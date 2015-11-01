<%@ page import="clinicmanager.Medication" %>



<div class="fieldcontain ${hasErrors(bean: medicationInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="medication.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${medicationInstance?.title}"/>
</div>

