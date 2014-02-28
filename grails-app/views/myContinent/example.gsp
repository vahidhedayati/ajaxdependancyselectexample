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

	
	
<h1>Example1: Dual select condition</h1><br><br>
A simple two tier relationship requires a call to selectPrimary with result returned to [] of MyCity1 normal select box<br><br>


<form method=post action=example5>
	
	<g:selectPrimary id="MyContinent1" name="MyContinent1"
        domain='ajaxdependancyselectexample.MyCountry'
        searchField='countryName'
        collectField='id'
        
        domain2='ajaxdependancyselectexample.MyCity'
        bindid="mycountry.id"
        searchField2='cityName'
        collectField2='id'
        filter='_ON'
        hidden="something"
        noSelection="['': 'Please choose Country']" 
        setId="MyCity1"
        value=''/>

<g:select name="MyCity1" id="MyCity1" optionKey="id" optionValue="cityName" from="[]" required="required" noSelection="['': 'Please choose Country']" />
<input type=submit value=go>  
    </form>

    
    
  <br><br>
<g:textArea readonly="readonly" name="something" id="1" style="width: 90%; padding: 5px; margin: 20px;">
	
	<g:selectPrimary id="MyContinent1" name="MyContinent1"
        domain='ajaxdependancyselectexample.MyCountry'
        searchField='countryName'
        collectField='id'
        
        domain2='ajaxdependancyselectexample.MyCity'
        bindid="mycountry.id"
        searchField2='cityName'
        collectField2='id'
        
        noSelection="['': 'Please choose Country']" 
        setId="MyCity1"
        value=''/>

<g:select name="MyCity1" id="MyCity1" optionKey="id" optionValue="cityName" from="[]" required="required" noSelection="['': 'Please choose Country']" />
<input type=submit value=go>  

</g:textArea>

  
    <br><br>
    
<h1>Example 2: Triple Select conditions</h1><br><br>
    
 A nested relationship of hasMany and fully dependent relationship with belongsTo in 3<br>    
    
 I have added an extra relationship and then enabled filtering of return results on g:selectPrimary and g:selectSecondary<br>
 
 
 So to use this<br>
 
 
 In a primary situation:<br>
 
 <b>
    filter='_ON'<br>
    hidden="hiddenNew"<br>
  </b><br>
  Simply add the two tags above in<br><br> 
  
  To get it working on g:selectSecondary<br><br>
  
  
  	domain='ajaxdependancyselectexample.MyCity'  //Where this is current domainClass
    searchField='cityName'		//Where this is current SearchField for this domainClass
    collectField='id'			// where this is current return value for this domainClass
    filter='_ON'				//Needed to activate tick box
    filterbind='mycountry.id'	// The actual bindid of this table and your previous selection
    hidden="hidden6"			//This hidden field to set value
    prevId="MyCountry11"		//The previousID of your g:selectPrimary or g:selectSecondary - This must exist for all this to work 
    
    
    <form method=post action=example5>
    
    
<g:selectPrimary id="MyContinent2" name="MyContinent2"
    domain='ajaxdependancyselectexample.MyContinent'
    searchField='continentName'
    collectField='id'
    
    domain2='ajaxdependancyselectexample.MyCountry'
    bindid="mycontinent.id"
    searchField2='countryName'
    appendValue=''
    appendName='Updated'
    collectField2='id'
    
    filter='_ON'
    hidden="hiddenNew"
    noSelection="['': 'Please choose Continent']" 
    setId="MyCountry11"
    value="${params.MyContinent2}"/>






<g:selectSecondary id="MyCountry11" name="MyCountry11"
	domain2='ajaxdependancyselectexample.MyCity'
    bindid="mycountry.id"
    searchField2='cityName'
    collectField2='id'
    
    
    domain='ajaxdependancyselectexample.MyCountry'
    searchField='countryName'
    collectField='id'
    filter='_ON'
    filterbind='mycontinent.id'
    hidden="hidden5"
    prevId="MyContinent2"
    
    
     appendValue=''
     appendName='Updated'
    
    
    noSelection="['': 'Please choose Continent']" 
    setId="MyCity11"
    value="${params.MyCountry11}"/>





    <g:selectSecondary name="MyCity11" id="MyCity11"  
    optionKey="id" optionValue="name"
    
    
    domain2='ajaxdependancyselectexample.MyShops'
    bindid="mycity.id"
    searchField2='shopName'
    collectField2='id'
    appendValue=''
    appendName='Updated'
    
    domain='ajaxdependancyselectexample.MyCity'
    searchField='cityName'
    collectField='id'
    filter='_ON'
    filterbind='mycountry.id'
    hidden="hidden6"
    prevId="MyCountry11"
    
    setId="MyShop12"
	noSelection="['': 'Please choose Country']" 
	/>



    <g:select name="MyShop12" id="MyShop12"  
    optionKey="id" optionValue="shopName" 
    from="[]" required="required" noSelection="['': 'Please choose City']" 
    />
    

    <input type=submit value=go>  
    </form>

  <br><br>
<g:textArea readonly="readonly" name="something" id="1" style="width: 90%; padding: 5px; margin: 20px;">

<g:selectPrimary id="MyContinent2" name="MyContinent2"
    domain='ajaxdependancyselectexample.MyContinent'
    searchField='continentName'
    collectField='id'
    
    domain2='ajaxdependancyselectexample.MyCountry'
    bindid="mycontinent.id"
    searchField2='countryName'
    appendValue=''
    appendName='updated'
    collectField2='id'
    noSelection="['': 'Please choose Continent']" 
    setId="MyCountry11"
    value=''
    

    prevId="MyCountry11"
    
    
    />

<g:selectSecondary id="MyCountry11" name="MyCountry11"
	domain2='ajaxdependancyselectexample.MyCity'
    bindid="mycountry.id"
    searchField2='cityName'
    collectField2='id'
    
     appendValue='optional_Additional_Value_'
    appendName='Optional Additional Name'
    
    
    noSelection="['': 'Please choose Continent']" 
    setId="MyCity11"
    value=''/>


    <g:select name="MyCity11" id="MyCity11"  
    optionKey="id" optionValue="name" 
    from="[]" required="required" noSelection="['': 'Please choose Country']" />

    <input type=submit value=go>  

</g:textArea>


    
    

  <br><br>
<h1>Example 3: Triple auto complete</h1>

Same as above but this time in auto complete mode:<br>

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
<g:textArea readonly="readonly" name="something" id="1" style="width: 90%; padding: 5px; margin: 20px;">
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

</g:textArea>

<br><br>

<h1> Example 4: dual auto complete twice</h1>

Ok so we are doing two different components of a form and calling the autComplete twice on one page, the thing with this example is rather daft since all of the above are also on this page haha

<br><br><br>
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
<g:textArea readonly="readonly" name="something" id="1" style="width: 90%; padding: 5px; margin: 20px;">

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
</g:textArea>



<br><br>
<h1>Example 5 : single Auto complete</h1>
Very basic one form field auto complete<br><br>

<form method=post action=example5>
<label>Countries:</label>
<g:autocomplete id="primarySearch" name="myId"
domain='ajaxdependancyselectexample.MyCountry'
searchField='countryName'
collectField='id'
value=''/>
<input type=submit value=go> </form>

<br><br>
<g:textArea readonly="readonly" name="something" id="1" style="width: 90%; padding: 5px; margin: 20px;">

<form method=post action=example5>
<label>Countries:</label>
<g:autocomplete id="primarySearch" name="myId"
domain='ajaxdependancyselectexample.MyCountry'
searchField='countryName'
collectField='id'
value=''/>
<input type=submit value=go> </form>
</g:textArea>

<br><br>
<h1>Example 6: single Auto complete showCollection</h1>
Single Auto complete where optionValue and Name are the same:<br><br>
<form method=post action=example5>
<label>Countries:</label>

<g:autocomplete id="primarySearch2" name="myId2"
action='autocompleteShowCollect'
domain='ajaxdependancyselectexample.MyCountry'
searchField='countryName'
collectField='id'
value=''/>

<input type=submit value=go> </form>


<br><br>
<g:textArea readonly="readonly" name="something" id="1" style="width: 90%; padding: 5px; margin: 20px;">
<form method=post action=example5>
<label>Countries:</label>

<g:autocomplete id="primarySearch2" name="myId2"
action='autocompleteShowCollect'
domain='ajaxdependancyselectexample.MyCountry'
searchField='countryName'
collectField='id'
value=''/>

<input type=submit value=go> </form>
</g:textArea>




<h1>Example 7: Controller actions</h1>
This is an example of interacting with my contollers and their actions using this plugin<br><Br>

<form method=post action=example5>

<div class="fieldcontain ${hasErrors(bean: permissionsInstance, field: 'controllerName', 'error')} ">
	<label for="controllerName">
		<g:message code="permissions.controllerName.label" default="Controller Name" />		
	</label>
	<g:selectController id="controllerName" name="controllerName"
	searchField='name'
	collectField='name'
	noSelection="['*': 'All Controllers']" 
	setId="ControllerActions"
	value="${permissionsInstance?.controllerName}"/>
</div>




<div class="fieldcontain ${hasErrors(bean: permissionsInstance, field: 'controllerAction', 'error')} ">
	<label for="controllerAction">
		<g:message code="permissions.controllerAction.label" default="Controller Action" />
	</label>
	<g:select name="controllerAction" id="ControllerActions" 
	optionKey="name" optionValue="name"  value="${permissionsInstance?.controllerAction}"
	from="[]" noSelection="['*': 'All Controller Actions']" /> 
</div> 
<br> <input type=submit value=go> 

</form>


<br><br>
<g:textArea readonly="readonly" name="something" id="1" style="width: 90%; padding: 5px; margin: 20px;">


<form method=post action=example5>

<div class="fieldcontain ${hasErrors(bean: permissionsInstance, field: 'controllerName', 'error')} ">
	<label for="controllerName">
		<g:message code="permissions.controllerName.label" default="Controller Name" />		
	</label>
	<g:selectController id="controllerName" name="controllerName"
	searchField='name'
	collectField='name'
	noSelection="['*': 'All Controllers']" 
	setId="ControllerActions"
	value="${permissionsInstance?.controllerName}"/>
</div>




<div class="fieldcontain ${hasErrors(bean: permissionsInstance, field: 'controllerAction', 'error')} ">
	<label for="controllerAction">
		<g:message code="permissions.controllerAction.label" default="Controller Action" />
	</label>
	<g:select name="controllerAction" id="ControllerActions" 
	optionKey="name" optionValue="name"  value="${permissionsInstance?.controllerAction}"
	from="[]" noSelection="['*': 'All Controller Actions']" /> 
</div> 
<br> <input type=submit value=go> 

</form>

</g:textArea>
	</body>
	</html>
	
