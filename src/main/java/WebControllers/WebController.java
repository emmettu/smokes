package WebControllers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by eunderhi on 09/07/15. Basically a wrapper
 * class for the Selenium webdriver
 */
public class WebController {

    private WebDriver driver;
    private final static String HOSTNAME = "http://localhost:9990/console/App.html";
    private final static String DEPLOYMENT_URL = "http://localhost:8080/mass-bugzilla-modifier";
    private final static String[] SUB_URLS = {
            "#deployments",
            "#datasources",
            "#interfaces",
            "#socket-bindings",
            "#path",
            "#properties",
            "#server-overview",
            "#vm",
            "#environment",
            "#logfiles",
            "#ds-metrics",
            "#jpa-metrics",
            "#naming",
            "#tx-logs",
            "#tx-metrics",
            "#web-metrics",
            "#webservice-runtime",
            "#role-assignment",
            "#patching"
    };

    public WebController() {
        setUpDriver();
    }

    private void setUpDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(HOSTNAME);
    }

    public void testWebsite() {
        goToAllWebPages();
    }
    private void goToAllWebPages() {
        for(int i = 0; i < SUB_URLS.length; i++) {
            driver.get(HOSTNAME+SUB_URLS[i]);
        }
        driver.get(DEPLOYMENT_URL);
    }
}
