
<%@ page import="clinicmanager.StockedMedicine" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stockedMedicine.label', default: 'StockedMedicine')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-stockedMedicine" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-stockedMedicine" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="stockedMedicine.medication.label" default="Medication" /></th>
					
						<g:sortableColumn property="quantity" title="${message(code: 'stockedMedicine.quantity.label', default: 'Quantity')}" />
					
						<th><g:message code="stockedMedicine.clinic.label" default="Clinic" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${stockedMedicineInstanceList}" status="i" var="stockedMedicineInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${stockedMedicineInstance.id}">${fieldValue(bean: stockedMedicineInstance, field: "medication")}</g:link></td>
					
						<td>${fieldValue(bean: stockedMedicineInstance, field: "quantity")}</td>
					
						<td>${fieldValue(bean: stockedMedicineInstance, field: "clinic")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${stockedMedicineInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
