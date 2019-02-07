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
import pages.*;
import util.TestUtil;

public class CompSciCoursePageTest extends TestBase {

    HomePage homePage;
    CoursesPage coursesPage;
    CompSciCoursePage compSciCoursePage;

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeTest
    public void setReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +
                "/test-output/" + this.getClass().getSimpleName() + ".html");
        htmlReporter.config().setDocumentTitle("Brilliant - Automation - POM design pattern");
        htmlReporter.config().setReportName("'Computer Science' Course Page Tests");
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
        coursesPage = homePage.clickCourses();
        coursesPage.practiceTab.click();
        compSciCoursePage = coursesPage.clickCompSciCourse();
    }

    @Test
    public void pageTitleAssertion() {
        test = extent.createTest("pageTitleAssertion");
        Assert.assertEquals(driver.getTitle(), compSciCoursePage.title);
    }

    @Test
    public void compSciTopicLoads() {
        test = extent.createTest("compSciTopicLoads");
        Assert.assertTrue(compSciCoursePage.compSciTopic.isDisplayed());
    }

    @Test // Expected to fail
    public void compSciTopicLoadsFalseAssertion() {
        test = extent.createTest("compSciTopicLoadsFalseAssertion");
        Assert.assertFalse(compSciCoursePage.compSciTopic.isDisplayed());
    }

    @Test
    public void fundamentalsHeaderIsDisplayed() {
        test = extent.createTest("fundamentalsHeaderIsDisplayed");
        FundamentalsCoursePage fundamentalsCoursePage = compSciCoursePage.clickFundamentalsCourse();
        Assert.assertTrue(fundamentalsCoursePage.header.isDisplayed());
    }

    @Test
    public void algorithmsHeaderIsDisplayed() {
        test = extent.createTest("algorithmsHeaderIsDisplayed");
        AlgorithmsCoursePage algorithmsCoursePage = compSciCoursePage.clickAlgorithmsCourse();
        Assert.assertTrue(algorithmsCoursePage.header.isDisplayed());
    }

    @Test
    public void AnnCourseIconsAreDisplayed() {
        test = extent.createTest("AnnCourseIconsAreDisplayed");
        AnnCoursePage annCoursePage = compSciCoursePage.clickAnnCourse();
        Assert.assertEquals((annCoursePage.courseIcons).size(), 8);
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
