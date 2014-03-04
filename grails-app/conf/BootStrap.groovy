
import grails.transaction.Transactional
import ajaxdependancyselectexample.Computers
import ajaxdependancyselectexample.Departments
import ajaxdependancyselectexample.Documents
import ajaxdependancyselectexample.Employee
import ajaxdependancyselectexample.MyCity
import ajaxdependancyselectexample.MyContinent
import ajaxdependancyselectexample.MyCountry
import ajaxdependancyselectexample.MyShops
import ajaxdependancyselectexample.Os
import ajaxdependancyselectexample.Streets
import ajaxdependancyselectexample.Users
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
		
		Documents.findOrSaveWhere(department:d1, name:'Document1.doc')
		Documents.findOrSaveWhere(department:d1, name:'LastYearReport.doc')
		Documents.findOrSaveWhere(department:d2, name:'christmast-report.doc')
		Documents.findOrSaveWhere(department:d2, name:'timsxml.xml')
		Documents.findOrSaveWhere(department:d2, name:'jack.doc')
		
		//  London and Oxford now have the following boroughs
		def dgg1=d1.addToOffices(name:'Reading').save(flush:true)
		
		def dgg2=d1.addToOffices(name:'Hull').save(flush:true)
		
		def dgg3=d2.addToOffices(name:'Kent').save(flush:true)
		
		def dgg4=d2.addToOffices(name:'Wales').save(flush:true)
		
		def ccc1=Computers.findOrSaveWhere(department:d1, pcName:'AXM-tqw')
		Computers.findOrSaveWhere(department:d1, pcName:'BXM-lpt')
		Computers.findOrSaveWhere(department:d1, pcName:'SXM-abf')
		def ccc2=Computers.findOrSaveWhere(department:d2, pcName:'gjb-aabf')
		Computers.findOrSaveWhere(department:d2, pcName:'otr-aaf')
		Computers.findOrSaveWhere(department:d2, pcName:'por-adf')
		
		Os.findOrSaveWhere(computers:ccc1, osName:'Windows')
		Os.findOrSaveWhere(computers:ccc1, osName:'Mac')
		Os.findOrSaveWhere(computers:ccc2, osName:'Redhat')
		Os.findOrSaveWhere(computers:ccc2, osName:'Centos')
				
		Users.findOrSaveWhere(computers:ccc2, userName:'Gill')
		Users.findOrSaveWhere(computers:ccc2, userName:'Kevin')
		Users.findOrSaveWhere(computers:ccc2, userName:'Sam')
		
		Users.findOrSaveWhere(computers:ccc1, userName:'Joe')
		Users.findOrSaveWhere(computers:ccc1, userName:'Mark')
		Users.findOrSaveWhere(computers:ccc1, userName:'Jackie')
		
		
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
	
	

		def sc1 = MyShops.findOrSaveWhere(mycity: cc1, shopName:'Abc')
		def sc2 = MyShops.findOrSaveWhere(mycity: cc1, shopName:'Cat')
		def sc3 = MyShops.findOrSaveWhere(mycity: cc2, shopName:'Gigo')
		def sc4 = MyShops.findOrSaveWhere(mycity: cc2, shopName:'DayLight')
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
