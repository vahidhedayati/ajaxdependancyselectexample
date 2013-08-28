package ajaxdependancyselectexample

class Departments {

	String name
	static hasMany=[employees: Employee]
	String toString()  { "${name}"}
	static constraints = {
	}
}
