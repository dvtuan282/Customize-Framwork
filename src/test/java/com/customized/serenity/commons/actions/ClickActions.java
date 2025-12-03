package com.customized.serenity.commons.actions;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ClickActions extends PageObject {

    private static final Logger logger = LogManager.getLogger(ClickActions.class);

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

    /**
     * Tìm WebElementFacade theo tên
     *
     * @param elementXpath tên của element
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
