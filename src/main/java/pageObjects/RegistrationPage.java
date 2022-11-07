package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.ExtentFactory;


import java.util.Random;

import static com.aventstack.extentreports.Status.INFO;

public class RegistrationPage {
    private WebDriver driver;

    //Asserssion asserssion;

    public RegistrationPage(WebDriver driver) {
        //driver=DriverFactory.getInstance().getDriver();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "ACCOUNT")
    private WebElement accountLink;
    @FindBy(linkText = "Register")
    private WebElement registerLink;
    @FindBy(id = "firstname")
    private WebElement firstName;
    @FindBy(id = "middlename")
    private WebElement middlename;
    @FindBy(id = "lastname")
    private WebElement lastName;
    @FindBy(xpath = "//input[@title='Email Address']")
    private WebElement email;
    @FindBy(xpath = "//input[@title='Password']")
    private WebElement password;
    @FindBy(id = "confirmation")
    private WebElement passwordConfirm;
    @FindBy(id = "is_subscribed")
    private WebElement issubscribed;
    @FindBy(xpath = "//button[@title='Register']")
    private WebElement registerButton;
    @FindBy(xpath = "//li[@class='success-msg']")
    private WebElement successMessage;
    @FindBy(linkText = "My Account")
    private WebElement myAccountLink;
    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;

    @FindBy(linkText = "Log In")
    private WebElement loginLink;
    @FindBy(xpath = "//h3[contains(text(),'Contact Information')]/following-sibling::a")
    private WebElement editAccountInfo;
    @FindBy(xpath = "//a[contains(text(),'Forgot Your Password?')]")
    private WebElement forgotPasswordLink;
    //public static Logger logger = Logger.getLogger(RegistrationPage.class.getName());

    //public static Logger log = Logger.getLogger("");
    public  void clickAccount() throws InterruptedException {
        Thread.sleep(5000);
        accountLink.click();
        //loginLink.click();
        //forgotPasswordLink.click();
        //loginLink.click();
        //driver=DriverFactory.getInstance().getDriver();
        //driver.findElement(By.linkText("ACCOUNT")).click();
        ExtentFactory.getInstance().getExtent().log(INFO,"Click on account link");
        ExtentFactory.getInstance().getExtent().assignAuthor("Mayank Mishra");
        //Log.info("clicked on account link");
    }


    public  void clickLoginLink() throws InterruptedException {
        //Log.info("Click on login link");
        Thread.sleep(5000);
        loginLink.click();
        ExtentFactory.getInstance().getExtent().log(INFO,"Click on login link");
    }

    public void clickForgotPasswordLink() throws InterruptedException {
        //Log.info("Click on login link");
        Thread.sleep(5000);
        forgotPasswordLink.click();
        ExtentFactory.getInstance().getExtent().log(INFO,"Click on forgot password link");
    }

    public void clickRegister() throws InterruptedException {
        Thread.sleep(5000);
        registerLink.click();
        ExtentFactory.getInstance().getExtent().log(INFO,"Click on register link");
        //Log.info("clicked on register link");
        //logger.info("clicked on register link");
    }

    public void enterFirstname(String fname) {
        firstName.sendKeys(fname);
        ExtentFactory.getInstance().getExtent().log(INFO,"Enter first name :"+fname);
    }

    public void enterMiddleName(String mname) {
        middlename.sendKeys(mname);
        ExtentFactory.getInstance().getExtent().log(INFO,"Enter middle name :"+mname);
    }

    public void enterLatName(String lname) {
        lastName.sendKeys(lname);
        ExtentFactory.getInstance().getExtent().log(INFO,"Enter last name :"+lname);
    }

    public void enterEmail() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        String emailId="dummyEmail" + randomInt + "@gmail.com";
        email.sendKeys(emailId);
        ExtentFactory.getInstance().getExtent().log(INFO,"Enter emailId :"+emailId);
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
        ExtentFactory.getInstance().getExtent().log(INFO,"Enter password is :"+pwd);
    }

    public void enterConfirmPassword(String cpwd) {
        passwordConfirm.sendKeys(cpwd);
        ExtentFactory.getInstance().getExtent().log(INFO,"Enter confirm password is :"+cpwd);
    }

    public void selectSubscribe() {
        issubscribed.click();

    }

    public boolean isSubscribe() {
        boolean isSubscribe = issubscribed.isSelected();
        //Log.info("newsletter subscribed");
        return isSubscribe;
    }

    public void clickRegisterButton() {
        registerButton.click();
        ExtentFactory.getInstance().getExtent().log(INFO,"click on register button");
    }

    public void verifySuccessRegistration(String expectedText) {
        //Utility.isElementPresent(successMessage, driver);
        //String actualText = successMessage.getText();
        //asserssion.isTextSame(expectedText, actualText);
        //Log.info("registration done");
        successMessage.isDisplayed();
        ExtentFactory.getInstance().getExtent().log(INFO,"Registration done");
    }

    public void clickMyAccount() {
        myAccountLink.click();
        //Log.info("clicked on my account link");
    }


    public void logout() {
        logoutLink.click();
        ///Log.info("clicked on logout link");
    }

    public void clickEdit() {
        editAccountInfo.click();
        //Log.info("editing account info");
    }

    public String getEmail() {
        String emailText = email.getAttribute("value");
        //Log.info("email is :" + emailText);
        return emailText;
    }

    public String getFullName() {
        String fname = firstName.getAttribute("value");
        String lname = lastName.getAttribute("value");
        String fullName = fname + " " + lname;
        //Log.info("full name is :" + fullName);
        return fullName;
    }

}
