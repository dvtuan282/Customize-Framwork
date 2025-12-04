package com.customized.serenity.commons.actions;

import com.customized.serenity.commons.BasePage;

public class ClickActions extends BasePage {


    /**
     * Click vào element theo xpath
     * @param elementXpath xpath của element
     */
    public void clickToElement(String elementXpath) {
        try {
            findElementByXpath(elementXpath).waitUntilClickable().click();
            logger.info("Click to element: '{}'", elementXpath);

        } catch (Exception e) {
            logger.error("ERROR: '{}' - DETAILS: {}", elementXpath, e.getMessage(), e);
            throw e;
        }
    }
}
