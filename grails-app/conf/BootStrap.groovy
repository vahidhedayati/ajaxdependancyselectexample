import ajaxdependancyselectexample.Departments
import ajaxdependancyselectexample.Employee
import ajaxdependancyselectexample.MyCity
import ajaxdependancyselectexample.MyContinent
import ajaxdependancyselectexample.MyCountry

class BootStrap {

    def init = { servletContext ->
		
		def d1=Departments.findOrSaveWhere(name: 'HR')
		def d2=Departments.findOrSaveWhere(name: 'Finance')
		
		Employee.findOrSaveWhere(department:d1, name:'Alison')
		Employee.findOrSaveWhere(department:d1, name:'Sarah')
		Employee.findOrSaveWhere(department:d2, name:'Tim')
		Employee.findOrSaveWhere(department:d2, name:'Dave')
		Employee.findOrSaveWhere(department:d2, name:'Jim')
		
		
		
		def n1=MyContinent.findOrSaveWhere(continentName: 'Asia')
		def n2=MyContinent.findOrSaveWhere(continentName: 'Europe')
		
		def c1 = MyCountry.findOrSaveWhere(mycontinent: n2, countryName:'United Kingdom',ccode:'GB',language:'')
		def c2 = MyCountry.findOrSaveWhere(mycontinent: n2, countryName:'France',ccode:'GB',language:'')
		def c3 = MyCountry.findOrSaveWhere(mycontinent: n1, countryName:'China',ccode:'CN',language:'')
		def c4 = MyCountry.findOrSaveWhere(mycontinent: n1, countryName:'India',ccode:'IN',language:'')
		
		MyCity.findOrSaveWhere(mycountry:c1,cityName:'Manchester')
		MyCity.findOrSaveWhere(mycountry:c1,cityName:'London')
		MyCity.findOrSaveWhere(mycountry:c1,cityName:'Oxford')
		
		MyCity.findOrSaveWhere(mycountry:c2,cityName:'Paris')
		MyCity.findOrSaveWhere(mycountry:c2,cityName:'Lyon')
		MyCity.findOrSaveWhere(mycountry:c2,cityName:'Nice')
   
		MyCity.findOrSaveWhere(mycountry:c3,cityName:'Beijing')
		MyCity.findOrSaveWhere(mycountry:c3,cityName:'Shanghai')
		MyCity.findOrSaveWhere(mycountry:c3,cityName:'Wuhu')
		
		MyCity.findOrSaveWhere(mycountry:c4,cityName:'Adilabad')
		MyCity.findOrSaveWhere(mycountry:c4,cityName:'Bairgania')
		MyCity.findOrSaveWhere(mycountry:c4,cityName:'Chatra')
	
			
	}
	def destroy = {
	}
}
