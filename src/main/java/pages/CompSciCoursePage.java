package pages;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompSciCoursePage extends TestBase {

    public String title = "Practice Computer Science | Brilliant";

    public CompSciCoursePage(WebDriver driver) {
        PageFactory.initElements(TestBase.driver, this);
    }

    @FindBy(id = "cmp_topic_practice_navigation_id")
    public WebElement compSciTopic;

    @FindBy(xpath = "//h4[contains(text(),'Computer Science Fundamentals')]")
    public WebElement compSciFundamentals;

    @FindBy(xpath = "//h4[contains(text(),'Artificial Neural Networks')]")
    public WebElement compSciANN;

    @FindBy(xpath = "//h4[contains(text(),'Computer Science Algorithms')]")
    public WebElement compSciAlgorithms;

    public FundamentalsCoursePage clickFundamentalsCourse(){
        compSciFundamentals.click();
        return new FundamentalsCoursePage(driver);
    }

    public AlgorithmsCoursePage clickAlgorithmsCourse(){
        compSciAlgorithms.click();
        return new AlgorithmsCoursePage(driver);
    }

    public AnnCoursePage clickAnnCourse(){
        compSciANN.click();
        return new AnnCoursePage(driver);
    }


}
