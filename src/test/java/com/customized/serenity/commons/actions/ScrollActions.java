package com.customized.serenity.commons.actions;

import io.appium.java_client.AppiumDriver;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class ScrollActions extends PageObject {
    private static final Logger logger = LogManager.getLogger(ScrollActions.class);

    protected AppiumDriver getAppiumDriver() {
        WebDriver driver = getDriver();
        return driver instanceof WebDriverFacade
                ? (AppiumDriver) ((WebDriverFacade) driver).getProxiedDriver()
                : (AppiumDriver) driver;
    }

    /**
     * Scroll by direction
     *
     * @param direction: "up", "down", "left", "right"
     */
    public void scrollByDirection(String direction) {
        Dimension size = getAppiumDriver().manage().window().getSize();

        int width = size.width;
        int height = size.height;

        int startX, startY, endX, endY;

        switch (direction.toLowerCase()) {
            case "up":
                startX = width / 2;
                startY = (int) (height * 0.75);
                endX = width / 2;
                endY = (int) (height * 0.25);
                break;

            case "down":
                startX = width / 2;
                startY = (int) (height * 0.25);
                endX = width / 2;
                endY = (int) (height * 0.75);
                break;

            case "left":
                startX = (int) (width * 0.8);
                startY = height / 2;
                endX = (int) (width * 0.2);
                endY = height / 2;
                break;

            case "right":
                startX = (int) (width * 0.2);
                startY = height / 2;
                endX = (int) (width * 0.8);
                endY = height / 2;
                break;

            default:
                throw new IllegalArgumentException("Direction must be: up, down, left, right");
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        (getAppiumDriver()).perform(Collections.singletonList(swipe));
    }

    /**
     * cuộn đến khi nào phần tử được xuất hiện trên trang
     * hoặc max số lần cuộn 10 nếu không tìm được element thì dừng lại
     *
     * @param elementXpath the XPath of the element to be checked for visibility
     * @param direction    the direction to scroll: "up", "down", "left", or "right"
     */
    public void scrollElementVisibleWithByDirection(String elementXpath, String direction) {
        int maxScroll = 10;
        int currentScroll = 0;
        boolean isElementVisible = findElementByXpath(elementXpath).isVisible();

        while (currentScroll < maxScroll && !isElementVisible) {
            scrollByDirection(direction);
            currentScroll++;
            isElementVisible = findElementByXpath(elementXpath).isVisible();
        }
    }

    /**
     * Scroll đến cuối trang hoặc đầu trang dựa trên page source
     *
     * @param direction "up" để cuộn lên đầu, "down" để cuộn xuống cuối
     */
    public void scrollToEndOrTop(String direction) {
        String previousPageSource;
        String currentPageSource = "";

        do {
            previousPageSource = currentPageSource;
            scrollByDirection(direction);
            currentPageSource = getAppiumDriver().getPageSource();

        } while (!currentPageSource.equals(previousPageSource));
    }


    public WebElementFacade findElementByXpath(String elementXpath) {
        WebElementFacade element = find(By.xpath(elementXpath));
        if (element == null) {
            String msg = "Element not available: " + elementXpath;
            logger.warn(msg);
            throw new RuntimeException(msg);
        }
        return element;
    }
}
