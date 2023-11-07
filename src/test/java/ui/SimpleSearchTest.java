/* (C)2023 */
package ui;

import com.common.ui.basesetup.BaseSetup;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.pages.DetailPage;
import ui.pages.MainPage;

public class SimpleSearchTest extends BaseSetup {
    private WebDriver driver;
    static final String searchedText = "Software Testing";

    @BeforeClass
    public void setup() {
        driver = getDriver();
    }

    @Test
    @Description("Case 1. Simple auto-search and click Search button")
    public void testSimpleSearch() {
        // Pre-condition: Launch the main page
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();

        // 1. Enter the searched text and read the first item from the auto-suggested list
        String firstItemList = mainPage.setTextAndGetFirstItemList(searchedText);

        // 2. Click Search
        mainPage.clickSearchButton();

        // Check that first item in the auto-suggested list contains the searched text
        Assert.assertTrue(
                searchedText.toLowerCase().contains(firstItemList.toLowerCase()),
                "The search suggested the wrong pages");

        // Read the content title of the redirected detail page
        DetailPage detailPage = new DetailPage(driver);
        String contentTitle = detailPage.getContentTitle();

        // Check that the opened page is the first page from the auto-suggested list
        Assert.assertEquals(
                contentTitle.toLowerCase(),
                firstItemList.toLowerCase(),
                "The selected page is not the first suggested item");
    }

    @AfterClass
    public void close() {
        driver.close();
    }
}
