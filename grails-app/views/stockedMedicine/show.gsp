<%@ page import="clinicmanager.StockedMedicine" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'stockedMedicine.label', default: 'StockedMedicine')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-stockedMedicine" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                      default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-stockedMedicine" class="content scaffold-show" role="main">
    <h1>${stockedMedicineInstance?.clinic?.title} - ${stockedMedicineInstance?.medication?.title} stock</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list stockedMedicine">

        <g:if test="${stockedMedicineInstance?.medication}">
            <li class="fieldcontain">
                <span id="medication-label" class="property-label"><g:message code="stockedMedicine.medication.label"
                                                                              default="Medication"/></span>

                <span class="property-value" aria-labelledby="medication-label">
                    ${stockedMedicineInstance?.medication?.title.encodeAsHTML()}
                </span>

            </li>
        </g:if>

        <g:if test="${stockedMedicineInstance?.quantity}">
            <li class="fieldcontain">
                <span id="quantity-label" class="property-label"><g:message code="stockedMedicine.quantity.label"
                                                                            default="Quantity"/></span>

                <span class="property-value" aria-labelledby="quantity-label"><g:fieldValue
                        bean="${stockedMedicineInstance}" field="quantity"/></span>

            </li>
        </g:if>

        <g:if test="${stockedMedicineInstance?.clinic}">
            <li class="fieldcontain">
                <span id="clinic-label" class="property-label"><g:message code="stockedMedicine.clinic.label"
                                                                          default="Clinic"/></span>

                <span class="property-value" aria-labelledby="clinic-label">
                    <g:link controller="clinic" action="show" id="${stockedMedicineInstance?.clinic?.id}">
                        ${stockedMedicineInstance?.clinic?.title.encodeAsHTML()}
                    </g:link>
                </span>
            </li>
        </g:if>
    </ol>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${stockedMedicineInstance?.id}"/>
            <g:link class="edit" action="edit" id="${stockedMedicineInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
