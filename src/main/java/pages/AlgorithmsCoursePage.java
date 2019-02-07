package pages;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlgorithmsCoursePage extends TestBase{

    public AlgorithmsCoursePage(WebDriver driver) {
        PageFactory.initElements(TestBase.driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Computer Science Algorithms')]")
     public WebElement header;
}
