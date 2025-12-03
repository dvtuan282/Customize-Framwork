package com.customized.serenity.commons.actions;

import com.customized.serenity.configs.LocatorResolver;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class EnterActions extends PageObject {

    private static final Logger logger = LogManager.getLogger(EnterActions.class);

    /**
     * Enter value vào element theo xpath
     * @param elementXpath xpath của element
     */
    public void enterTheValueInto(String value, String elementXpath) {
        try {
            findElementByXpath(elementXpath).waitUntilVisible().sendKeys(value);
            logger.info("Enter the value {} to element: '{}'", value,elementXpath);

        } catch (Exception e) {
            logger.error("ERROR: '{}' - DETAILS: {}", elementXpath, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Enter value vào element theo xpath after clean input
     * @param elementXpath xpath của element
     */
    public void enterTheValueIntoAfterClean(String value, String elementXpath) {
        try {
            findElementByXpath(elementXpath).waitUntilVisible().clear();
            findElementByXpath(elementXpath).sendKeys(value);
            logger.info("Enter the value {} to element after clean: '{}'", value,elementXpath);

        } catch (Exception e) {
            logger.error("ERROR: '{}' - DETAILS: {}", elementXpath, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Clean input by xpath
     *
     * @param elementXpath của element
     */
    public void cleanInput(String elementXpath) {
        try {
            findElementByXpath(elementXpath).waitUntilVisible().clear();
            logger.info("Clean input: '{}'", elementXpath);

        } catch (Exception e) {
            logger.error("ERROR: '{}' - DETAILS: {}", elementXpath, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Tìm WebElementFacade theo tên
     *
     * @param elementXpath của element
     * @return WebElementFacade
     */
    public WebElementFacade findElementByXpath(String elementXpath) {
        WebElementFacade element = find(By.xpath(
                LocatorResolver.resolve(elementXpath)
        ));
        if (element == null) {
            String msg = "Element not available: " + elementXpath;
            logger.warn(msg);
            throw new RuntimeException(msg);
        }
        return element;
    }
}
