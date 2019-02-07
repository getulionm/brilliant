package test;

import base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CoursesPage;
import pages.HomePage;
import pages.WelcomePage;
import util.TestUtil;


public class HomePageTest extends TestBase {

    HomePage homePage;
    CoursesPage coursesPage;

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeTest
    public void setReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +
                "/test-output/" + this.getClass().getSimpleName() + ".html");
        htmlReporter.config().setDocumentTitle("Brilliant - Automation - POM design pattern");
        htmlReporter.config().setReportName("'Home / Welcome' Page Tests");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Test Class", this.getClass().getSimpleName());
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", TestBase.browser);
        extent.setSystemInfo("Tester Name", "Getulio");
    }

    @BeforeMethod
    public void setUp() {
        initialiseBrowser();
        homePage = new HomePage();
    }

    @Test
    public void newUserSeesPopularCourses(){
        test = extent.createTest("newUserSeesPopularCourses");
        WelcomePage welcomePage = homePage.newValidUserSignUp();
        coursesPage = welcomePage.goThroughNewUserIntro();
        Assert.assertTrue(coursesPage.popularCourses.isDisplayed());
    }

    @DataProvider(name = "validDates")
    public Object[][] validDates() {
        return new Object[][]{
                {"1","1","1930"},
                {"2","29","2004"},
                {"12","31","2005"}
        };
    }

    @Test(dataProvider = "validDates")
    public void signUpTestValidDates(String month, String day, String year){
        test = extent.createTest("signUpTestValidDates");
        homePage.clickSignUpForFree();
        homePage.clickJoinUsingEmail();
        homePage.fillUpFields(
                TestUtil.createRandomEmail(),
                TestUtil.createRandomPassword(6),
                TestUtil.createRandomName(8),
                TestUtil.createRandomName(8),
                month,
                day,
                year
        );
        WelcomePage welcomePage = homePage.clickSignUp();
        Assert.assertTrue(welcomePage.continueStep.isDisplayed());
    }

    @DataProvider(name = "invalidDates")
    public Object[][] invalidDates() {
        return new Object[][]{
                {"2","30","1930"},
                {"2","29","2005"},
                {"12","31","2006"}
        };
    }

    @Test(dataProvider = "invalidDates")
    public void signUpTestInvalidDates(String month, String day, String year){
        test = extent.createTest("signUpTestInvalidDates");
        homePage.clickSignUpForFree();
        homePage.clickJoinUsingEmail();
        homePage.fillUpFields(
                TestUtil.createRandomEmail(),
                TestUtil.createRandomPassword(6),
                TestUtil.createRandomName(8),
                TestUtil.createRandomName(8),
                month,
                day,
                year
        );
        WelcomePage welcomePage = homePage.clickSignUp();
        Assert.assertTrue(homePage.invalidDateError.isDisplayed());
    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Failed. Test Case: " + result.getName());
            test.log(Status.FAIL, "Failed. Test Case: " + result.getThrowable());

            String screenshotPath = TestUtil.getScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Skipped. Test Case: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Passed. Test Case: " + result.getName());
        }
        finaliseBrowser();
    }

}
