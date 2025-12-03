package com.customized.serenity.bussiness;

import com.customized.serenity.commons.StepActions;
import com.customized.serenity.configs.LocatorResolver;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;

public class LoginDefs {
    @Steps
    StepActions stepActions;

    @When("user login with username {str} and password {str}")
    public void userLoginWithAccount(String username, String password) {
        stepActions.enterTheValueInto(username,"loginPage.username");
        stepActions.enterTheValueInto(password,"loginPage.passwordField");
        stepActions.clickToElement("loginPage.username");
    }
}
