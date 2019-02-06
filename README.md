# Contract currency calculator

[![CircleCI](https://circleci.com/gh/Lukaszpg/zadanie_kalkulator_s/tree/master.svg?style=svg)](https://circleci.com/gh/Lukaszpg/zadanie_kalkulator_s/tree/master)

This project is a recruitment task. Calculator can be used to calculate monthly contract earnings in Polish Zloty using following countries: 

- United Kingdom (25% tax cut, 600 GBP fixed costs)
- Germany (20% tax cut, 800 EUR fixed costs)
- Poland (19% tax cut, 1200 PLN fixed costs)

Month has 22 days by default (configurable in application.properties).

Currency rates are fetched from http://api.nbp.pl/.

### Technologies used: 

- Spring Boot as a backend technology
- Gradle for backend dependency management and project building
- NPM for frontend dependency management
- Webpack for transpiling from TypeScript to JavaScript
- Angular 7 + Angular Material Design for frontend
- Spock for testing
- CircleCI for Continuous Integration

### Building the project

Requires Java JDK 1.8+ installed and JAVA_HOME path set in the environment variables.

Clone the repo and execute gradle build task: 

```
gradlew clean build
```

Gradle is configured to do all the following things: 

1. Get all necessary java dependencies.
2. Launch tests with Spock.
3. Compile Java classes.
4. Download node and npm if there's none present in the project directory.
5. Install and configure npm and node.
6. Transpile Angular typescript files to javascript files.
7. Pack all frontend files (javascript, css and html) to .jar.
8. Copy .jar to static directory .
9. Pack the .jar into the Spring Boot libs directory so it can be actually used and served by embedded Tomcat.

### Launching the project

Navigate to backend\build\libs and launch the project with:

```
java -jar calculator-1.0.0.jar
```

Default server ip and port: http://localhost:8080
