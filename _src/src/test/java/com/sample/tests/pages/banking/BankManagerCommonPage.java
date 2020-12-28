package com.sample.tests.pages.banking;

import org.openqa.selenium.WebDriver;

import com.sample.framework.kdt.Alias;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

@Alias("Banking Manager Home")
public class BankManagerCommonPage extends Page {

    public BankManagerCommonPage(WebDriver driverValue) {
        super(driverValue);
        // TODO Auto-generated constructor stub
    }

    @Alias("Add Customer")
    @FindBy(locator = "//button[contains(text(),'Add Customer')]")
    public Control buttonAddCustomer;
    @Alias("Open Account")
    @FindBy(locator = "//button[contains(text(),'Open Account')]")
    public Control buttonOpenAccount;
    @Alias("Customers")
    @FindBy(locator = "//button[contains(text(),'Customers')]")
    public Control buttonCustomers;
}
