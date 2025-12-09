package com.customized.serenity.business;

import com.customized.serenity.commons.StepActions;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import static com.customized.serenity.configs.LocatorResolver.resolve;


public class LoginDefs {
    @Steps
    StepActions stepActions;

    @When("user login with username {str} and password {str}")
    public void userLoginWithAccount(String username, String password) {
        stepActions.enterTheValueInto(username, resolve("loginPage.txtUsername"));
        stepActions.enterTheValueInto(password,resolve("loginPage.txtPassword"));
        stepActions.clickToElement(resolve("loginPage.btnLogin"));
    }
}
