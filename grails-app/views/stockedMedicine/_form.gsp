<%@ page import="clinicmanager.StockedMedicine" %>



<div class="fieldcontain ${hasErrors(bean: stockedMedicineInstance, field: 'medication', 'error')} required">
    <label for="medication">
        <g:message code="stockedMedicine.medication.label" default="Medication"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="medication" name="medication.id" from="${clinicmanager.Medication.list()}" optionValue="title" optionKey="id" required=""
              value="${stockedMedicineInstance?.medication?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockedMedicineInstance, field: 'quantity', 'error')} required">
    <label for="quantity">
        <g:message code="stockedMedicine.quantity.label" default="Quantity"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="quantity" type="number" min="0" value="${stockedMedicineInstance.quantity}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockedMedicineInstance, field: 'clinic', 'error')} required">
    <label for="clinic">
        <g:message code="stockedMedicine.clinic.label" default="Clinic"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="clinic" name="clinic.id" from="${clinicmanager.Clinic.list()}" optionValue="title" optionKey="id" required=""
              value="${stockedMedicineInstance?.clinic?.id}" class="many-to-one"/>
</div>

