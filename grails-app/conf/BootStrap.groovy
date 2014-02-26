import grails.transaction.Transactional
import ajaxdependancyselectexample.Departments
import ajaxdependancyselectexample.Employee
import ajaxdependancyselectexample.MyCity
import ajaxdependancyselectexample.MyContinent
import ajaxdependancyselectexample.MyCountry
import ajaxdependancyselectexample.Streets
@Transactional
class BootStrap {

    def init = { servletContext ->
		
		def d1=Departments.findOrSaveWhere(name: 'HR')
		def d2=Departments.findOrSaveWhere(name: 'Finance')
		
		Employee.findOrSaveWhere(department:d1, name:'Alison')
		Employee.findOrSaveWhere(department:d1, name:'Sarah')
		Employee.findOrSaveWhere(department:d2, name:'Tim')
		Employee.findOrSaveWhere(department:d2, name:'Dave')
		Employee.findOrSaveWhere(department:d2, name:'Jim')
		
		
		// Create continents 
		def n1=MyContinent.findOrSaveWhere(continentName: 'Asia')
		def n2=MyContinent.findOrSaveWhere(continentName: 'Europe')
		
		// Create countries and provde continent map value as above defs
		def c1 = MyCountry.findOrSaveWhere(mycontinent: n2, countryName:'United Kingdom',ccode:'GB',language:'En')
		def c2 = MyCountry.findOrSaveWhere(mycontinent: n2, countryName:'France',ccode:'FR',language:'Fr')
		def c3 = MyCountry.findOrSaveWhere(mycontinent: n1, countryName:'China',ccode:'CN',language:'Zr')
		def c4 = MyCountry.findOrSaveWhere(mycontinent: n1, countryName:'India',ccode:'IN',language:'Hi')
		
		
		// Create cities bind them to the country map and define some to then set up further relationship 
		MyCity.findOrSaveWhere(mycountry:c1,cityName:'Manchester')
		
		def cc1=MyCity.findOrSaveWhere(mycountry:c1,cityName:'London')
		
		def cc2=MyCity.findOrSaveWhere(mycountry:c1,cityName:'Oxford')
		
		MyCity.findOrSaveWhere(mycountry:c2,cityName:'Paris')
		MyCity.findOrSaveWhere(mycountry:c2,cityName:'Lyon')
		MyCity.findOrSaveWhere(mycountry:c2,cityName:'Nice')
   
		MyCity.findOrSaveWhere(mycountry:c3,cityName:'Beijing')
		MyCity.findOrSaveWhere(mycountry:c3,cityName:'Shanghai')
		MyCity.findOrSaveWhere(mycountry:c3,cityName:'Wuhu')
		
		MyCity.findOrSaveWhere(mycountry:c4,cityName:'Adilabad')
		MyCity.findOrSaveWhere(mycountry:c4,cityName:'Bairgania')
		MyCity.findOrSaveWhere(mycountry:c4,cityName:'Chatra')
		
	   	// This bit preloads DB with the no reference values of Boroughs 
	   	// and binds them to the above defined cities cc1 and cc2 :
	   	//  London and Oxford now have the following boroughs
		def gg1=cc1.addToMyborough(actualName:'Lambeth').save(flush:true)
		
		def gg2=cc1.addToMyborough(actualName:'Camden').save(flush:true)
		
		def gg3=cc2.addToMyborough(actualName:'Banbury').save(flush:true)
		
		def gg4=cc2.addToMyborough(actualName:'Witney').save(flush:true)
	
	
		// Fill Streets where Boroughs and Streets have a map relationship
		
		Streets.findOrSaveWhere(localborough: gg1.myborough.toList()[0], streetName: 'Vauxhall Road')
		Streets.findOrSaveWhere(localborough: gg1.myborough.toList()[0], streetName: 'Wandsworth Road')
		
		Streets.findOrSaveWhere(localborough: gg2.myborough.toList()[1], streetName: 'Fleet Road')
		Streets.findOrSaveWhere(localborough: gg2.myborough.toList()[1], streetName: 'Abbey Road')
		
		Streets.findOrSaveWhere(localborough: gg3.myborough.toList()[0], streetName: 'Warwick Road')
		Streets.findOrSaveWhere(localborough: gg3.myborough.toList()[0], streetName: 'Stratford Road')
		
		Streets.findOrSaveWhere(localborough: gg4.myborough.toList()[1], streetName: 'Langdale Road')
		Streets.findOrSaveWhere(localborough: gg4.myborough.toList()[1], streetName: 'Curbridge Road')

	
		
			
	}
	def destroy = {
	}
}
