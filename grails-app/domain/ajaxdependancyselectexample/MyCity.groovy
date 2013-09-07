package ajaxdependancyselectexample

class MyCity {

	String cityName
	MyCountry mycountry
	static hasMany=[myborough: MyBorough]
	String toString()  { "${cityName}"}
}
