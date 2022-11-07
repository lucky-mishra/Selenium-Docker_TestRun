package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
//import pages.LoginPage;
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

public class LoginTest extends TestBase {
    LoginPage loginPage;
    RegistrationPage registrationPage;
    ExcelOperations excel = new ExcelOperations("validLogin");
    ExcelOperations excel2 = new ExcelOperations("invalidLogin");

    String testCase;
    //Dataprovider method --> return object array
    @DataProvider(name = "validLogin")
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

        loginPage = PageFactory.initElements(DriverFactory.getInstance().getDriver(), LoginPage.class);
        registrationPage = PageFactory.initElements(DriverFactory.getInstance().getDriver(), RegistrationPage.class);
    }

    @Test(dataProvider = "invalidLogin", description = "login with invalid password")
    public void loginTest_01(Object obj1) throws TestLinkAPIException {
        try {
           testCase="AS-1";

            Map<String, String> testdata = (HashMap<String, String>) obj1;
            registrationPage.clickAccount();
            System.out.println("clicking on login link");
            registrationPage.clickLoginLink();
            //ISuite suite = context.getSuite();
            //String email = (String) suite.getAttribute("emailId");
            //System.out.println("Email id is :"+email);
            //loginPage.enterEmail(email);
            loginPage.enterEmail(testdata.get("email"));
            registrationPage.enterPassword(testdata.get("password"));
            loginPage.clickLoginButton();
            //loginPage.verifyLogin();
            result = TestLinkAPIResults.TEST_PASSED;
            notes = "Executed successfully";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            TeslinkEngine.result = TestLinkAPIResults.TEST_FAILED;
            TeslinkEngine.notes = "Execution failed";
            Assert.fail("Cant do login");
        } finally {
            reportResult(testProject, testPlan, testCase, build, notes, result);
            ExtentFactory.getInstance().getExtent().assignCategory("Login");
            ExtentFactory.getInstance().getExtent().assignAuthor("Mayank Mishra");
        }
    }

    @Test(dataProvider = "validLogin", description = "login with valid password")
    public void loginTest_02(Object obj2) throws TestLinkAPIException {
        try {
            testCase="AS-2";
            Map<String, String> testdata = (HashMap<String, String>) obj2;
            registrationPage.clickAccount();
            System.out.println("clicking on login link");
            Thread.sleep(5000);
            registrationPage.clickLoginLink();
            //loginPage.clickForgotPasswordLink();
            //ISuite suite = context.getSuite();
            //String email = (String) suite.getAttribute("emailId");
            //System.out.println("Email id is :"+email);
            //loginPage.enterEmail(email);
            loginPage.enterEmail(testdata.get("email"));
            registrationPage.enterPassword(testdata.get("password"));
            loginPage.clickLoginButton();
            loginPage.verifyLogin();
            result = TestLinkAPIResults.TEST_PASSED;
            notes = "Executed successfully";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            TeslinkEngine.result = TestLinkAPIResults.TEST_FAILED;
            TeslinkEngine.notes = "Execution failed";
            Assert.fail("Cant do login");
        }finally {
            reportResult(testProject, testPlan, testCase, build, notes, result);
            ExtentFactory.getInstance().getExtent().assignCategory("Login");
            assignAuthor();
            assignDevice();
        }

    }

}
