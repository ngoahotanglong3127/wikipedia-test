/* (C)2023 */
package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {

    private final WebDriver driver;

    private final By pageHeader = By.xpath("//*[@id='firstHeading']");
    private final By next20 =
            By.xpath("//div[@class='mw-search-pager-top']//a[@class='mw-nextlink']");

    private final By firstItemResult =
            By.cssSelector("li:first-child div.mw-search-result-heading > a");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNext20() {
        driver.findElement(next20).click();
    }

    public String getPageHeader() {
        return driver.findElement(pageHeader).getText();
    }

    public void clickFirstItem() {
        driver.findElement(firstItemResult).click();
    }

    public String getFirstItemText() {
        return driver.findElement(firstItemResult).getText();
    }
}
