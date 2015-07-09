package WebControllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by eunderhi on 09/07/15. Basically a wrapper
 * class for the Selenium webdriver
 */
public class WebController {

    private WebDriver driver;
    private final static String URL = "http://127.0.0.1:9990/console/App.html";

    public void testWebsite() {
    }

    public WebController() {
        setUpDriver();
        driver.get(URL);
    }

    private void setUpDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
