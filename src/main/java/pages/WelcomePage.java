package pages;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WelcomePage extends TestBase {

    public WelcomePage(WebDriver driver) {
        PageFactory.initElements(TestBase.driver, this);
    }

    @FindBy(id = "step-1-continue")
    public WebElement continueStep;

    @FindBy(id = "step-1-continue")
    public List<WebElement> continueSteps;

    @FindBy(xpath = "//label[contains(text(),'None of these')]")
    public WebElement noneOfThese;

    public CoursesPage goThroughNewUserIntro() {
        continueSteps.get(0).click();
        continueSteps.get(1).click();
        continueSteps.get(2).click();
        continueSteps.get(3).click();

        noneOfThese.click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return new CoursesPage(driver);
    }

}
