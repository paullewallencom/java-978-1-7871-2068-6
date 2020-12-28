package com.sample.tests.junit.odt;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Context;
import com.sample.framework.Driver;
import com.sample.framework.odt.ODTTestStep;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.tests.pages.banking.AddCustomerPage;
import com.sample.tests.pages.banking.BankManagerCommonPage;
import com.sample.tests.pages.banking.CustomersPage;
import com.sample.tests.pages.banking.HomePage;

public class BankingODTTestSteps {

    public BankingODTTestSteps() {
        // TODO Auto-generated constructor stub
    }

    public class SetupStep extends ODTTestStep {

        @Override
        public void stepBody() throws Exception {
            Configuration.load();
            Configuration.print();
            
            System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
            System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
            
            Assert.assertTrue("Only web platforms are supported by this test", Configuration.platform().isWeb());
            
            DesiredCapabilities cap = new DesiredCapabilities();
            Driver.add(Configuration.get("browser"), cap);
            
        }
    }
    public class AfterStep extends ODTTestStep {

        @Override
        public void stepBody() throws Exception {
            Driver.current().quit();
        }
    }
    public class GoToBankManagerStep extends ODTTestStep {

        @Override
        public void stepBody() throws Exception {
            Driver.current().get("http://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
            HomePage home = PageFactory.init(HomePage.class);
            home.buttonBankManagerLogin.clickAndWaitFor(BankManagerCommonPage.class);
        }
        
    }
    public class GoToBankManagerTabPageStep extends ODTTestStep {
        private Class<? extends BankManagerCommonPage> pageClass;
        public GoToBankManagerTabPageStep(Class<? extends BankManagerCommonPage> pageClassValue) {
            this.pageClass = pageClassValue;
        }
        @Override
        public void stepBody() throws Exception {
            PageFactory.init(this.pageClass).navigate();
        }
    }
    public class VerifyCustomerListNotEmpty extends ODTTestStep {

        @Override
        public void stepBody() throws Exception {
            CustomersPage customers = PageFactory.init(CustomersPage.class);
            Assert.assertTrue(customers .tableCustomers.isNotEmpty());
        }
    }
    public class GetCustomersCount extends ODTTestStep {

        @Override
        public void stepBody() throws Exception {
            GoToBankManagerTabPageStep navigateStep = new GoToBankManagerTabPageStep(CustomersPage.class);
            navigateStep.run();
            CustomersPage customers = PageFactory.init(CustomersPage.class);
            Context.put("Customers Count", customers.tableCustomers.getItemsCount());
        }
    }
    public class AddNewCustomer extends ODTTestStep {
        private String firstName;
        private String lastName;
        private String postCode;

        public AddNewCustomer(String firstName, String lastName, String postCode) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.postCode = postCode;
        }
        @Override
        public void stepBody() throws Exception {
            Thread.sleep(1000);
            AddCustomerPage addCustomer = PageFactory.init(AddCustomerPage.class);
            addCustomer .editFirstName.setText(firstName);
            addCustomer.editLastName.setText(lastName);
            addCustomer.editPostCode.setText(postCode);
            addCustomer.buttonSubmit.click();
            addCustomer.getDriver().switchTo().alert().accept();
        }
        
    }
    public class VerifyLastCustomerData extends ODTTestStep {
        private String firstName;
        private String lastName;
        private String postCode;

        public VerifyLastCustomerData(String firstName, String lastName, String postCode) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.postCode = postCode;
        }
        @Override
        public void stepBody() throws Exception {
            CustomersPage customers = PageFactory.init(CustomersPage.class);
            int rows = customers.tableCustomers.getItemsCount();
            Assert.assertEquals(firstName, customers.tableCustomers.getSubItem("First Name", rows - 1).getText());
            Assert.assertEquals(lastName, customers.tableCustomers.getSubItem("Last Name", rows - 1).getText());
            Assert.assertEquals(postCode, customers.tableCustomers.getSubItem("Post Code", rows - 1).getText());
        }
        
    }
    public class VerifyCustomerCountChangedBy extends ODTTestStep {
        private int shift;
        public VerifyCustomerCountChangedBy(int shift) {
            this.shift = shift;
        }

        @Override
        public void stepBody() throws Exception {
            CustomersPage customers = PageFactory.init(CustomersPage.class);
            Assert.assertEquals((int) Context.get("Customers Count") + this.shift,
                    customers.tableCustomers.getItemsCount());
        }
    }
    public class DeleteLastCustomer extends ODTTestStep {

        @Override
        public void stepBody() throws Exception {
            CustomersPage customers = PageFactory.init(CustomersPage.class);
            customers.tableCustomers.getSubItem("Delete Customer",
                    customers.tableCustomers.getItemsCount() - 1).click();
        }
        
    }
}
