# Mobile QA Assignment

## Description
The repository contains automated tests for a simple native Android application that shows a list of searchable cities using the Espresso framework. 

## Tools
- Java 11
- JUnit 4
- Espresso
- Android Studio
- Gradle
- Emulator(Pixel 4 API 30)

## Project structure
-      app/src/androidTest - Tests, test data, screens, test utilities
-      app/build/reports/androidTest/connected/index.html - JUnit HTML Report
-      Reports - Test cases, report, bug reports
   
## How to Run tests

Preconditions:
- Install Android Studio
- Java version 11 must be installed
- Clone this project locally
- Create a virtual device (Tools ->  Device Manager -> Create Device -> Choose any device with mark Play Store and APi level > 30)
- Before running the tests on the virtual device, you must start the application

## Run UI tests

Running through the console in the IDE or Android Studio
Preconditions:
- The emulator must be configured (for example, Pixel 4 API 30)
- Run the application
- In the Android Studio console, run the command 
-     './gradlew connectedCheck'

Launching with Android Studio
- You need to run the test class using the "Run" button
Path to the test class: 'app/src/androidTest/java/views/tests/AndroidTestsAppMobileAssignmentTest'

## Reports

- In the root of the project in the Reports folder are stored: test cases, bug reports, report

JUnit HTML Report
It's located at the path: 'app/build/reports/androidTest/connected/index.html'
To view the report you need to run 'Open in -> Browser' on the file index.html
