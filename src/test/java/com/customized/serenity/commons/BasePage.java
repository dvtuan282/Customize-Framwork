package com.customized.serenity.commons;

import com.customized.serenity.commons.actions.ScrollActions;
import com.customized.serenity.configs.DynamicLocator;
import io.appium.java_client.AppiumDriver;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends PageObject {

    protected final Logger logger = LogManager.getLogger(getClass());

    protected <T extends AppiumDriver> T getDriverInstance(Class<T> driverClass) {
        WebDriver driver = getDriver();
        return driver instanceof WebDriverFacade
                ? driverClass.cast(((WebDriverFacade) driver).getProxiedDriver())
                : driverClass.cast(driver);
    }

    protected AppiumDriver getAppiumDriver() {
        WebDriver driver = getDriver();
        return driver instanceof WebDriverFacade
                ? (AppiumDriver) ((WebDriverFacade) driver).getProxiedDriver()
                : (AppiumDriver) driver;
    }


    /**
     * Tìm WebElementFacade theo tên
     *
     * @param elementXpath của element
     * @return WebElementFacade
     */
    public WebElementFacade findElementByXpath(Object elementXpath) {
        WebElementFacade element = find(By.xpath(elementXpath.toString()));
        if (element == null) {
            String msg = "Element not available: " + elementXpath;
            logger.warn(msg);
            throw new RuntimeException(msg);
        }
        return element;
    }
}
