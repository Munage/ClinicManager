<%@ page import="clinicmanager.Clinic" %>



<div class="fieldcontain ${hasErrors(bean: clinicInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="clinic.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${clinicInstance?.title}"/>
</div>


<g:if test="${params.action != 'create'}">
    <div class="fieldcontain ${hasErrors(bean: clinicInstance, field: 'medication', 'error')} ">
        <label for="medication">
            <g:message code="clinic.medication.label" default="Medication" />

        </label>

        <ul class="one-to-many">
            <g:each in="${clinicInstance?.medication?}" var="m">
                <li><g:link controller="stockedMedicine" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
            </g:each>

            <li class="add">
            <g:link controller="stockedMedicine" action="create" params="['clinic.id': clinicInstance?.id]">
                ${message(code: 'default.add.label', args:
                        [message(code: 'stockedMedicine.label', default: 'StockedMedicine')]
                )}</g:link>
            </li>
        </ul>
    </div>
</g:if>
