
<%@ page import="clinicmanager.Clinic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'clinic.label', default: 'Clinic')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-clinic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link controller="report" class="list" action="getAllReport">Full stock report</g:link></li>
                <li><g:link controller="report" class="list" action="getLowStockedReport">Low stock report</g:link></li>
			</ul>
		</div>
		<div id="show-clinic" class="content scaffold-show" role="main">
			<h1>${clinicInstance?.title}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            <g:each in="${warnings}">
                <div class="${it.style}" role="status">
                    ${it.message}
                </div>
            </g:each>

            <g:if test="${clinicInstance.country}">
                <ul class="property-list clinic">
                    <li>Country: ${clinicInstance?.country}</li>
                </ul>
            </g:if>


			<ol class="property-list clinic">
				<g:if test="${sortedMedication}">
                        <table>
                            <thead>
                                <tr>
                                    <td>Medication</td>
                                    <td>Quantity</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <g:each in="${sortedMedication}" var="m">
                                <tr>
                                    <td>${m.medication.title?.encodeAsHTML()}</td>
                                    <td>${m.quantity?.encodeAsHTML()}</td>
                                    <td><g:link controller="stockedMedicine" action="edit" params="[id:m.id]">Edit</g:link></td>
                                </tr>
                            </g:each>
                        </table>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${clinicInstance?.id}" />
					<g:link class="edit" action="edit" id="${clinicInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
