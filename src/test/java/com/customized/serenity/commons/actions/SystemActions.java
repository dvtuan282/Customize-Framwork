package com.customized.serenity.commons.actions;

import com.customized.serenity.commons.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SystemActions extends BasePage {

    public void pressBack() {
        ((AndroidDriver) getAppiumDriver()).pressKey(
                new KeyEvent(AndroidKey.BACK)
        );
    }
}
