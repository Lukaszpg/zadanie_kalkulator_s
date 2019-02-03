# Contract currency calculator

This project is a recruitment task. Calculator can be used to calculate monthly contract earnings in Polish Zloty using following countries currency: 

- United Kingdom (25% tax cut, 600 GBP fixed costs)
- Germany (20% tax cut, 800 EUR fixed costs)
- Poland (19% tax cut, 1200 PLN fixed costs)

We assume that month has 22 days.

Currency rates are fetched from https://fixer.io/.

### Technologies used: 

- Spring Boot as backend technology
- Gradle for backend dependency management and project building
- NPM for frontend dependency management and transpiling from TypeScript to JavaScript
- Angular 7 + Semantic UI for frontend
- Spock for testing

### Building the project

Requires Java JDK 1.8+ installed and JAVA_HOME path set in the environment variables.

Clone the repo and execute gradle build task: 

```
gradlew clean build
```

Gradle is configured to do all the following things in this order: 

1. Get all necessary java dependencies.
2. Launch unit tests with Spock.
3. Compile Java classes.
4. Download node and npm if there's none present in the project directory.
5. Install and configure npm and node.
6. Transpile Angular typescript files to javascript files.
7. Pack all frontend files (javascript, css and html) to .jar.
8. Copy .jar to static directory .
9. Pack the .jar into the Spring Boot libs directory so it can be actually used and served by embedded Tomcat.

### Launching the project

Navigate to backend\build\libs and launch the project with 

```
java -jar calculator-1.0.0.jar
```
