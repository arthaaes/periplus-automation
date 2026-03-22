# Periplus Shopping Cart Automation

## Project Overview

This project is my answer for **Question 5, Scenario Option B, Part 2.2** from the QA internship technical hometask.

The task asks me to automate one shopping cart scenario on the **Periplus** website using:

- Java
- Selenium WebDriver
- TestNG
- Maven

The scenario covered in this project is:

1. Open Google Chrome in a new window
2. Go to `https://www.periplus.com/`
3. Enter login email and password
4. Find one product
5. Add one product to the cart
6. Verify that the product was added successfully

---

## What This Test Does

This automation performs one simple end-to-end flow:

- open the Periplus website
- log in using a test account
- search for one product
- open the product page
- add the product to cart
- open the cart page
- verify that the selected product is in the cart

For this assignment, I used one fixed product in the test because the requirement only says to **find one product** and verify that it is added to the cart.

---

## Tools Used

- **Java 17**
- **Maven**
- **Selenium WebDriver**
- **TestNG**
- **WebDriverManager**

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

Short explanation:

- `BaseTest` is used for browser setup and teardown
- `BasePage` stores common Selenium helper methods
- page classes are used to separate actions by page
- `ShoppingCartTest` contains the main test flow

I used this structure because it is cleaner than putting all Selenium code in one file.

---

## How to Run

Make sure Java, Maven, and Google Chrome are already installed.

Run this command from the project folder:

```bash
mvn clean test
```

---

## Notes About Slow Connection

I currently live in **China**, and to access Periplus I need to use a **VPN with Indonesia server**. Because of that, the website can be very slow and sometimes inconsistent while loading.

So in the code, I added a few things to make the test more stable for this situation, for example:

- using waits before interacting with important elements
- using some defensive checks for popup/overlay handling
- keeping the flow simple and direct
- using page load strategy adjustments to reduce unnecessary waiting time
- adding a short fixed wait in one part of the flow when the site was still unstable in my environment

As a QA engineer, I wanted the test to be a bit more time-efficient and also less likely to fail only because of the slow connection.

---

## Successful Test Run

Below is one successful result from my local execution:

```text
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

This result shows that the project compiled successfully and the automated test passed.

---

## Possible Improvements

If I continue this project later, I would improve these parts:

- move login data into config or environment variables
- add more cart test cases, including negative cases
- make cart verification more specific
- add screenshots or reports for failed tests

---

## Personal Note

This is my first time learning Java, so in this project I tried to make the solution work correctly while also keeping the structure understandable. I used a simple Page Object Model approach because it helped me organize the code better and made the test flow easier to read.
