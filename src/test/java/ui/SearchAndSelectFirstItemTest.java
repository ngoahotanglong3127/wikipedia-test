/* (C)2023 */
package ui;

import static com.common.ui.Constant.SEARCH_RESULTS;

import com.common.ui.basesetup.BaseSetup;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.DetailPage;
import ui.pages.MainPage;
import ui.pages.SearchResultsPage;

public class SearchAndSelectFirstItemTest extends BaseSetup {
    private WebDriver driver;
    static final String searchedText = "Software Testing";

    @BeforeClass
    public void setup() {
        driver = getDriver();
    }

    @Test
    @Description("Case 5. Simple searching pages containing the text option")
    public void testSearchAndSelectFirstItem() {
        // Pre-condition: Launch the main page
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();

        // 1. Enter the searched text and read the first item from the auto-suggested list
        mainPage.setTextAndClickSearchContaining(searchedText);

        // Check that Search Results page is displayed
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        String pageHeader = searchResultsPage.getPageHeader();
        Assert.assertEquals(
                pageHeader.toLowerCase(),
                SEARCH_RESULTS.toLowerCase(),
                "The Search results page is not displayed");

        // 2. Navigate to next 20 pages
        searchResultsPage.clickNext20();

        // 3. Select the first item in the search results
        String firstItemText = searchResultsPage.getFirstItemText();
        searchResultsPage.clickFirstItem();

        // Check the selected page is opened
        DetailPage detailPage = new DetailPage(driver);
        String contentTitle = detailPage.getContentTitle();
        Assert.assertEquals(
                contentTitle.toLowerCase(),
                firstItemText.toLowerCase(),
                "The selected page is not correct");
    }

    @AfterClass
    public void close() {
        driver.close();
    }
}
