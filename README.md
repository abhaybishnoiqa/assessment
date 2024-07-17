**FEATURE**

- **Implicit and Explicit Waits**: Improve test stability by incorporating both implicit and explicit waits to handle synchronization issues.

- **Logging with Log4j**: Log test execution messages using Log4j for better monitoring and troubleshooting.

- **Configuration from `config.properties`**: Configure test data and environment settings easily using the `config.properties` file.

- **Maven Integration**: Use Maven for project management tasks, including dependency management and test execution.

- **TestNG Execution**: Execute tests using TestNG XML files, allowing for flexible test suite execution.

- **Report Manager**: Extent report is created.
- 
- **Utilities** : Created utilities for reusable methods like ElementUtility,JSUtility and ExcelUtility.

## Getting Started

Follow these steps to get started with the automated testing framework:

1. Clone the repository to your local machine.
2. Install Java and Maven if not already installed.
3. Configure test data and environment settings in the `config.properties` file.
4. Run `mvn clean` to clean the project.
5. Trigger test execution by running the TestNG XML file (`testng.xml`).
6. Review test reports generated after test execution.
7. Handling Failed Scenarios:
8. Screenshots of failed scenarios are automatically captured and saved in the Screenshots folder.
9. HTML reports are generated and stored in the Build/reports folder after test execution.
10. Parallel execution is implemented at both class and test levels for faster test execution.


## Folder Structure

![image](https://github.com/user-attachments/assets/ded05fd4-14ad-47e6-95d9-73e64f6a4c3e)

## Test Cases

The framework includes a set of 6 test cases covering various scenarios to demonstrate its functionality.

## Contributor

- Abhay Bishnoi


