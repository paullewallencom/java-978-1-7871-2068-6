package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.Configuration;
import com.sample.framework.Platform;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.SelectList;
import com.sample.tests.controls.LocationLookupEdit;

public class SearchPage extends Page {

    @FindBy(locator = "search_searchInput", platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "ss")
    public LocationLookupEdit editDestination;
    
    @FindBy(locator = "checkincell", platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "css=i.sb-date-field__chevron.bicon-downchevron")
    public Control checkoutDayExpand;
    
    @FindBy(locator = "xpath=(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]",
                platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]")
    public Control checkoutDayToday;
    
    @FindBy(locator = "business_purpose_leisure", platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[2]")
    public Control radioLeisure;
    
    @FindBy(locator = "business_purpose_business", platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[1]")
    public Control radioBusiness;
    
    @FindBy(locator = "", platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "xpath=(//input[@name='nflt'])[2]")
    public Control radioHotels;
    
    @FindBy(locator = "adult_count", platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "group_adults")
    public SelectList  selectAdultsNumber;

    @FindBy(locator = "search_search", platform = Platform.ANRDOID_NATIVE)
    @FindBy(locator = "//button[@type='submit']")
    public Control buttonSubmit;
    
    public SearchPage(WebDriver driverValue) {
        super(driverValue);
    }

    @Override
    public Page navigate() {
        if (Configuration.platform().isWeb()) {
            String baseUrl = Configuration.get("url");
            this.getDriver().get(baseUrl);
        }
        return this;
    }
    public void checkInToday() {
        checkoutDayExpand.click();
        checkoutDayToday.click();
    }
    public void selectTravelFor(boolean isLeisure) {
        if (isLeisure) {
            radioLeisure.click();
        } else {
            radioBusiness.click();
        }
    }
}
