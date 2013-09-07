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
	<div class="nav" role="navigation">
			<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" controller="MyContinent" action="norefselection"><g:message code="no ref" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyContinent" action="norefselectionext"><g:message code="ext no ref" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyContinent" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyCity" action="list"><g:message code="city" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyCountry" action="list"><g:message code="country" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="Streets" action="list"><g:message code="streets" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="Departments" action="list"><g:message code="depts" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="Employee" action="list"><g:message code="employee" args="[entityName]" /></g:link></li>	
			</ul>
		</div>
    <form method=post action=example5>
<g:selectPrimary id="MyContinent2" name="MyContinent2"
    domain='ajaxdependancyselectexample.MyContinent'
    searchField='continentName'
    collectField='id'
   
    domain2='ajaxdependancyselectexample.MyCountry'
    bindid="mycontinent.id"
    searchField2='countryName'
    collectField2='id'
    noSelection="['null': 'Please choose Continent']"
    setId="MyCountry11"
    value=''/>

<g:selectSecondary id="MyCountry11" name="MyCountry11"
    domain2='ajaxdependancyselectexample.MyCity'
    bindid="mycountry.id"
    searchField2='cityName'
    collectField2='id'
   
    searchField='countryName'
    collectField='id'
    noSelection="['null': 'Please choose Continent']"
    setId="MyCity11"
    value=''/>

<g:selectSecondaryNR id="MyCity11" name="MyCity11"
	domain='ajaxdependancyselectexample.MyCity'
    bindid="myborough"
searchField='cityName'
    collectField='id'
    
    domain2='ajaxdependancyselectexample.MyBorough'
     searchField2='actualName'
    collectField2='id'
    
    
   
  
    noSelection="['null': 'Please choose City']"
    setId="MyBorough11"
    value=''/>


<br>
<g:selectSecondary id="MyBorough11" name="MyBorough11"
    domain2='ajaxdependancyselectexample.Streets'
    bindid="localborough.id"
    searchField2='streetName'
    collectField2='id'
   
    searchField='cityName'
    collectField='id'
    noSelection="['null': 'Please choose Borough']"
    setId="MyRoad22"
    value=''/>
    
     <g:select name="MyRoad22" id="MyRoad22"  
    optionKey="id" optionValue="name" 
    from="[]" noSelection="['null': 'Please choose Borough']" />
    
    
    
   
<br> <input type=submit value=go> </form>


	</body>
	</html>
	