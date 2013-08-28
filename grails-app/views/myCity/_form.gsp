<%@ page import="ajaxdependancyselectexample.MyCity" %>



<div class="fieldcontain ${hasErrors(bean: myCityInstance, field: 'cityName', 'error')} ">
	<label for="cityName">
		<g:message code="myCity.cityName.label" default="City Name" />
		
	</label>
	<g:textField name="cityName" value="${myCityInstance?.cityName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: myCityInstance, field: 'mycountry', 'error')} required">
	<label for="mycountry">
		<g:message code="myCity.mycountry.label" default="Mycountry" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mycountry" name="mycountry.id" from="${ajaxdependancyselectexample.MyCountry.list()}" optionKey="id" required="" value="${myCityInstance?.mycountry?.id}" class="many-to-one"/>
</div>

