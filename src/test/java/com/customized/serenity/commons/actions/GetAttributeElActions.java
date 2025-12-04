package com.customized.serenity.commons.actions;

import com.customized.serenity.commons.BasePage;
import net.serenitybdd.core.Serenity;

public class GetAttributeElActions extends BasePage {

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
}
