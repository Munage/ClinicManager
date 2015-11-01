<%@ page import="clinicmanager.StockedMedicine" %>

<div class="fieldcontain ${hasErrors(bean: stockedMedicineInstance, field: 'medication', 'error')} required">
    <label for="medication">
        <g:message code="stockedMedicine.medication.label" default="Medication"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select disabled="true" id="medication" name="medication.id" from="${clinicmanager.Medication.list()}" optionValue="title" optionKey="id" required=""
              value="${stockedMedicineInstance?.medication?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockedMedicineInstance, field: 'quantity', 'error')} required">
    <label for="quantity">
        <g:message code="stockedMedicine.quantity.label" default="Quantity"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="quantity" type="number" min="0" value="${stockedMedicineInstance.quantity}" required=""/>
</div>

<g:field name="clinic.id" type="number" value="${stockedMedicineInstance?.clinic?.id}" style="display:none"/>
