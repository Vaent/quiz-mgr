# Quiz manager

## Database setup

You must have PostgreSQL installed to set up the database.

Open PGAdmin and connect to any existing database.

Open a query tool and run the following command: `CREATE DATABASE quizzes;`

Refresh the list of databases, connect to the `quizzes` database and open a new query tool.

Open and run the `database/db_init.sql` file.

Open and run the `database/db_user_details.sql` file.

At this stage you can optionally open and run the `database/test_db_setup.sql` file - execution should complete with no errors. **THIS SCRIPT MUST NOT BE EXCEUTED AFTER ANY LIVE DATA HAS BEEN ENTERED** as it will delete all details in the quiz, question and answer tables.
