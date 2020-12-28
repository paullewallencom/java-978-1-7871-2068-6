package com.sample.tests.junit;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

public class TestCommon {

    public TestCommon() {
        // TODO Auto-generated constructor stub
    }
    protected SearchPage searchPage;
    protected SearchResultsPage searchResultsPage;
    
    @Before
    public void setUp() throws Exception {
        Configuration.load();
        Configuration.print();
        
        System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
        
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformVersion", Configuration.get("app_platformVersion"));
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", new File(Configuration.get("app_path")).getAbsolutePath());
        cap.setCapability("deviceName", "Any");
        cap.setCapability("commandTimeout", "60");
        if (Configuration.platform().isWeb()) {
            Driver.add(Configuration.get("browser"), cap);
        } else {
            Driver.add(Configuration.get("driver_url"), Configuration.get("browser"), cap);
        }
        searchPage = PageFactory.init(SearchPage.class);
        searchPage.navigate();
    }
    @After
    public void tearDown() {
        Driver.current().quit();
    }
}
