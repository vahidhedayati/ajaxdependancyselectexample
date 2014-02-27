<%@ page import="ajaxdependancyselectexample.MyContinent" %>
<%@ page import="ajaxdependancyselectexample.MyCountry" %>
<%@ page import="ajaxdependancyselectexample.MyCity" %>


<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'myContinent.label', default: 'MyContinent')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	
		
		
	<body>
	<a href="#create-myContinent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<g:render template="/menubar"></g:render>
		
	This is a no reference Auto complete example<br>


<form method=post action=example5>
<label>Continent:</label>
<g:selectPrimary id="MyContinent2" name="MyContinent2"
    domain="ajaxdependancyselectexample.MyContinent"
    searchField="continentName"
    collectField="id"

    controller="MyContinent"
    action="selectCountries"

    domain2="ajaxdependancyselectexample.MyCountry"
    bindid="mycontinent.id"
    searchField2="countryName"
    appendValue=""
    appendName="values updated"
    collectField2="id"
    noSelection="['': 'Please choose Continent']" 
    setId="MyCountry121"
    value=""/>
    
    
    
 <g:select name="MyCountry" id="MyCountry121"  from="[]" required="required" noSelection="['': 'Please choose Continent']" />
    
    

<input type=submit value=go> </form>

	</body>
	</html>
	
