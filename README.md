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

Reports
- Test cases
- Bag Report

## Project structure
AndroidTest
 -java
  --app.com.mobileassignment.views 
   ---screens (package containing screen descriptions)
   ---tests (package contains tests)
   ---unit (the package contains various test utilities)
   
## Run tests
- The emulator must be configured (for example, Pixel 4 API 30)
- In the Android Studio console, run the command './gradlew connectedCheck'
