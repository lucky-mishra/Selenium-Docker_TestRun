package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.ForgotPasswordPage;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import reports.ExtentFactory;
import reusableComponents.ExcelOperations;
import reusableComponents.TeslinkEngine;
import testBase.DriverFactory;
import testBase.TestBase;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.util.HashMap;
import java.util.Map;

public class    ForgotPasswordTest extends TestBase {
    LoginPage loginPage;
    RegistrationPage registrationPage;
    ForgotPasswordPage forgotPasswordPage;
    ExcelOperations excel = new ExcelOperations("validLogin");
    ExcelOperations excel2 = new ExcelOperations("invalidLogin");
    String testCase;

    //Dataprovider method --> return object array
    @DataProvider(name = "validEmailId")
    public Object[][] testDataSupplier1() throws Exception {
        Object[][] obj = new Object[excel.getRowCount()][1];
        for (int i = 1; i <= excel.getRowCount(); i++) {
            HashMap<String, String> testData = excel.getTestDataInMap(i);
            obj[i - 1][0] = testData;
        }
        return obj;

    }

    @DataProvider(name = "invalidLogin")
    public Object[][] testDataSupplier2() throws Exception {
        Object[][] obj = new Object[excel2.getRowCount()][1];
        for (int i = 1; i <= excel2.getRowCount(); i++) {
            HashMap<String, String> testData = excel2.getTestDataInMap(i);
            obj[i - 1][0] = testData;
        }
        return obj;

    }

    @BeforeMethod
    public void loadClass() {
        //loginPage = PageFactory.initElements(DriverFactory.getInstance().getDriver(), LoginPage.class);
        registrationPage = PageFactory.initElements(DriverFactory.getInstance().getDriver(), RegistrationPage.class);
        forgotPasswordPage = PageFactory.initElements(DriverFactory.getInstance().getDriver(), ForgotPasswordPage.class);
    }

    @Test(dataProvider = "validEmailId",description = "get forgot password with blank emailId")
    public void forgotPasswordTest_01(Object obj1) throws TestLinkAPIException {
        try {
            testCase="AS-3";
            //Map<String, String> testdata = (HashMap<String, String>) obj1;
            registrationPage.clickAccount();
            registrationPage.clickLoginLink();
            forgotPasswordPage.clickForgotPasswordLink();
           forgotPasswordPage.clickSubmit();
            forgotPasswordPage.verifyBlankEmailMessage();
            result = TestLinkAPIResults.TEST_PASSED;
            notes = "Executed successfully";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            TeslinkEngine.result = TestLinkAPIResults.TEST_FAILED;
            TeslinkEngine.notes = "Execution failed";
            Assert.fail("Cant do login");
        }
        finally {
            reportResult(testProject, testPlan, testCase, build, notes, result);
            ExtentFactory.getInstance().getExtent().assignCategory("Forgot Password");
            ExtentFactory.getInstance().getExtent().assignAuthor("Mayank Mishra");
        }
    }

    @Test(dataProvider = "validEmailId", description = "get forgot password with valid emailId")
    public void forgotPasswordTest_02(Object obj1) throws TestLinkAPIException {
        try {
            testCase="AS-4";
            Map<String, String> testdata = (HashMap<String, String>) obj1;
            registrationPage.clickAccount();
            registrationPage.clickLoginLink();
            forgotPasswordPage.clickForgotPasswordLink();
            forgotPasswordPage.enterEmail();
            forgotPasswordPage.clickSubmit();
            forgotPasswordPage.verifySuccessMessage(testdata.get("email"));
            result = TestLinkAPIResults.TEST_PASSED;
            notes = "Executed successfully";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            TeslinkEngine.result = TestLinkAPIResults.TEST_FAILED;
            TeslinkEngine.notes = "Execution failed";
            Assert.fail("Cant do login");
        }
        finally {
            reportResult(testProject, testPlan, testCase, build, notes, result);
            ExtentFactory.getInstance().getExtent().assignCategory("Forgot Password");
            ExtentFactory.getInstance().getExtent().assignAuthor("Mayank Mishra");
        }
    }

}
