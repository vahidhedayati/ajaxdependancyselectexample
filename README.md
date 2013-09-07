ajaxdependancyselectexample
===========================

example grails project using ajaxdependancyselection plugin


This example grails project uses :

https://github.com/vahidhedayati/ajaxdependancyselection

# Updated to work with ajaxdependancyselection 0.15


I have added extra options to the main page to give access to other examples and domain classes if new records are required for the test.

All the select examples complete for UK, London and from there on everything has a value, please refer to the BootStrap.groovy where the db is preloaded with giberish
https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/conf/BootStrap.groovy



Feel free to reuse as requred..


To run this project you will need grails 2.2.3:

from command line: (Linux)

Export JAVA_HOME

export JAVA_HOME=/usr/lib/jvm/java-6-openjdk-i386/jre/

    The java home will probably be different find out where your JRE folder is and do as above..

Unsure but I have multiple grails installed on my desktop so to ensure it is the right version run:

    alias grails=/home/user/Downloads/springsource/grails-2.2.3/bin/grails

This is my path to grails 2.2.3 binary file

Download this app and run:

    grails run-app
   




All the examples are here:

https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/example.gsp
https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/norefselection.gsp
https://github.com/vahidhedayati/ajaxdependancyselectexample/blob/master/grails-app/views/myContinent/norefselectionext.gsp



