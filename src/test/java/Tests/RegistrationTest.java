package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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

public class RegistrationTest extends TestBase {
    LoginPage loginPage;
    RegistrationPage registrationPage;
    ExcelOperations excel = new ExcelOperations("registration");
    String testCase;


    //Dataprovider method --> return object array
    @DataProvider(name = "successRegistration")
    public Object[][] testDataSupplier1() throws Exception {
        Object[][] obj = new Object[excel.getRowCount()][1];
        for (int i = 1; i <= excel.getRowCount(); i++) {
            HashMap<String, String> testData = excel.getTestDataInMap(i);
            obj[i - 1][0] = testData;
        }
        return obj;

    }



    @BeforeMethod
    public void loadClass() {
        //loginPage = PageFactory.initElements(DriverFactory.getInstance().getDriver(), LoginPage.class);
        registrationPage = PageFactory.initElements(DriverFactory.getInstance().getDriver(), RegistrationPage.class);
    }

    @Test(dataProvider = "successRegistration", description = "Success Registration")
    public void registrationTest_01(Object obj1) throws TestLinkAPIException {
        try {
            testCase="AS-5";
            Map<String, String> testdata = (HashMap<String, String>) obj1;
            registrationPage.clickAccount();
            registrationPage.clickRegister();
            registrationPage.enterFirstname(testdata.get("firstName"));
            registrationPage.enterMiddleName(testdata.get("middleName"));
            registrationPage.enterLatName(testdata.get("lastName"));
            registrationPage.enterEmail();
            registrationPage.enterPassword(testdata.get("password"));
            registrationPage.enterConfirmPassword(testdata.get("confirmPassword"));
            registrationPage.clickRegisterButton();
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
