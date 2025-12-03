package com.customized.serenity.commons;

import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class StepDefinitions {

    @Steps
    StepActions stepActions;

    // CLICK
    @When("user click to {str}")
    public void clickToElement(String elementLocator) {
        stepActions.clickToElement(elementLocator);
    }

    // INPUT TEXT
    @When("user enter {str} into {str}")
    public void enterValueInto(String value, String elementLocator) {
        stepActions.enterTheValueInto(value, elementLocator);
    }

    @When("user enter {str} into {str} after clean text")
    public void enterValueAfterClean(String value, String elementLocator) {
        stepActions.enterTheValueIntoAfterClean(value, elementLocator);
    }

    // CLEAN TEXT
    @When("user clear text in {str}")
    public void clearText(String elementLocator) {
        stepActions.cleanDataToElement(elementLocator);
    }

    // GET ATTRIBUTE & SAVE VARIABLE
    @When("user get attribute {str} of {str} and save to variable {str}")
    public void getAttributeAndSave(String attribute, String elementLocator, String variableName) {
        stepActions.getByAttributeOfElementAndSaveVariable(attribute, elementLocator, variableName);
    }

    // SCROLL
    @When("user scroll by direction {str}")
    public void scrollByDirection(String direction) {
        stepActions.scrollByDirection(direction);
    }

    @When("user scroll to make {str} visible by direction {str}")
    public void scrollElementVisible(String elementLocator, String direction) {
        stepActions.scrollElementVisibleWithByDirection(elementLocator, direction);
    }

    @When("user scroll to{str} of page")
    public void scrollToEndOrTop(String direction) {
        stepActions.scrollToEndOrTop(direction);
    }

    // VERIFY
    @When("user verify {str} is {str}")
    public void verifyElementStatus(String elementLocator, String status) {
        stepActions.verifyStatusElement(elementLocator, status);
    }

    @When("user verify text of {str} is {str}")
    public void verifyText(String elementLocator, String expectedText) {
        stepActions.verifyTextElementWithExpected(elementLocator, expectedText);
    }
}
