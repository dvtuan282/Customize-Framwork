package com.customized.serenity.commons.actions;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.time.Duration;

public class VerifyActions extends PageObject {
    private static final Logger logger = LogManager.getLogger(VerifyActions.class);


    /**
     * So sánh trạng thái của element trên trang ui
     *
     * @param elementXpath của element
     * @param status trạng thái mong muốn: visible, disabled, selected, present
     */
    public void verifyStatusElement(String elementXpath, String status) {
        boolean isStatus = false;
        switch (status) {
            case "visible":
                isStatus = findElementByXpath(elementXpath).waitUntilVisible().isVisible();
                break;
            case "disabled":
                isStatus = findElementByXpath(elementXpath).withTimeoutOf(Duration.ofSeconds(30)).isDisabled();
                break;
            case "selected":
                isStatus = findElementByXpath(elementXpath).withTimeoutOf(Duration.ofSeconds(30)).isSelected();
                break;
            case "present":
                isStatus = findElementByXpath(elementXpath).withTimeoutOf(Duration.ofSeconds(30)).isPresent();
                break;
            default:
                logger.warn("Status '{}' not available", status);
        }
        Assert.assertTrue("verify element " + status, isStatus);
        logger.info("verify {} is {}: {}", elementXpath, status, isStatus);
    }


    /**
     * Lấy text của element và so sánh với text mong muốn
     *
     * @param elementXpath của element
     * @param expectedText text mong muốn
     */
    public void verifyTextElementWithExpected(String elementXpath, String expectedText) {
        String actualText = findElementByXpath(elementXpath).waitUntilVisible().getText();
        Assert.assertEquals(expectedText, actualText);
        logger.info("verify text element {} with {}", elementXpath, expectedText);
    }

    /**
     * Tìm WebElementFacade theo tên
     *
     * @param elementXpath của element
     * @return WebElementFacade
     */
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
