# todo-list-api

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.1.2.

## Prerequisites #1

Create a database named "TodoListDB" with user todoapi and password todoapi that would be able to create schemas in that database.
Or set these parrameters in:
   'build.gradle' file sections 'jooq' and 'flyway'
   in main/java/resources/ 'application.properties'
   
## Prerequisites #2
run 'gradle generateTodoapiJooqSchemaSource' this will run flywayMigrate to add todo-schema and requered tables + some data to then, then it will generate jooq classes that connect to the database
