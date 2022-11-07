package reports;

import java.util.Objects;

//import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reusableComponents.PropertiesOperations;
import testBase.DriverFactory;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class ListenersImplementation implements ITestListener{
	//JiraOperations jiraOps = new JiraOperations();

	static ExtentReports report;
		   ExtentTest test;
		   
	public void onTestStart(ITestResult result) {
		//before each test case
		test = report.createTest(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(test);
	}

	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
		ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestFailure(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
		
		//add screenshot for failed test.

			//Log.info(getTestMethodName(iTestResult) + " test is failed.");

			//Get driver from BaseTest and assign to local webdriver variable.
			Object testClass = result.getInstance();
			WebDriver driver = DriverFactory.getInstance().getDriver();
			//Take base64Screenshot screenshot for extent reports
			String base64Screenshot =
					"data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
			//ExtentReports log and screenshot operations for failed tests.
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Failed",
					ExtentFactory.getInstance().getExtent().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));


		///////JIRA defect creation part
		String automaticJIRAcreation = PropertiesOperations.getPropertyValueByKey("automatic_Issue_Creation_In_JIRA");
		if(automaticJIRAcreation.trim().equalsIgnoreCase("ON")) {
			String issueS = "Automation Test Failed - "+result.getMethod().getMethodName();
			String issueD = "Test Data to be passed here.";
			String issueNumber = null;
			try {
				//issueNumber = jiraOps.createJiraIssue("QDPM", issueS, issueD, "10000", "5", "QDPM", "SIT", "5f782c4b95fe8e0069705791");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				//jiraOps.addAttachmentToJiraIssue(issueNumber, screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
		ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		try {
			 report = ExtentReportNG.setupExtentReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		//close extent
		report.flush();
	}

}
