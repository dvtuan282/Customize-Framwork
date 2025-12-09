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
    public void getByAttributeOfElementAndSaveVariable(String attribute, Object element, String variableName) {
        String valueAttribute = getByAttributeOfElement(attribute, element.toString());
        logger.info("Get {} Of Element {} As {}", element, attribute, valueAttribute);
        Serenity.setSessionVariable(variableName).to(variableName);
    }

    /**
     * Lấy attribute của element
     * @param attribute loại attribute: name, text, checked, value,..
     * @param element cần lấy attribute
     *
     */
    public String getByAttributeOfElement(String attribute, Object element) {
        String valueAttribute = "";
        switch(attribute) {
            case "textContent":
                valueAttribute = findElementByXpath(element.toString()).waitUntilPresent().getTextContent();
                break;
            case "text":
                valueAttribute = findElementByXpath(element.toString()).waitUntilPresent().getText();
                break;
            case "value":
                valueAttribute = findElementByXpath(element.toString()).waitUntilPresent().getValue();
                break;
            case "tagName":
                valueAttribute = findElementByXpath(element.toString()).waitUntilPresent().getTagName();
                break;
            default:
                logger.info("Attribute error: {}", attribute);
                break;
        }
        return valueAttribute;
    }
}
