<%@ page import="clinicmanager.Clinic" %>



<div class="fieldcontain ${hasErrors(bean: clinicInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="clinic.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${clinicInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clinicInstance, field: 'country', 'error')}">
    <label for="country">
        <g:message code="clinic.country.label" default="Country" />
    </label>
    <g:textField name="country" value="${clinicInstance?.country}"/>
</div>

<g:if test="${params.action != 'create'}">
    <div class="fieldcontain ${hasErrors(bean: clinicInstance, field: 'medication', 'error')} ">
        <label for="medication">
            <g:message code="clinic.medication.label" default="Medication" />

        </label>

        <ul class="one-to-many"
            <g:each in="${clinicInstance?.medication?}" var="m">
                <li><g:link controller="stockedMedicine" action="show" id="${m.id}">${m?.medication.title.encodeAsHTML()}</g:link></li>
            </g:each>

            <li class="add">
            <g:link controller="stockedMedicine" action="create" params="['clinic.id': clinicInstance?.id]">
               Add new stock
            </g:link>
            </li>
        </ul>
    </div>
</g:if>
