ajaxdependancyselectexample
===========================

Example grails project using [grails ajaxdependancyselection plugin](http://grails.org/plugin/ajaxdependancyselection), [source can be found here](https://github.com/vahidhedayati/ajaxdependancyselection). [Issues can be reported here](https://github.com/vahidhedayati/ajaxdependancyselection/issues)


#### Updated to work with ajaxdependancyselection 0.37


I have added extra options to the main page to give access to other examples and domain classes if new records are required for the test.

All the select examples complete for UK, London and from there on everything has a value, please refer to the BootStrap.groovy where the db is preloaded with giberish
[BootStrap.groovy](https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/conf/BootStrap.groovy)


## General note over naming conventions.
In the examples provided domainClasses has been defined as countryName cityName and so forth, this was really an example to show it can be any naming convention besides the standard id, name column naming convention. Feel free to name things as you desire i.e. this is not a requirement of the plugin.



## Running project
To run this project you will need grails 2.3.4:

from command line: (Linux)

Export JAVA_HOME

export JAVA_HOME=/usr/lib/jvm/java-6-openjdk-i386/


    The java home will probably be different find out where your JDK folder is and do as above..


Whilst attempting to run this with JRE JAVA_HOME path I got the following:

 	grails run-app
	| Compiling 126 source files

	| Compiling 22 source files...
	| Error Error packaging application: Error occurred processing message bundles: Error starting Sun's native2ascii:  (Use --stacktrace to see the full trace)

so please ensure you point it to jdk rather than jre


Unsure but I have multiple grails installed on my desktop so to ensure it is the right version run:

    	alias grails=/home/user//grails-2.3.7/bin/grails
and if you wanted:

	export GRAILS_HOME=/home/user//grails-2.3.7/
	

This is my path to grails 2.3.7 binary file

Download this app and run:

    grails run-app
   


All the examples are here:

[example](https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/example.gsp)

[noRef](https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/norefselection.gsp)

[noRefExtended](https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/norefselectionext.gsp)



Project changes made to make this work:

Added to: [BuildConfig](https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/conf/BuildConfig.groovy)

	// EXTRAS ADDED TO MAKE THIS EXAMPLE PROJECT WORK
    compile ":ajaxdependancyselection:0.37"
   	compile ":jquery-ui:1.10.3"




Added to: [main.gsp](https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/layouts/main.gsp)


    <g:javascript library="jquery-ui"/>
    <g:javascript library="jquery"/>
    

Thats about all that was added, the rest are the domainClasses, (auto generated: controllers,views) and the example gsp's calling the plugin

The jquery-ui should only really be required if you are going to use the auto complete, the g:select stuff does not need it









