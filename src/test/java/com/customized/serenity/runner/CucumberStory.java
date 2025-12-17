package com.customized.serenity.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "html:target//serenity-reports/report.html", "json:target/serenity-reports/cucumber_report.json", "rerun:rerun/serenity-reports/rerun.txt"},
        glue = {"com.customized.serenity.commons",
                "com.customized.serenity.commons.actions",
                "com.customized.serenity.configs",
                "com.customized.serenity.business"}
)
public class CucumberStory {

}
