<%@ page import="ajaxdependancyselectexample.MyCity" %>



<div class="fieldcontain ${hasErrors(bean: myCityInstance, field: 'cityName', 'error')} ">
	<label for="cityName">
		<g:message code="myCity.cityName.label" default="City Name" />
		
	</label>
	<g:textField name="cityName" value="${myCityInstance?.cityName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: myCityInstance, field: 'myborough', 'error')} ">
	<label for="myborough">
		<g:message code="myCity.myborough.label" default="Myborough" />
		
	</label>
	<g:select name="myborough" from="${ajaxdependancyselectexample.MyBorough.list()}" multiple="multiple" optionKey="id" size="5" value="${myCityInstance?.myborough*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: myCityInstance, field: 'mycountry', 'error')} required">
	<label for="mycountry">
		<g:message code="myCity.mycountry.label" default="Mycountry" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mycountry" name="mycountry.id" from="${ajaxdependancyselectexample.MyCountry.list()}" optionKey="id" required="" value="${myCityInstance?.mycountry?.id}" class="many-to-one"/>
</div>

