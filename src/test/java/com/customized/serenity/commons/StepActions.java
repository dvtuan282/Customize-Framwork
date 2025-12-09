package com.customized.serenity.commons;

import com.customized.serenity.commons.actions.*;
import net.serenitybdd.annotations.Step;

public class StepActions {

    private ClickActions clickActions;

    private EnterActions enterActions;

    private GetAttributeElActions getAttributeElActions;

    private VerifyActions verifyActions;

    private ScrollActions scrollActions;

    @Step("click to element {0}")
    public void clickToElement(Object elementXpath) {
        clickActions.clickToElement(elementXpath);
    }

    @Step("enter the value {0} into {1}")
    public void enterTheValueInto(String value, Object elementXpath) {
        enterActions.enterTheValueInto(value, elementXpath);
    }

    @Step("enter the value {0} into {1} after clean text")
    public void enterTheValueIntoAfterClean(String value, Object elementXpath) {
        enterActions.enterTheValueIntoAfterClean(value, elementXpath);
    }

    @Step("clean data to element {0}")
    public void cleanDataToElement(Object elementXpath) {
        enterActions.cleanInput(elementXpath);
    }

    @Step("get attribute {0} of element {1} and save to variable {2}")
    public void getByAttributeOfElementAndSaveVariable(String attribute, Object elementXpath, String variableName) {
        getAttributeElActions.getByAttributeOfElementAndSaveVariable(attribute, elementXpath, variableName);
    }

    @Step("scroll by direction {0}")
    public void scrollByDirection(String direction) {
        scrollActions.scrollByDirection(direction);
    }

    @Step("scroll element {0} visible with by direction {1}")
    public void scrollElementVisibleWithByDirection(Object element, String direction) {
        scrollActions.scrollElementVisibleWithByDirection(element, direction);
    }

    @Step("scroll to end or top {0}")
    public void scrollToEndOrTop(String direction) {
        scrollActions.scrollToEndOrTop(direction);
    }

    @Step("verify element {0} is {1}")
    public void verifyStatusElement(Object element, String status) {
        verifyActions.verifyStatusElement(element, status);
    }

    @Step("verify text element {0} with expected text {1}")
    public void verifyTextElementWithExpected(Object element, String expectedText) {
        verifyActions.verifyTextElementWithExpected(element, expectedText);
    }

}
