package com.sample.tests.pages.android;

import org.openqa.selenium.WebDriver;

import com.sample.framework.Platform;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class LocationSearchPage extends Page {

    @FindBy(locator = "disam_search", platform = Platform.ANRDOID_NATIVE)
    public Edit editSearch;
    
    @FindBy(locator = "xpath=(//android.widget.LinearLayout[contains(@resource-id, 'disam_list_root')])[1]",
           platform = Platform.ANRDOID_NATIVE)
    public Control itemTopMostResult;
    
    public LocationSearchPage(WebDriver driverValue) {
        super(driverValue);
    }

}
