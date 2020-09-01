package prova_getnet;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;


public class TesteSteps {
    private static WebDriver driver;

    @Before
    public static void setUpTest(){
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://site.getnet.com.br/");


    }

    @After
    public static void tearDownTest(){ driver.quit(); }

    public void waitForLoad(){
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    @Given("^I'm in the GetNet Site$")
    public void i_m_in_the_GetNet_Site()  {

    //BDD Step Explanation

    }

    @When("^I Search for Superget on the Search Field$")
    public void i_Search_for_Superget_on_the_Search_Field() {

        WebElement searchField = driver.findElement(By.xpath("//*[@id=\"search-trigger\"]/div/span"));
        waitForLoad();
        searchField.click();
        waitForLoad();

        WebElement searchOption = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        searchOption.sendKeys(("superget"));

        WebElement searchButton = driver.findElement(By.xpath("/html/body/section/div/div/div/form/button"));
        searchButton.click();
    }

    @Then("^I Click on Como acessar a minha conta SuperGet?")
    public void i_Click_on_Como_acessar_a_minha_conta_SuperGet$()  {

        WebElement clickOnOption = driver.findElement(By.linkText("Como acesso a minha conta SuperGet?"));
        clickOnOption.click();
        waitForLoad();

    }

    @Then("^I Check if the message has been opened correctly$")
    public void i_Check_if_the_message_has_been_opened_correctly()  {

        WebElement reportSuccess = driver.findElement(By.xpath("//*[text()='Como acesso a minha conta SuperGet?']"));
        assertEquals("Como acesso a minha conta SuperGet?", reportSuccess.getText());

    }

}