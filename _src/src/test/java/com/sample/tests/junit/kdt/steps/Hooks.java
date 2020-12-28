package com.sample.tests.junit.kdt.steps;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Context;
import com.sample.framework.Driver;
import com.sample.framework.utils.jira.JiraUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        Context.clearCurrent();
        Configuration.load();
        Configuration.print();
        
        System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
        
        Assert.assertTrue("Only web platforms are supported by this test", Configuration.platform().isWeb());
        
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
    }
    @After
    public void afterScenario(Scenario scenario) throws Exception {
    	Driver.current().quit();
  		JiraUtils.handleError("http://localhost:9090", "mkolisnyk", "password", scenario);
    }
}
