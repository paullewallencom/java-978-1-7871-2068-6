package com.sample.tests.junit;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.sample.framework.Configuration;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.SearchResultsPage;

@RunWith(Parameterized.class)
public class BookingSearchTest extends TestCommon {
	private String destination;
	private boolean isLeisure;
	private int numberOfAdults;

	
	public BookingSearchTest(String destination, boolean isLeisure,
			int numberOfAdults) {
		super();
		this.destination = destination;
		this.isLeisure = isLeisure;
		this.numberOfAdults = numberOfAdults;
	}

	@Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(
            new Object[][] {
            		{"London", true, 2 },
            		{"Manchester", false, 1 },
            });
    }
	
	@Test
	public void testValidSearch() throws Exception {
	    searchPage.editDestination.setText(destination);
	    searchPage.checkoutDayToday.click();
		searchPage.selectTravelFor(isLeisure);
		if (Configuration.platform().isWeb()) {
		    searchPage.radioHotels.click();
		}
		//searchPage.selectAdultsNumber.selectByText("" + numberOfAdults);
		searchResultsPage = searchPage.buttonSubmit.clickAndWaitFor(SearchResultsPage.class);
		//searchResultsPage.editDestination.click();
		searchResultsPage.isTextPresent(destination);
		searchResultsPage.captureScreenShot("./build/image01.png");
	}

}
