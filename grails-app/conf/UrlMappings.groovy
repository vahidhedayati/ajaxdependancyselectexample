class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/" (controller:'myContinent', view: 'example')
		//"/"(view:"/index")
		"500"(view:'/error')
	}
}
