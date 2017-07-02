# CubeAssignment
Steps to run assignment:
1)Run DDL script present in webcontent directory for database setup
2)Create a new tomcat server and add this app in Tomcat. Also WebContent directory present in app, to the classpath of tomcat
3)Startserver
5)http://localhost:8080/CubeAssignment/rest/RuleService/createRule
Run this link in browser to create rule
6)http://localhost:8080/CubeAssignment/rest/ActionDefinitionService/createAction
Run this link in browser to create actions

Above steps complete set up of app.

7)http://localhost:8080/CubeAssignment/rest/EventIngestorService/ingestEvent
Run this link with JSON input of events to it.



Rule framework is made to perform actions on transaction like sending Pushnotifications to user for first bill pay

Action framework is designed for actions which are to be performed after certain period of time, 
like 
1)alerting cube operator if bill is paid but there is no feedback in 15 mins
2)alerting customer if bill pay transaction of more than 20000 are made within 5 mins

