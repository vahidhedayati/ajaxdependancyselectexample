<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" controller="MyContinent" action="norefselection"><g:message code="no ref" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyContinent" action="norefselectionext"><g:message code="ext no ref" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyContinent" action="example2"><g:message code="no ref auto complete" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyContinent" action="example"><g:message code="main example" args="[entityName]" /></g:link></li>
			
		
			<li><g:link class="list" controller="MyContinent" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyCountry" action="list"><g:message code="country" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyCity" action="list"><g:message code="city" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="MyBorough" action="list"><g:message code="Borough" args="[entityName]" /></g:link></li>
			
			<li><g:link class="list" controller="Streets" action="list"><g:message code="streets" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="Departments" action="list"><g:message code="depts" args="[entityName]" /></g:link></li>
			<li><g:link class="list" controller="Employee" action="list"><g:message code="employee" args="[entityName]" /></g:link></li>	
		</ul>
</div>
