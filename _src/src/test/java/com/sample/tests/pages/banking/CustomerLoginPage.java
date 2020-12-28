package com.sample.tests.pages.banking;

import org.openqa.selenium.WebDriver;

import com.sample.framework.kdt.Alias;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.SelectList;

@Alias("Customer Login")
public class CustomerLoginPage extends Page {

    public CustomerLoginPage(WebDriver driverValue) {
        super(driverValue);
    }

    @Alias("Select User")
    @FindBy(locator = "userSelect")
    public SelectList selectUser;
    
    @Alias("Login")
    @FindBy(locator = "//button[text() = 'Login']", excludeFromSearch = true)
    public Control buttonLogin;
}
