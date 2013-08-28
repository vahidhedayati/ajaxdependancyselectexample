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
	<form method=post action=example5>

Example1: Dual select condition<br><br>
	
	<g:selectPrimary id="MyContinent1" name="MyContinent1"
        domain='ajaxdependancyselectexample.MyCountry'
        searchField='countryName'
        collectField='id'
        
        domain2='ajaxdependancyselectexample.MyCity'
        bindid="mycountry.id"
        searchField2='cityName'
        collectField2='id'
        
        noSelection="['null': 'Please choose Country']" 
        setId="MyCity1"
        value=''/>

<g:select name="MyCity1" id="MyCity1" optionKey="id" optionValue="cityName" from="[]" noSelection="['null': 'Please choose Country']" />
<input type=submit value=go>  
    </form>
    
    <br><br>
Example 2: Triple Select conditions<br><br>
    
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


    <g:select name="MyCity11" id="MyCity11"  
    optionKey="id" optionValue="name" 
    from="[]" noSelection="['null': 'Please choose Country']" />

    <input type=submit value=go>  
    </form>

  <br><br>
Example 3: Triple auto complete

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
value=''/>

<input type=hidden id="hidden5" name="cityId" value=""/>

<input type=submit value=go> </form>


<br><br>
 Example 4: dual auto complete twice
 <form method=post action=exampel5>
 
 <label>Countries:</label>
 <g:autoCompletePrimary id="primarySearch1121" name="myCountryId"
     domain='ajaxdependancyselectexample.MyCountry'
     searchField='countryName'
     collectField='id'
     hidden='hidden2'
     setId="secondarySearch111"
     value=''/>
<input type=hidden id="hidden2" name="hiddenField" value=""/>

<label>Cities:</label> 
<g:autoCompleteSecondary id="secondarySearch111" 
name="myCityId" 
domain='ajaxdependancyselectexample.MyCity'
 primarybind='mycountry.id' 
 hidden='hidden2' 
 hidden2='hidden5'
  searchField='cityName' 
 collectField='id' value=''/> 
 <input type=hidden id="hidden5" name="cityId" value=""/>

<br><br>
<label>Department:</label> <g:autoCompletePrimary id="primarySearch112" 
name="deparmentName" 
domain='ajaxdependancyselectexample.Departments' 
searchField='name' 
collectField='id' 
setId="secondarySearch22" 
hidden='hidden44' 
value=''/>
 <input type=hidden id="hidden44" name="departmentId" value=""/>

<label>Cities:</label> 
<g:autoCompleteSecondary id="secondarySearch22" 
name="employeeName" 
domain='ajaxdependancyselectexample.Employee' 
primarybind='department.id' 
hidden='hidden44' 
hidden2='hidden55' 
searchField='name' 
collectField='id' value=''/> 
<input type=hidden id="hidden55" name="employeeId" value=""/> <br/><br/>

<input type=submit value=go> </form>




<br><br>
Example 5 : single Auto complete
<form method=post action=example5>
<label>Countries:</label>
<g:autocomplete id="primarySearch" name="myId"
domain='ajaxdependancyselectexample.MyCountry'
searchField='name'
collectField='id'
value=''/>
<input type=submit value=go> </form>


<br><br>
Example 6: single Auto complete showCollection
<form method=post action=example5>
<label>Countries:</label>

<g:autocomplete id="primarySearch2" name="myId2"
action='autocompleteShowCollect'
domain='ajaxdependancyselectexample.MyCountry'
searchField='name'
collectField='id'
value=''/>

<input type=submit value=go> </form>


Example 7: Controller actions

<form method=post action=example5>
<g:selectController id="selectPrimaryTest22" name="mycontrollers"
searchField='name'
collectField='name'
noSelection="['null': 'Please choose Controller']" 
setId="ControllerActions"
value=''/>

<g:select name="myname" id="ControllerActions" 
optionKey="name" optionValue="name" 
from="[]" noSelection="['null': 'Please choose controller']" /> 
<br> <input type=submit value=go> </form>


	</body>
	</html>
	