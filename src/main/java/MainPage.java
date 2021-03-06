
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class MainPage {
    private final By TABLE_HEAD = By.xpath("/html/body/div[2]/main/div[3]/div[11]/table/tbody/tr");
    private final By TABLE_ROWS = By.xpath("/html/body/div[2]/main/div[3]/div[1]/table/tbody/tr");
    private final By TABLE_ROWS2 = By.xpath("/html/body/div[2]/main/div[3]/div[46]/table/tbody/tr");
    private final By TABLE_NAMES = By.xpath("/html/body/div[2]/main/div[3]/div[58]/table/thead/tr/th");

    private final By FORMS_MENU = By.xpath("//*[@id=\"bd-docs-nav\"]/ul/li[5]/button");
    private final By CHECKS = By.xpath("//*[@id=\"forms-collapse\"]/ul/li[4]/a");
    private final By TITLE_CHECKS = By.xpath("//*[@id=\"content\"]");
    private final By ANCHOR = By.xpath("//*[@id=\"checks\"]/a");
    private final By CHECKBOX = By.xpath("/html/body/div[2]/main/div[3]/div[4]/div/label");
    private final By COLLAPSE_BUTTON = By.xpath("/html/body/nav/div/button");

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> tableRows() {
        List<WebElement> rows = driver.findElements(TABLE_ROWS);
        List<String> result = new ArrayList<>();
        for (WebElement i : rows) {
            String text = i.findElement(By.xpath("./td[1]")).getText();
            result.add(text);
        }
        return result;
    }

    public List<String> tableSecRows() {
        List<WebElement> rows = driver.findElements(TABLE_ROWS2);
        List<String> result = new ArrayList<>();
        for (WebElement i : rows) {
            String text = i.findElement(By.xpath("./td[2]")).getText();
            result.add(text);
        }
        return result;
    }

    public List<String> tableNameRows() {
        List<WebElement> rows = driver.findElements(TABLE_HEAD);
        List<String> result = new ArrayList<>();
        for (WebElement i : rows) {
            String text = i.findElement(By.xpath("./th")).getText();
            result.add(text);
        }
        return result;
    }

    public List<String> tableColNames() {
        List<WebElement> rows = driver.findElements(TABLE_NAMES);
        List<String> result = new ArrayList<>();
        for (WebElement i : rows) {
            String text = i.getText();
            result.add(text);
        }
        return result;
    }

    public String[] checksRadios() {
        String[] result = new String[2];
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            click(COLLAPSE_BUTTON);
        } catch (Exception e) {
            System.out.println("Browser is quite big, not as a mobil");}
        try{
            click(FORMS_MENU);
        } catch (Exception e) {
            Allure.addAttachment("First click image", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        try {
            wait.until(ExpectedConditions.attributeToBe(FORMS_MENU, "aria-expanded", "true"));
            Actions action = new Actions(driver);
            action.sendKeys(Keys.PAGE_DOWN).build().perform();
            click(CHECKS);
        } catch (Exception e) {
 //           driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios");
            Allure.addAttachment("Second click image", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } try {

      //      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ANCHOR)));
       //     wait.until(ExpectedConditions.textToBe(TITLE_CHECKS,"Checks and radios"));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(CHECKBOX)));
            result[0] = driver.findElement(TITLE_CHECKS).getText();
            result[1] = driver.getCurrentUrl();
        } catch (Exception e) {
            Allure.addAttachment("Third step image", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        return result;
    }

    private void click(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
}
