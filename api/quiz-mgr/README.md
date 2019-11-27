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

## Routes

By default the service runs locally on `http://localhost:8080` and pages can be viewed in the browser by appending the route to this e.g. `http://localhost:8080/quizzes`.

All pages can only be viewed when logged in. Any attempt to access a route (including undefined routes) while not logged in will redirect to the login page. Registered users are loaded from the database when the service starts. A 'Sign out' link has been added to the top of each custom view, linking to the logout page.

`/login` is used by the user authentication system - no custom route is defined. **Navigating directly to this route results in a 404 error after login credentials are supplied** (this is true even if the user is already logged in).

`/logout` is similarly used by the user authentication system with no customisation. Confirming sign out redirects to the login page.

`/quizzes` displays a list of all quizzes in the database.

`/quizzes/{quizId}/{quizTitle}` displays the questions associated with the supplied {quizId} and the answers associated with each question. The URL includes a {quizTitle} for readability, but the database value is retrieved and used for elements on the page unless no title exists in the database. Currently the id is not validated so a page will be displayed for quizzes which don't exist in the database and will use the supplied {quizTitle}; this behaviour should be changed.
