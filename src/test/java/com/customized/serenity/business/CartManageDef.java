package com.customized.serenity.business;

import com.customized.serenity.commons.StepActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;

import java.util.List;
import java.util.Map;

import static com.customized.serenity.configs.LocatorResolver.resolve;

public class CartManageDef {
    @Steps
    StepActions stepActions;

    @When("user add product to cart")
    public void userAddProductToCart(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            stepActions.clickOnElement(resolve("productDetailPage.lblProductNameInListProduct").of(row.get("productName")));
            stepActions.clickOnElement(resolve("productDetailPage.imgProductColor").of(row.get("productColor")));
            selectQuantityOfProduct(Integer.valueOf(row.get("quantity")));
            stepActions.clickOnElement(resolve("productDetailPage.btnAddToCart"));
            stepActions.pressBack();
        }
    }

    @When("user verify product add to cart")
    public void userVerifyProductAddToCart(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            stepActions.verifyStatusElement(resolve("cartPage.lblProductByName").of(row.get("productName")), "visible");
            stepActions.verifyStatusElement(resolve("cartPage.lblProductQuantity").of(row.get("quantity")), "visible");
        }
    }

    private void selectQuantityOfProduct(Integer quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (quantity > 1) {
            for (int i = 1; i < quantity; i++) {
                stepActions.clickOnElement(resolve("productDetailPage.btnIncreaseQuantity"));
            }
        } else if (quantity == 0) {
            stepActions.clickOnElement(resolve("productDetailPage.btnReduceQuantity"));
        }
    }
}
