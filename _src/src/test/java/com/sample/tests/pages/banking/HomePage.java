package com.sample.tests.pages.banking;

import org.openqa.selenium.WebDriver;

import com.sample.framework.kdt.Alias;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

@Alias("Banking Home")
public class HomePage extends Page {

    public HomePage(WebDriver driverValue) {
        super(driverValue);
        // TODO Auto-generated constructor stub
    }

    @Alias("Customer Login")
    @FindBy(locator = "//button[text() = 'Customer Login']")
    public Control buttonCustomerLogin;
    
    @Alias("Banking Manager Login")
    @FindBy(locator = "//button[text() = 'Bank Manager Login']")
    public Control buttonBankManagerLogin;
    
    public CustomerCommonPage loginAsCustomer(String name) throws Exception {
        CustomerLoginPage loginPage = this.buttonCustomerLogin.clickAndWaitFor(CustomerLoginPage.class);
        loginPage.selectUser.selectByText(name);
        return loginPage.buttonLogin.clickAndWaitFor(CustomerCommonPage.class);
    }
}
