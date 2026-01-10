package com.customized.serenity.commons;

import com.customized.serenity.configs.DataResolver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class StepDefinitions {

    @Steps
    StepActions stepActions;

    @Given("user load data to")
    public void loadData(DataTable table) {
        table.asList().forEach(file -> DataResolver.load(file.trim()));
    }

    @When("user click on {str}")
    public void clickToElement(Object elementLocator) {
        stepActions.clickOnElement(elementLocator);
    }

    @When("user enter {str} into {str}")
    public void enterValueInto(String value, Object elementLocator) {
        stepActions.enterTheValueInto(value, elementLocator);
    }

    @When("user enter {str} into {str} after clean text")
    public void enterValueAfterClean(String value, Object elementLocator) {
        stepActions.enterTheValueIntoAfterClean(value, elementLocator);
    }

    @When("user clear text in {str}")
    public void clearText(Object elementLocator) {
        stepActions.cleanDataToElement(elementLocator);
    }

    @When("user get attribute {str} of {str} and save to variable {str}")
    public void getAttributeAndSave(String attribute, String elementLocator, String variableName) {
        stepActions.getByAttributeOfElementAndSaveVariable(attribute, elementLocator, variableName);
    }

    @When("user scroll by direction {str}")
    public void scrollByDirection(String direction) {
        stepActions.scrollByDirection(direction);
    }

    @When("user scroll to make {str} visible by direction {str}")
    public void scrollElementVisible(String elementLocator, String direction) {
        stepActions.scrollElementVisibleWithByDirection(elementLocator, direction);
    }

    @When("user scroll to {str} of page")
    public void scrollToEndOrTop(String direction) {
        stepActions.scrollToEndOrTop(direction);
    }

    @When("user verify status {str} is {str}")
    public void verifyElementStatus(String elementLocator, String status) {
        stepActions.verifyStatusElement(elementLocator, status);
    }

    @When("user verify text of {str} is {str}")
    public void verifyText(String elementLocator, String expectedText) {
        stepActions.verifyTextElementWithExpected(elementLocator, expectedText);
    }
}
