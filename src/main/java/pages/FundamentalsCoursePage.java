package pages;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundamentalsCoursePage extends TestBase{

    public FundamentalsCoursePage(WebDriver driver) {
        PageFactory.initElements(TestBase.driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Computer Science Fundamentals')]")
    public WebElement header;
}
