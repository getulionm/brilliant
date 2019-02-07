package pages;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class AnnCoursePage extends TestBase{

    public AnnCoursePage(WebDriver driver) {
        PageFactory.initElements(TestBase.driver, this);
    }

    @FindBy(className = "exploration-image")
    public List<WebElement> courseIcons;
}
