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

To run tests and coverage report:

```
./mvnw clean test site -DgenerateReports=false
```

With this command two reports are generated:  
* `/target/site/surefire-report.html` the report about tests
* `/target/site/jacoco/index.html` the report about coverage

The last one will be something like:

<img width="100%" alt="informe-coverage-servicio" src="https://user-images.githubusercontent.com/64134043/81397468-af5bc300-9127-11ea-9398-ad9e08b34583.png">

It assures a **92% of line coverage** and **87% of brach coverage**.
