**📖 Project Overview**

This is a Selenium + Cucumber + TestNG automation framework for Veeva web applications.
It leverages Page Object Model (POM) design, supports parallel execution, retry logic, and generates Cucumber HTML & Extent Reports for detailed test results.

**⚙️ Prerequisites**
Java JDK ≥ 11
Maven ≥ 3.6
Chrome / Firefox / Edge browser installed
IDE: IntelliJ IDEA or Eclipse recommended
Environment variables set:
JAVA_HOME
MAVEN_HOME

VeevaAutoTesting/Architecture Diagram.png



**🗂️ Project Structure**

Veeva_WebTestAutomation/
│
├── src/
│   ├── main/java/
│   │   └── pages/             # Page Object classes
│   │   └── utils/             # Utilities (ElementUtils, ConfigReader, DriverManager)
│   │
│   └── test/java/
│       ├── stepDefns/         # Step Definitions
│       ├── hooks/             # Cucumber Hooks
│       └── runners/           # Cucumber TestNG runner classes
│
├── src/test/resources/
│   ├── features/              # Cucumber feature files
│   └── extent.properties      # Extent Report configuration
│
├── pom.xml                     # Maven build configuration
└── README.md

**💻 Setup Instructions**

1. Clone the repository

git clone <repository-url>
cd Veeva_WebTestAutomation


2. Install Maven dependencies
mvn clean install

3.Configure properties
src/test/resources/config.properties:
browser=chrome
url=https://www.nba.com/
Timeout=30
Modify according to your environment.
Optional: Update extent.properties for Extent Report customization.

**🧪 Running Tests**
1. Run All Tests via Maven
mvn clean test

2. Run Specific Feature
mvn clean test -Dcucumber.filter.tags="@SmokeTest"

3. Run via TestNG XML
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml

**⚡ Parallel Execution**:Configure testng.xml:

<suite name="CucumberSuite" parallel="tests" thread-count="2">
    <test name="Veeva Tests">
        <classes>
            <class name="runners.RunCucumberTest"/>
        </classes>
    </test>
</suite>

**Notes:**
Thread-safe driver is implemented using ThreadLocal<WebDriver> in DriverManager.
Each scenario runs in an isolated browser instance.

**🔁 Retry Logic**
. Failed tests automatically retry up to 2 times using RetryAnalyzer and RetryListener.
. Registered in testng.xml or via annotation transformer.

**📊 Reporting**
1. Cucumber HTML Report
Generated in: target/cucumber-report.html
Contains feature-wise scenario results.

2. Extent Reports
Generated in: target/extent-reports/ExtentSpark.html
Interactive dashboard with:
Scenario steps
Pass/Fail status
Screenshots on failure
Configuration: src/test/resources/extent.properties

3. Advanced Cucumber Reporting (Optional)
Using maven-cucumber-reporting plugin with JSON output for dashboard.

**📌 Best Practices**
Page Object Model (POM) used for all pages.
Dynamic locators implemented via getDynamicXpath() utility.
Waits & scrolls handled via ElementUtils for stability.
Screenshots captured on failure in Hooks.java.
Logs maintained for test execution steps.

**🚀 Notes**
Ensure ChromeDriver / GeckoDriver version matches your browser.
For flaky tests, check retry logs and screenshot folder.
For CI/CD integration, run mvn clean verify in pipeline to generate reports.
