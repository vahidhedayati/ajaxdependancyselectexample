<%@ page import="ajaxdependancyselectexample.Departments" %>



<div class="fieldcontain ${hasErrors(bean: departmentsInstance, field: 'employees', 'error')} ">
	<label for="employees">
		<g:message code="departments.employees.label" default="Employees" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${departmentsInstance?.employees?}" var="e">
    <li><g:link controller="employee" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="employee" action="create" params="['departments.id': departmentsInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'employee.label', default: 'Employee')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: departmentsInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="departments.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${departmentsInstance?.name}"/>
</div>

