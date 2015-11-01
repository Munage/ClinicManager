
<%@ page import="clinicmanager.Clinic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'clinic.label', default: 'Clinic')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-clinic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link controller="report" class="list" action="getAllReport">Full stock report</g:link></li>
                <li><g:link controller="report" class="list" action="getLowStockedReport">Low stock report</g:link></li>
			</ul>
		</div>
		<div id="list-clinic" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            <g:each in="${lowStocks}">
                <g:link controller="clinic" action="show" params="[id:it.id]">
                    <div class="message" role="status">${it.title} has low stock and needs to be attended to</div>
                </g:link>
            </g:each>

			<table>
				<thead>
					<tr>
						<g:sortableColumn property="title" title="${message(code: 'clinic.title.label', default: 'Title')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${clinicInstanceList}" status="i" var="clinicInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${clinicInstance.id}">
                            ${fieldValue(bean: clinicInstance, field: "title")}
                        </g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${clinicInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
