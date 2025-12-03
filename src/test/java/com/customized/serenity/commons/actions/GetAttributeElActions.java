package com.customized.serenity.commons.actions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GetAttributeElActions extends PageObject {
    private static final Logger logger = LogManager.getLogger(GetAttributeElActions.class);

    /**
     * Lấy attribute của element
     * @param attribute loại attribute: name, text, checked, value,..
     * @param variableName tên của biến lưu giá trị
     * @param element cần lấy attribute
     *
     */
    public void getByAttributeOfElementAndSaveVariable(String attribute, String element, String variableName) {
        String valueAttribute = getByAttributeOfElement(attribute, element);
        logger.info("Get {} Of Element {} As {}", element, attribute, valueAttribute);
        Serenity.setSessionVariable(variableName).to(variableName);
    }

    /**
     * Lấy attribute của element
     * @param attribute loại attribute: name, text, checked, value,..
     * @param element cần lấy attribute
     *
     */
    public String getByAttributeOfElement(String attribute, String element) {
        String valueAttribute = "";
        switch(attribute) {
            case "textContent":
                valueAttribute = findElementByXpath(element).waitUntilPresent().getTextContent();
                break;
            case "text":
                valueAttribute = findElementByXpath(element).waitUntilPresent().getText();
                break;
            case "value":
                valueAttribute = findElementByXpath(element).waitUntilPresent().getValue();
                break;
            case "tagName":
                valueAttribute = findElementByXpath(element).waitUntilPresent().getTagName();
                break;
            default:
                logger.info("Attribute error: {}", attribute);
                break;
        }
        return valueAttribute;
    }


    /**
     * Tìm WebElementFacade theo xpath
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
