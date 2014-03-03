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

	
	
<h1>Example3 : simple belongsTo example</h1><br><br>

So far all my examples have worked on relationship of

<pre> 
FirstTable hasMany=[secondTable: SecondTable]
SecondTable FirstTable firstable
</pre>
I have set this up to show its the same for belongsTo=[firstable: FirstTable]

so we have:

<pre>

class Table1 {

	String aName
	static hasMany=[tableb: Table2]
    static constraints = {
    }
}


class Table2 {
	String bName
	static belongsTo=[tablea: Table1]
    static constraints = {
    }
}

</pre>

This works identical to the other declaration method:



<form method=post action=example5>
	
	<g:selectPrimary id="Test1" name="Test1"
        domain='ajaxdependancyselectexample.Table1'
        searchField='aName'
        collectField='id'
        
        domain2='ajaxdependancyselectexample.Table2'
        bindid="tablea.id"
        searchField2='bName'
        collectField2='id'

        noSelection="['': 'Please choose test1']" 
        setId="Test2ID"
        value=''/>

<g:select name="test2" id="Test2ID" optionKey="id" optionValue="bName" from="[]" required="required" noSelection="['': 'Please choose test1']" />
<input type=submit value=go>  
    </form>


    
    
  <br><br>
<pre>
&lt;form method=post action=example5&gt;
	
	&lt;g:selectPrimary id="Test1" name="Test1"
        domain='ajaxdependancyselectexample.Table1'
        searchField='aName'
        collectField='id'
        
        domain2='ajaxdependancyselectexample.Table2'
        bindid="tablea.id"
        searchField2='bName'
        collectField2='id'

        noSelection="['': 'Please choose test1']" 
        setId="Test2ID"
        value=''/&gt;

&lt;g:select name="test2" id="Test2ID" optionKey="id" optionValue="bName" from="[]" required="required" noSelection="['': 'Please choose test1']" /&gt;
&lt;input type=submit value=go&gt;  
    &lt;/form&gt;
</pre>

	</body>
	</html>
	
