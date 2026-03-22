# Periplus Automation Testing Project

## Overview

This project is my answer for **Question 5 - Scenario Option B (Part 2.2)** of the technical hometask internship application.

The goal of this automation is to test the main shopping cart flow on the **Periplus web store** using:

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven**

The automated scenario covers:

1. Open Google Chrome in a new window  
2. Navigate to `https://www.periplus.com/`  
3. Enter login email and password  
4. Find one product  
5. Add one product to the cart  
6. Verify that the product has been successfully added to the cart  

---

## Scenario Implemented

The test automates one end-to-end shopping cart case:

- open Periplus
- log in with a registered test account
- search for one book
- open the product detail page
- add the product to cart
- open cart page
- verify the selected product is present in the cart

For this project, I used one fixed product in the test flow because the assignment only requires **finding one product** and verifying that it is added successfully.

---

## Project Structure

```text
periplus-automation/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/periplus/pages/
│   │           ├── BasePage.java
│   │           ├── CartPage.java
│   │           ├── HomePage.java
│   │           ├── LoginPage.java
│   │           └── ProductPage.java
│   └── test/
│       └── java/
│           └── com/periplus/tests/
│               ├── BaseTest.java
│               └── ShoppingCartTest.java
```

---

## Tech Stack

- **Java 17**
- **Maven**
- **Selenium WebDriver**
- **TestNG**
- **WebDriverManager**

---

## Design Approach

I used a simple **Page Object Model (POM)** structure so the code is easier to read and maintain.

- `BaseTest` handles browser setup and teardown
- `BasePage` contains shared Selenium helper methods
- `LoginPage`, `HomePage`, `ProductPage`, and `CartPage` represent the main pages used in the test
- `ShoppingCartTest` contains the test scenario itself

This structure helps avoid putting all Selenium steps in one file.

---

## Prerequisites

Before running the project, make sure these are installed:

- Java 17
- Maven
- Google Chrome

You can verify them with:

```bash
java -version
mvn -version
```

---

## How to Run

Open terminal inside the project folder and run:

```bash
mvn clean test
```

If you are inside the parent folder first:

```bash
cd periplus-automation
mvn clean test
```

---

## Test Execution Evidence

Below is one successful execution result from my local run:

```text
PS C:\openway\periplus-automation> cd periplus-automation
PS C:\openway\periplus-automation\periplus-automation> mvn clean test
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------< com.periplus.test:PeriplusAutomation >----------------
[INFO] Building PeriplusAutomation 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ PeriplusAutomation ---
[INFO] Deleting C:\openway\periplus-automation\periplus-automation\target
[INFO] 
[INFO] --- resources:3.4.0:resources (default-resources) @ PeriplusAutomation ---
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ PeriplusAutomation ---
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling 5 source files with javac [debug target 17] to target\classes
[INFO] 
[INFO] --- resources:3.4.0:testResources (default-testResources) @ PeriplusAutomation ---
[INFO] skip non existing resourceDirectory C:\openway\periplus-automation\periplus-automation\src\test\resources
[INFO]
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ PeriplusAutomation ---
[INFO] Changes detected - recompiling the module! :dependency
[INFO] Compiling 2 source files with javac [debug target 17] to target\test-classes
[INFO] 
[INFO] --- surefire:3.2.5:test (default-test) @ PeriplusAutomation ---
[WARNING] useSystemClassLoader setting has no effect when not forking
[WARNING] The parameter forkCount should likely not be 0. Forking a JVM for tests improves test accuracy. Ensure to have a <forkCount> >= 1.
[INFO] Using auto detected provider org.apache.maven.surefire.testng.TestNGProvider
[INFO] Running TestSuite
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Mar 20, 2026 8:03:43 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 146
Mar 20, 2026 8:03:43 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 146.0.7680.80. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.25.0` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
DEBUG: Looking for email field...
DEBUG: Waiting for visibility of: By.cssSelector: input[name='email']
DEBUG: Typing text into: By.cssSelector: input[name='email']
DEBUG: Email entered. Looking for password field...
DEBUG: Waiting for visibility of: By.cssSelector: input[name='password']
DEBUG: Typing text into: By.cssSelector: input[name='password']
DEBUG: Clicking login button...
DEBUG: Waiting for visibility of: By.cssSelector: input[name='filter_name']
DEBUG: Typing text into: By.cssSelector: input[name='filter_name']
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 136.3 s -- in TestSuite
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:21 min
[INFO] Finished at: 2026-03-20T20:05:57+07:00
[INFO] ------------------------------------------------------------------------
PS C:\openway\periplus-automation\periplus-automation>
```

This output shows that the test suite ran successfully and Maven returned **BUILD SUCCESS**.

---

## Notes About the Current Implementation

A few notes about my current solution:

- this is focused on one main positive flow required by the assignment
- the code is intentionally kept simple and readable
- the project uses page objects to make the structure cleaner
- some waits were added because website loading can be slow depending on network conditions

In my case, access can be slower because I am currently living in China and sometimes need to use a VPN route through Indonesia to access the website. Because of that, I tried to make the test stable enough for a slow-loading environment.

---

## Possible Future Improvements

If this project were continued, some possible improvements would be:

- move login data to environment variables or config file
- improve cart verification using more specific UI elements
- add more negative and edge test cases
- add reporting and screenshots on failure
- improve driver/browser version handling if needed

---

## Author Note

This project was created as part of a QA internship technical task. Since this is my first time learning Java, I tried to keep the solution practical, readable, and aligned with the assignment requirements while still using a proper automation test structure.
