package com.sample.tests.junit;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.tests.pages.banking.AddCustomerPage;
import com.sample.tests.pages.banking.BankManagerCommonPage;
import com.sample.tests.pages.banking.CustomersPage;
import com.sample.tests.pages.banking.HomePage;

public class BankingTest {

    public BankingTest() {
        // TODO Auto-generated constructor stub
    }

    protected HomePage home;
    protected BankManagerCommonPage bankManagerMenu;
    protected AddCustomerPage addCustomer;
    protected CustomersPage customers;
    
    @Before
    public void setUp() throws Exception {
        Configuration.load();
        Configuration.print();
        
        System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
        
        Assert.assertTrue("Only web platforms are supported by this test", Configuration.platform().isWeb());
        
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        Driver.current().get("http://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }
    
    @After
    public void tearDown() {
        Driver.current().quit();
    }
    @Test
    public void testAddNewCustomer() throws Exception {
        home = PageFactory.init(HomePage.class);
        bankManagerMenu = home.buttonBankManagerLogin.clickAndWaitFor(BankManagerCommonPage.class);
        
        customers = bankManagerMenu.buttonCustomers.clickAndWaitFor(CustomersPage.class);
        Assert.assertTrue(customers.tableCustomers.isNotEmpty());
        int rows = customers.tableCustomers.getItemsCount();
        
        addCustomer = customers.buttonAddCustomer.clickAndWaitFor(AddCustomerPage.class);
        Assert.assertTrue(addCustomer.allElementsExist(new Control[] {
            addCustomer.editFirstName,
            addCustomer.editLastName,
            addCustomer.editPostCode,
            addCustomer.buttonSubmit
        }));
        Assert.assertTrue(addCustomer.anyOfElementsExist(new Control[] {
                addCustomer.editFirstName,
                addCustomer.editLastName,
                addCustomer.editPostCode,
                addCustomer.buttonSubmit
            }));
        Thread.sleep(1000);
        addCustomer.editFirstName.setText("Test");
        addCustomer.editLastName.setText("User");
        addCustomer.editPostCode.setText("WWW99");
        addCustomer.buttonSubmit.click();
        addCustomer.getDriver().switchTo().alert().accept();

        customers = addCustomer.buttonCustomers.clickAndWaitFor(CustomersPage.class);
        Assert.assertEquals(rows + 1, customers.tableCustomers.getItemsCount());
        Assert.assertEquals("Test", customers.tableCustomers.getSubItem("First Name", rows).getText());
        Assert.assertEquals("User", customers.tableCustomers.getSubItem("Last Name", rows).getText());
        Assert.assertEquals("WWW99", customers.tableCustomers.getSubItem("Post Code", rows).getText());
        
        customers.tableCustomers.getSubItem("Delete Customer", rows).click();
        Assert.assertTrue(customers.tableCustomers.isNotEmpty());
        Assert.assertEquals(rows, customers.tableCustomers.getItemsCount());
    }
}
