
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class MainPage {
    private final By TABLE_HEAD = By.xpath("/html/body/div[2]/main/div[3]/div[11]/table/tbody/tr");
    private final By TABLE_ROWS = By.xpath("/html/body/div[2]/main/div[3]/div[1]/table/tbody/tr");
    private final By TABLE_ROWS2 = By.xpath("/html/body/div[2]/main/div[3]/div[46]/table/tbody/tr");
    private final By TABLE_NAMES = By.xpath("/html/body/div[2]/main/div[3]/div[58]/table/thead/tr/th");

    WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> tableRows() {
        List<WebElement> rows = driver.findElements(TABLE_ROWS);
        List<String> result = new ArrayList<>();
        for (WebElement i:rows) {
            String text = i.findElement(By.xpath("./td[1]")).getText();
            result.add(text);
        }
            return result;
    }

    public List<String> tableSecRows() {
        List<WebElement> rows = driver.findElements(TABLE_ROWS2);
        List<String> result = new ArrayList<>();
        for (WebElement i:rows) {
            String text = i.findElement(By.xpath("./td[2]")).getText();
            result.add(text);
        }
        return result;
    }

    public List<String> tableNameRows() {
        List<WebElement> rows = driver.findElements(TABLE_HEAD);
        List<String> result = new ArrayList<>();
        for (WebElement i:rows) {
            String text = i.findElement(By.xpath("./th")).getText();
            result.add(text);
        }
        return result;
    }

    public List<String> tableColNames() {
        List<WebElement> rows = driver.findElements(TABLE_NAMES);
        List<String> result = new ArrayList<>();
        for (WebElement i:rows) {
            String text = i.getText();
            result.add(text);
        }
        return result;
    }
}
