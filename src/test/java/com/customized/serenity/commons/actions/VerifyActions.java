package com.customized.serenity.commons.actions;

import com.customized.serenity.commons.BasePage;
import org.junit.Assert;

import java.time.Duration;

public class VerifyActions extends BasePage {
    /**
     * So sánh trạng thái của element trên trang ui
     *
     * @param elementXpath của element
     * @param status trạng thái mong muốn: visible, disabled, selected, present
     */
    public void verifyStatusElement(Object elementXpath, String status) {
        boolean isStatus = false;
        switch (status) {
            case "visible":
                isStatus = findElementByXpath(elementXpath.toString()).waitUntilVisible().isVisible();
                break;
            case "disabled":
                isStatus = findElementByXpath(elementXpath.toString()).withTimeoutOf(Duration.ofSeconds(30)).isDisabled();
                break;
            case "selected":
                isStatus = findElementByXpath(elementXpath.toString()).withTimeoutOf(Duration.ofSeconds(30)).isSelected();
                break;
            case "present":
                isStatus = findElementByXpath(elementXpath.toString()).withTimeoutOf(Duration.ofSeconds(30)).isPresent();
                break;
            default:
                logger.warn("Status '{}' not available", status);
        }
        Assert.assertTrue("verify element " + status, isStatus);
        logger.info("verify {} is {}: {}", elementXpath.toString(), status, isStatus);
    }


    /**
     * Lấy text của element và so sánh với text mong muốn
     *
     * @param elementXpath của element
     * @param expectedText text mong muốn
     */
    public void verifyTextElementWithExpected(Object elementXpath, String expectedText) {
        String actualText = findElementByXpath(elementXpath.toString()).waitUntilVisible().getText();
        Assert.assertEquals(expectedText, actualText);
        logger.info("verify text element {} with {}", elementXpath.toString(), expectedText);
    }
}
