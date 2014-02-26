<form method=post action=example5>
        <label>Continent:</label>
        <g:autoCompletePrimary id="primarySearch1"  
        name="NAMEOFcontinentName"
        domain='ajaxdependancyselectexample.MyContinent'
        searchField='continentName'
        collectField='id'
        setId="secondarySearch2"
        hidden='hidden3'
        value=''/>

<input type=hidden id="hidden3" name="continentId" value=""/>

<label>Country:</label> <g:autoCompleteSecondary id="secondarySearch2" name="NAMEOFcountryName" domain='ajaxdependancyselectexample.MyCountry' primarybind='mycontinent.id' hidden='hidden3' hidden2='hidden4' searchField='countryName' collectField='id' setId="secondarySearch3" value=''/>

<input type=hidden id="hidden4" name="countryId" value=""/>

<label>City:</label> <g:autoCompleteSecondary id="secondarySearch3" name="NAMEOFcityName" domain='ajaxdependancyselectexample.MyCity' primarybind='mycountry.id' hidden='hidden4' hidden2='hidden5' searchField='cityName' collectField='id' setId="secondarySearch4" value=''/>

<input type=hidden id="hidden5" name="cityId" value=""/>

<label>Borough:</label> <g:autoCompleteSecondaryNR id="secondarySearch4" name="NAMEOFcityName" domain='ajaxdependancyselectexample.MyCity' domain2='ajaxdependancyselectexample.MyBorough' primarybind='myborough' hidden='hidden5' hidden2='hidden6' searchField='actualName' collectField='id'

value=''/>

<input type=hidden id="hidden6" name="BoroughID" value=""/>

<input type=submit value=go> </form>



===========================
Select examples:

<form method=post action=output>
        Zip Code area:
          <g:selectPrimaryNR id="selectZip" name="selectZip"

domain='Postcode' searchField='postcode' collectField='id'

bindid="myarea"

domain2='Area' searchField2='area' collectField2='id'

noSelection="['null': 'Please choose zipcode']" setId="PickupIDbinder" /> 
Pick up area: <g:select name="selectPickup" id="PickupIDbinder" optionKey="id" 
optionValue="name" from="[]" noSelection="['null': 'Please choose zipcode']" /> <br> 
<input type=submit value=go> </form>





