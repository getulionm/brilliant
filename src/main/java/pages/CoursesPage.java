package pages;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursesPage extends TestBase {

    public CoursesPage(WebDriver driver) {
        PageFactory.initElements(TestBase.driver, this);
    }

    @FindBy(className = "toggle")
    public WebElement practiceTab;

    @FindBy(xpath = "//a[@href='/computer-science/'][contains(text(),'Computer Science')]")
    public WebElement compSciCourse;

    @FindBy(xpath = "//h2[@class='courses-category-name']")
    public WebElement popularCourses;

    public CompSciCoursePage clickCompSciCourse(){
        compSciCourse.click();
        return new CompSciCoursePage(driver);
    }

}
