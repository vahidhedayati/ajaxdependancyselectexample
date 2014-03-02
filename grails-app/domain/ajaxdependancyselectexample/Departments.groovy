package ajaxdependancyselectexample

class Departments {

	String name
	static hasMany=[employees: Employee, offices: Offices, documents: Documents]
	String toString()  { "${name}"}
	static constraints = {
	}
}
