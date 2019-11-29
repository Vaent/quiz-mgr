# Quiz manager

## Design documents

[design/requirements_breakdown.docx](design/requirements_breakdown.docx) is a text-only, high-level summary of the project requirements. Green text indicates elements which have been implemented.

[design/front_end_design.docx](design/front_end_design.docx) goes into more detail about the views that should be presented to users, with representative images and an indication of what interactions should take place with the API.

## Database setup

You must have PostgreSQL installed to set up the database, this can be downloaded for free at https://www.postgresql.org/download/.

Open PGAdmin and connect to any existing database.

Open a query tool and run the following command: `CREATE DATABASE quizzes;`

Refresh the list of databases, connect to the `quizzes` database and open a new query tool.

Open and run the `database/db_init.sql` file.

Open and run the `database/db_user_details.sql` file.

At this stage you can optionally open and run the `database/test_db_setup.sql` file - execution should complete with no errors. **THIS SCRIPT MUST NOT BE EXCEUTED AFTER ANY LIVE DATA HAS BEEN ENTERED** as it will delete all details in the quiz, question and answer tables.

Open and run the `database/fn_create_answer.sql` file, followed by the `database/fn_create_question.sql` file. In a production environment, questions and answers should be added to the database using the functions defined in these files, rather than directly writing records via SQL `INSERT` statements.

## Web service

This is set up as a Java (Spring Boot) project under [api/quiz-mgr](api/quiz-mgr) and has its own README.

The front end is defined within the Java project with views prepared dynamically at runtime.
