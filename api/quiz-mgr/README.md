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

## Database access

A file named `db_access.properties` must be present on the classpath (recommended location is under `src/main/resources`) and must contain the following attributes:
```
dbUrl=<url>
dbUserName=<username>
dbPassword=<password>
```

Example:
```
dbUrl=jdbc:postgresql://localhost:5432/quizzes
dbUserName=dbUser
dbPassword=s3cr3t
```

## Routes

By default the service runs locally on `http://localhost:8080` and pages can be viewed in the browser by appending the route to this e.g. `http://localhost:8080/quizzes`.

While not logged in, any attempt to access any route will redirect to `/login`. Details of registered users are loaded from the database when the service starts.

**IMPORTANT: current security implementation is very basic and unsuitable for a live service.** Robust password encryption and use of HTTPS to exchange credentials are yet to be applied as minimum requirements for release. Additionally, credentials should be checked against the database on demand rather than loading into memory, if possible.

`/login` is used by the user authentication system. Successfully signing in results in a redirect to `/quizzes`. The page is based on the Spring default to ensure compatibility with the Spring Security workflow.

`/logout` immediately signs the user out (previously confirmation was requested) and redirects to `/login`.

`/quizzes` displays a list of all quizzes in the database.

`/quizzes/{quizId}/{quizTitle}` displays the questions associated with the supplied {quizId} and, if the logged-in user has appropriate permissions, the answers associated with each question. The URL includes a {quizTitle} for readability, but the database value is retrieved and used for elements on the page unless no title exists in the database. Currently the {quizId} is not validated so a page will be displayed for quizzes which don't exist in the database and will use the supplied {quizTitle}; **this behaviour should be changed**.
