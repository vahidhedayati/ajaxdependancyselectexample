package ajaxdependancyselectexample

class MyContinent {
	String continentName
	
    static constraints = {
    }
	
	static hasMany=[mycountry: MyCountry]
	String toString()  { "${continentName}"}
}
