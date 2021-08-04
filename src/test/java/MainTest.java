import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {
    private WebDriver driver;

    @BeforeAll
    public static void Init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("incognito");
        options.addArguments("--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://getbootstrap.com/docs/5.0/content/tables/");
    }

    @Test
    public void testTableRows() {
        MainPage mainPage = new MainPage(driver);
        List<String> expected = new ArrayList<>();
        expected.add("Mark");
        expected.add("Jacob");
        expected.add("Larry the Bird");
        Assertions.assertEquals(expected, mainPage.tableRows());
    }

    @Test
    public void testTableSecRows() {
        MainPage mainPage = new MainPage(driver);
        List<String> expected = new ArrayList<>();
        expected.add("Otto");
        expected.add("Thornton");
        expected.add("@twitter");
        Assertions.assertEquals(expected, mainPage.tableSecRows());
    }

    @Test
    public void testTableNameRows() {
        MainPage mainPage = new MainPage(driver);
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        Assertions.assertEquals(expected, mainPage.tableNameRows());
    }

    @Test
    public void testTableColNames() {
        MainPage mainPage = new MainPage(driver);
        List<String> expected = new ArrayList<>();
        expected.add("#");
        expected.add("First");
        expected.add("Last");
        expected.add("Handle");
        Assertions.assertEquals(expected, mainPage.tableColNames());
    }

    @Test
    public void testCheckRadioClick(){
        MainPage mainPage = new MainPage(driver);
        String expected = "Checks and radios";
        Assertions.assertEquals(expected, mainPage.checksRadios()[0]);
    }

    @Test
    public void testCheckRadioUrl(){
        MainPage mainPage = new MainPage(driver);
        String expected = "https://getbootstrap.com/docs/5.0/forms/checks-radios/";
        Assertions.assertEquals(expected, mainPage.checksRadios()[1]);
    }

    @AfterEach
    public void closing() {
        if (driver != null) {
            driver.quit();
        }
    }

}
