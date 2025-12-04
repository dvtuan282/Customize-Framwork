package com.customized.serenity.bussiness;

import com.customized.serenity.commons.StepActions;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import static com.customized.serenity.configs.LocatorResolver.elementAs;


public class LoginDefs {
    @Steps
    StepActions stepActions;

    @When("user login with username {str} and password {str}")
    public void userLoginWithAccount(String username, String password) {
        stepActions.enterTheValueInto(username, elementAs("loginPage.txtUsername"));
        stepActions.enterTheValueInto(password,elementAs("loginPage.txtPassword"));
        stepActions.clickToElement(elementAs("loginPage.btnLogin"));
    }
}
