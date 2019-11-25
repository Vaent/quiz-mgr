# quiz-mgr REST service/database API

It is recommended to launch the application from inside an IDE (e.g. IntelliJ) to make it easier to stop the service effectively.

Alternatively, to build and run the service from the command line:

```
mvn clean install
mvn spring-boot:run
```

To stop the service if it is started this way (or if incorrectly closed in the IDE) you will need to locate and kill the task, bearing in mind the port will be blocked until this is done so the service cannot be restarted. Per the solution at https://stackoverflow.com/a/27416379, for Windows run the following commands:

```
netstat -ano | find "8080"
(this will display a result with content like "LISTENING 1234")
taskkill /F /PID 14496
```
