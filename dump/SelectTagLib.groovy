package ajaxdependancyselection


class SelectTagLib {
	def autoCompleteService
	def basicjs='/autoComplete/selectJs'
	static namespace = "new"
	
	def selectPrimary = {attrs ->
		def clazz,name = ""
		
		if (!attrs.id) {
			throwTagError("Tag [selectPrimary] is missing required attribute [id]")
		}
		if (!attrs.domain) {
			throwTagError("Tag [selectPrimary] is missing required attribute [domain]")
		}

		
		if (!attrs.setId) {
			attrs.setId = "selectPrimary"
		}	
		if (!attrs.collectField) {
			attrs.collectField = attrs.searchField
		}
		if (attrs.class) {
			clazz = " class='${attrs.class}'"
		}
		if (attrs.name) {
			name = "${attrs.name}"
		} else {
			name = "${attrs.id}"
		}
		
		if ((attrs.appendValue)&&(!attrs.appendName)) {
			attrs.appendName='Values Updated'
		}
		Boolean requireField=true
		if (attrs.require) {
			requireField=attrs.remove('require')?.toBoolean()
		}
		
		if (attrs.required) {
			requireField=attrs.remove('required')?.toBoolean()
		}
		
		List primarylist
		if (attrs.filter) {
			if (!attrs.filterController) {
				attrs.filterController=attrs.controller
			}
			if (!attrs.filteraction) {
				attrs.filteraction="loadFilterWord"
			}
			if (!attrs.filteraction2) {
				attrs.filteraction2="returnPrimarySearch"
			}	
			def filter=''
			if (!attrs.hidden) {
				attrs.hidden="hidden${attrs.id}"
			}
			if (!attrs.filterDisplay) {
				attrs.filterDisplay='all'
			}
			
			if (attrs.filter.equals('_ON')) {
				def userTemplate=grailsApplication?.config?.ajaxdependancyselection.filterField
				if (userTemplate) {
					out << g.render(template:userTemplate, model: [attrs:attrs])
				}else{
					out << g.render(contextPath: pluginContextPath, template: '/autoComplete/filterField', model: [attrs:attrs])
				}
			}else if (attrs.filter){
				filter=attrs.filter
			}
			primarylist=autoCompleteService.returnPrimarySearch('',filter,attrs.domain, attrs)
		}else{
			primarylist=autoCompleteService.returnPrimaryList(attrs.domain)
			
		}
		
		
		def userTemplate=grailsApplication?.config?.ajaxdependancyselection.selectBasicJS
		if (userTemplate) {
			out << g.render(template:userTemplate, model: [attrs:attrs])
		}else{
			out << g.render(contextPath: pluginContextPath, template: basicjs, model: [attrs:attrs])
		}
		
		def gsattrs=['optionKey' : "${attrs.collectField}" , 'optionValue': "${attrs.searchField}", 'id': "${attrs.id}", 'value': "${attrs.value}", 'name': "${name}",'placeholder':'woot' ]
		gsattrs['from'] = primarylist
		if (requireField) {
			gsattrs['required'] = 'required'
		}
		if (attrs.multiple) {
			gsattrs['multiple']= "${attrs.multiple}"
		}
		
		/*
		if (attrs.value) {
			gsattrs['value'] =attrs.value
		}*/
		
		gsattrs['noSelection'] =attrs.noSelection
		def changeAddon=returnAddon(attrs)
		
		gsattrs['onchange'] = "${attrs.id}Select(escape(this.value))"
		
		if (!attrs.secondaryValue) {
			attrs.secondaryValue=""
		}
		
		/*
		if (attrs.value) {
			out << """
				<script type='text/javascript'>
					${remoteFunction(controller:''+attrs.controller+'', action:''+attrs.action+'', params:'\'id='+attrs.value+'&value='+attrs.secondaryValue+'&setId='+attrs.setId+changeAddon+'&filterController='+attrs.filterController+'&filterDisplay='+attrs.filterDisplay+'&filterType='+attrs.filterType+'&filterType2='+attrs.filterType2+'&filter='+attrs.filter+'&filter2='+attrs.filter2+'&prevId='+attrs.prevId+'&bindid='+ attrs.bindid+'&collectField='+attrs.collectField2+'&searchField='+attrs.searchField2+'&domain2='+attrs.domain2+'&controller='+attrs.controller+'\'',onSuccess:''+attrs.id+'Update(data)')}
				</script>
			"""
		}
		*/
		
		out << g.select(gsattrs)
		
	}
	
	

	
	// selectSecondary is used by both gsp calls to g:selectPrimary and g:selectSecondary
	def selectSecondary = {attrs ->
		def clazz,name = ""
		if (!attrs.id) {
			throwTagError("Tag [selectScondary] is missing required attribute [id]")
		}
		

		if (!attrs.noSelection) {
			throwTagError("Tag [selectScondary] is missing required attribute [noSelection]")
		}
		
		if (!attrs.setId) {
			attrs.setId = "selectSecondary"
		}
		println "------ 0   BIND ID ${attrs.bindValue}"
		if (attrs.bindValue) { 
			println "------ 1  BIND ID ${attrs.bindValue}"
		
		}
		if (!attrs.collectField2) {
			attrs.collectField2 = attrs.searchField2
		}
		if (!attrs.searchField) {
			attrs.searchField = attrs.searchField2
		}
		if (!attrs.collectField) {
			attrs.collectField = attrs.searchField2
		}
		if (attrs.class) {
			clazz = " class='${attrs.class}'"
		}
		if (attrs.name) {
			name = "${attrs.name}"
		} else {
			name = "${attrs.id}"
		}
		Boolean requireField=true
		if (attrs.require) {
			requireField=attrs.remove('require')?.toBoolean()
		}
		
		if (attrs.required) {
			requireField=attrs.remove('required')?.toBoolean()
		}
		
		if ((attrs.appendValue)&&(!attrs.appendName)) {
			attrs.appendName='Values Updated'
		}

		
		List secondarylist=[]
		if (attrs.filter) {
			if (!attrs.filterController) {
				attrs.filterController=attrs.controller
			}
			if (!attrs.filteraction) {
				attrs.filteraction="loadFilterWord2"
			}
			if (!attrs.filteraction2) {
				attrs.filteraction2="secondarySearch"
			}
			if (!attrs.hidden) {
				attrs.hidden="hidden${attrs.id}"
			}
			if (!attrs.filterDisplay) {
				attrs.filterDisplay='all'
			}
			if (!attrs.filterbind) {
				throwTagError("Tag [selectScondary] is missing required attribute [filterbind]")
			}
			if (attrs.filter.equals('_ON')) {
				def userTemplate=grailsApplication?.config?.ajaxdependancyselection.filterField
				if (userTemplate) {
					out << g.render(template:userTemplate, model: [attrs:attrs])
				}else{
					out << g.render(contextPath: pluginContextPath, template: '/autoComplete/filterField', model: [attrs:attrs])
				}
			}	
		}
		
		
		def selectSecondaryJs=grailsApplication?.config?.ajaxdependancyselection.selectSecondaryJsFilter
		if (selectSecondaryJs) {
			out << g.render(template:selectSecondaryJs, model: [attrs:attrs])
		}else{
			basicjs='/autoComplete/selectJs2'
			out << g.render(contextPath: pluginContextPath, template: basicjs, model: [attrs:attrs])
		}
		
		def gsattrs=['optionKey' : "${attrs.collectField}" , 'optionValue': "${attrs.searchField}", 'id': "${attrs.id}", 'value': "${attrs.value}", 'name': "${name}"]
		gsattrs['from'] = secondarylist
		if (requireField) {
			gsattrs['required'] = 'required'
		}
		
		if (attrs.multiple) {
			gsattrs['multiple']= "${attrs.multiple}"
		}
		/*if (attrs.value) {			
			gsattrs['value'] = attrs.value
		}*/
		
		gsattrs['noSelection'] =attrs.noSelection
		def changeAddon=returnAddon(attrs)
		//gsattrs['onchange'] = "${remoteFunction(controller:''+attrs.controller+'', action:''+attrs.action+'', params:'\'id=\' + escape(this.value) +\'&setId='+attrs.setId+changeAddon+'&filterController='+attrs.filterController+'&filterDisplay='+attrs.filterDisplay+'&bindid='+ attrs.bindid+'&collectField='+attrs.collectField2+'&searchField='+attrs.searchField2+'&filterType='+attrs.filterType+'&filterType2='+attrs.filterType2+'&filter='+attrs.filter+'&filter2='+attrs.filter2+'&domain2='+attrs.domain2+'&prevId='+attrs.prevId+'&controller='+attrs.controller+'\'',onSuccess:''+attrs.id+'Update(data)')}"
		
		gsattrs['onclick'] = "${attrs.id}Reload()"
		if (!attrs.secondaryValue) {
			attrs.secondaryValue=""
		}
		
		/*if (attrs.value) {
			out << """
				<script type='text/javascript'>
					${remoteFunction(controller:''+attrs.controller+'', action:''+attrs.action+'', params:'\'id='+attrs.value+'&value='+attrs.secondaryValue+'&setId='+attrs.setId+changeAddon+'&filterController='+attrs.filterController+'&filterDisplay='+attrs.filterDisplay+'&bindid='+ attrs.bindid+'&collectField='+attrs.collectField2+'&searchField='+attrs.searchField2+'&filterType='+attrs.filterType+'&filterType2='+attrs.filterType2+'&filter='+attrs.filter+'&filter2='+attrs.filter2+'&domain2='+attrs.domain2+'&prevId='+attrs.prevId+'&controller='+attrs.controller+'\'',onSuccess:''+attrs.id+'Update(data)')}

				</script>
			"""
		}*/
		
		out <<  g.select(gsattrs)
		
	}

	
	def returnAddon(attrs) {
		def changeAddon=""
		if (attrs.domain3) {
			if (!attrs.bindid3) {
				throwTagError("Tag [selectPrimary] is missing required attribute [bindid3]")
			}
			if (!attrs.setId3) {
				throwTagError("Tag [selectPrimary] is missing required attribute [setId3]")
			}
			changeAddon="&collectField3=${attrs.collectField3}&searchField3=${attrs.searchField3}&domain3=${attrs.domain3}&controller3=${attrs.controller3}&action3=${attrs.action3}&setId3=${attrs.setId3}&bindid3=${attrs.bindid3}"
		}
		if (attrs.domain4) {
			if (!attrs.bindid4) {
				throwTagError("Tag [selectPrimary] is missing required attribute [bindid4]")
			}
			if (!attrs.setId4) {
				throwTagError("Tag [selectPrimary] is missing required attribute [setId4]")
			}
			changeAddon+="&collectField4=${attrs.collectField4}&searchField4=${attrs.searchField4}&domain4=${attrs.domain4}&controller4=${attrs.controller4}&action4=${attrs.action4}&setId4=${attrs.setId4}&bindid4=${attrs.bindid4}"
		}
		if (attrs.domain5) {
			if (!attrs.bindid5) {
				throwTagError("Tag [selectPrimary] is missing required attribute [bindid5]")
			}
			if (!attrs.setId5) {
				throwTagError("Tag [selectPrimary] is missing required attribute [setId5]")
			}
			changeAddon+="&collectField5=${attrs.collectField5}&searchField5=${attrs.searchField5}&domain5=${attrs.domain5}&controller5=${attrs.controller5}&action5=${attrs.action5}&setId5=${attrs.setId5}&bindid5=${attrs.bindid5}"
		}
		if (attrs.domain6) {
			if (!attrs.bindid6) {
				throwTagError("Tag [selectPrimary] is missing required attribute [bindid6]")
			}
			if (!attrs.setId6) {
				throwTagError("Tag [selectPrimary] is missing required attribute [setId6]")
			}
			changeAddon+="&collectField6=${attrs.collectField6}&searchField6=${attrs.searchField6}&domain6=${attrs.domain6}&controller6=${attrs.controller6}&action6=${attrs.action6}&setId6=${attrs.setId6}&bindid6=${attrs.bindid6}"
		}
		if (attrs.domain7) {
			if (!attrs.bindid7) {
				throwTagError("Tag [selectPrimary] is missing required attribute [bindid7]")
			}
			if (!attrs.setId7) {
				throwTagError("Tag [selectPrimary] is missing required attribute [setId7]")
			}
			changeAddon+="&collectField7=${attrs.collectField7}&searchField7=${attrs.searchField7}&domain7=${attrs.domain7}&controller7=${attrs.controller7}&action7=${attrs.action7}&setId7=${attrs.setId7}&bindid7=${attrs.bindid7}"
		}
		if (attrs.domain8) {
			if (!attrs.bindid8) {
				throwTagError("Tag [selectPrimary] is missing required attribute [bindid8]")
			}
			if (!attrs.setId8) {
				throwTagError("Tag [selectPrimary] is missing required attribute [setId8]")
			}
			changeAddon+="&collectField8=${attrs.collectField8}&searchField8=${attrs.searchField8}&domain8=${attrs.domain8}&controller8=${attrs.controller8}&action8=${attrs.action8}&setId8=${attrs.setId8}&bindid8=${attrs.bindid8}"
		}
		return changeAddon
	}
	def autoCompleteHeader = {
		out << "<style>"
		out <<  ".ui-autocomplete-loading"
		out << "        { background: white url(${resource(dir:'images',file:'ajax-loader.gif')}) right center no-repeat   }"
		out << " </style>"
	}
	
}
