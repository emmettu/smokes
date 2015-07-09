package Robots;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by eunderhi on 09/07/15.
 * class to enter a username and password
 * into a pop up text field. Because awt.robots
 * need KeyEvents instead of strings in order to work
 * I've hardcoded the username and password as admin
 * qwer#1234.
 */
public class KeyPresser {

    private Robot robot;
    private final static StringSelection USERNAME = new StringSelection("admin");
    private final static StringSelection PASSWORD = new StringSelection("qwer#1234");

    public KeyPresser() {
        makeRobot();
    }
    private void makeRobot() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public void enterUsernameAndPassword() {
        pasteWord(USERNAME);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        pasteWord(PASSWORD);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    private void pasteWord(StringSelection word) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(word, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        waitForPaste();
    }
    private void waitForPaste() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
