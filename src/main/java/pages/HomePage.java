package pages;

import base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.TestUtil;

public class HomePage extends TestBase {

    public HomePage() {
        PageFactory.initElements(TestBase.driver, this);
    }

    @FindBy(xpath = "//a[@class='btn signup-btn btn-accent col-2 ax-click']")
    public WebElement signUpForFree;

    @FindBy(xpath = "//div[@class='modal-bg in']//a[@id='signup-email']")
    public WebElement joinUsingEmail;

    @FindBy(id = "id_email")
    public WebElement emailField;

    @FindBy(id = "id_password1")
    public WebElement passwordField;

    @FindBy(id = "id_first_name")
    public WebElement firstNameField;

    @FindBy(id = "id_last_name")
    public WebElement lastNameField;

    @FindBy(id = "id_birthday_month")
    public WebElement selectBirthdayMonth;

    @FindBy(id = "id_birthday_day")
    public WebElement selectBirthdayDay;

    @FindBy(id = "id_birthday_year")
    public WebElement selectBirthdayYear;

    @FindBy(xpath = "//button[contains(text(),'Sign up')]")
    public WebElement signUpButton;

    @FindBy(xpath = "//div[@class='row outer-field birthday']//ul[@class='errorlist']")
    public WebElement invalidDateError;

    @FindBy(linkText = "Courses")
    public WebElement coursesLink;


    public void clickSignUpForFree(){
        signUpForFree.click();
    }

    public void clickJoinUsingEmail(){
        joinUsingEmail.click();
    }

    public void fillUpFields(String email,
                             String password,
                             String firstName,
                             String lastName,
                             String bdayMonth,
                             String bdayDay,
                             String bdayYear)
    {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        new Select(selectBirthdayMonth).selectByValue(bdayMonth);
        new Select(selectBirthdayDay).selectByValue(bdayDay);
        new Select(selectBirthdayYear).selectByValue(bdayYear);
    }

    public WelcomePage clickSignUp(){
        signUpButton.click();
        return new WelcomePage(driver);
    }

    public WelcomePage newValidUserSignUp(){
        clickSignUpForFree();
        clickJoinUsingEmail();
        fillUpFields(TestUtil.createRandomEmail(),
                TestUtil.createRandomPassword(6),
                TestUtil.createRandomName(8),
                TestUtil.createRandomName(8),
                TestUtil.createRandomIntBetween(1,12),
                TestUtil.createRandomIntBetween(1,28),
                TestUtil.createRandomIntBetween(1930,2005));
        return clickSignUp();
    }

    public CoursesPage clickCourses(){
        TestUtil.scrollToBottom();
        coursesLink.click();
        return new CoursesPage(driver);
    }

}