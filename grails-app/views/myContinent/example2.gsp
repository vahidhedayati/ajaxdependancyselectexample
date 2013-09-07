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
	
	
	This is a no reference Auto complete example<br>


<form method=post action=example5>
<label>Continent:</label>
<g:autoCompletePrimary id="primarySearch1"  
name="NAMEOFcontinentName"
domain='ajaxdependancyselectexample.MyContinent'
searchField='continentName'
collectField='id'
setId="secondarySearch2"
hidden='hidden3'
value=''/>

<input type=hidden id="hidden3" name="continentId" value=""/>

<label>Country:</label> 
<g:autoCompleteSecondary id="secondarySearch2" 
name="NAMEOFcountryName" 
domain='ajaxdependancyselectexample.MyCountry' 
primarybind='mycontinent.id' 
hidden='hidden3' 
hidden2='hidden4' 
searchField='countryName' 
collectField='id'
setId="secondarySearch3" 
value=''/>

<input type=hidden id="hidden4" name="countryId" value=""/>

<label>City:</label>
<g:autoCompleteSecondary id="secondarySearch3" 
name="NAMEOFcityName" 
domain='ajaxdependancyselectexample.MyCity' 
primarybind='mycountry.id' 
hidden='hidden4' 
hidden2='hidden5' 
searchField='cityName' 
collectField='id' 
setId="secondarySearch4"
value=''/>

<input type=hidden id="hidden5" name="cityId" value=""/>

<label>Borough:</label>
<g:autoCompleteSecondaryNR id="secondarySearch4" 
name="NAMEOFBorough" 
domain='ajaxdependancyselectexample.MyCity' 
domain2='ajaxdependancyselectexample.MyBorough' 
primarybind='myborough' 
hidden='hidden5' 
hidden2='hidden6' 
searchField='actualName' 
collectField='id' 

value=''/>

<input type=hidden id="hidden6" name="BoroughID" value=""/>

<input type=submit value=go> </form>

	</body>
	</html>
	
