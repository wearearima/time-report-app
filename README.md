# time-report-app
App for creating reports of worklogs. Will be used as an example app for testing purposes

**Status**

* Use case: Generate a report for one user and one day (WIP).  
When the user sets a worker's userName and selects a date the application should show the status of worklogs for that user/date.

To run the app

```
 ./mvnw spring-boot:run
```

And go to the [Home page at http://localhost:8080](http://localhost:8080)

In this branch, a test, and the required configuration for reporting, has been added in order to fullfill a minimun of 70% coverage required by the project specifications.

To run all the reports configured:

```
./mvnw clean test site -DgenerateReports=false
```

With this command three reports are generated:  
* `/target/site/surefire-report.html` the report about tests
* `/target/site/jacoco/index.html` the report about coverage
* `/target/site/pit-reports/yyyyMMddhhmm/index.html` the pit test coverage report (where _yyyyMMddhhmm_ indicates the timestamp of the report generation).

The coverage report will show a **100% of line coverage** and **100% of brach coverage**, but a **65% of mutation coverage**
