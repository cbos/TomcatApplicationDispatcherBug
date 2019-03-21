# TomcatApplicationDispatcherBug


Steps to reproduce the bug:

* ./gradlew clean build
* Take war from build/libs folder
* Deploy it on Tomcat
* Visit <host>/tomcat-diagnostics東京/diagnostics
    * Check the contextPath, this is URL encoded
* Visit <host>/tomcat-diagnostics東京/crosscontext/diagnostics
    * Check the contextPath, this is not URL encoded