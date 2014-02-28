ajaxdependancyselectexample
===========================

example grails project using ajaxdependancyselection plugin


This example grails project uses :

https://github.com/vahidhedayati/ajaxdependancyselection

# Updated to work with ajaxdependancyselection 0.27


I have added extra options to the main page to give access to other examples and domain classes if new records are required for the test.

All the select examples complete for UK, London and from there on everything has a value, please refer to the BootStrap.groovy where the db is preloaded with giberish
https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/conf/BootStrap.groovy



Feel free to reuse as requred..


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

    	alias grails=/home/user/Downloads/springsource/grails-2.3.4/bin/grails
or:

	export GRAILS_HOME=/home/user/Downloads/springsource/grails-2.3.4
	

This is my path to grails 2.3.4 binary file

Download this app and run:

    grails run-app
   




All the examples are here:

https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/example.gsp

https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/norefselection.gsp

https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/norefselectionext.gsp



Project changes made to make this work:

Added to: https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/conf/BuildConfig.groovy

	// EXTRAS ADDED TO MAKE THIS EXAMPLE PROJECT WORK
    compile ":ajaxdependancyselection:0.27"
   	compile ":jquery-ui:1.10.3"




Added to: https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/layouts/main.gsp


    <g:javascript library="jquery-ui"/>
    <g:javascript library="jquery"/>
    

Thats about all that was added, the rest are the domainClasses, (auto generated: controllers,views) and the example gsp's calling the plugin

The jquery-ui should only really be required if you are going to use the auto complete, the g:select stuff does not need it









