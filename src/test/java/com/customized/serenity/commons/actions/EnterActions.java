package com.customized.serenity.commons.actions;

import com.customized.serenity.commons.BasePage;

public class EnterActions extends BasePage {

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
}
