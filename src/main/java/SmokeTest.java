import Robots.KeyPresser;
import WebControllers.WebController;

/**
 * Created by eunderhi on 08/07/15. This is an application
 * that logs onto jbossas management and clicks a bunch of stuff in order
 * to see if it breaks.
 */
public class SmokeTest {
    private static WebController controller;

    public static void main(String args[]) {
        launchWebController();
        waitForWebsiteToLoad();
        enterUsernameAndPassword();
        testWebsite();
    }
    private static void launchWebController() {
        controller = new WebController();
    }
    private static void waitForWebsiteToLoad() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void enterUsernameAndPassword() {
        KeyPresser presser = new KeyPresser();
        presser.enterUsernameAndPassword();
    }
    private static void testWebsite() {
        controller.testWebsite();
    }
}
