<%@ page import="ajaxdependancyselectexample.MyContinent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'myContinent.label', default: 'MyContinent')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
	
	
	Output params are: <br>
	
	
	${params}
		<br/>
	
		<g:if test="${continent}">
				Continent name: ${continent}<br/>
		</g:if>
		<g:if test="${country}">
				Country name: ${country} ${country.id}<br/>
		</g:if>
		<g:if test="${city}">
				city name: ${city} ${city.id}<br/>
		</g:if>
	</body>
	</html>
	
