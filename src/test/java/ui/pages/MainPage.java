/* (C)2023 */
package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage {
    private final WebDriver driver;
    private final By searchBox = By.xpath("//input[@name='search']");
    private final By searchButton = By.cssSelector("#searchform > div > button");
    private final By lstSuggestedText =
            By.cssSelector("li[role=option] span.cdx-search-result-title");

    private final By txtSearchContaining =
            By.cssSelector("li[role=option] span.cdx-typeahead-search__search-footer__text > span");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public String setTextAndGetFirstItemList(String input) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(input);
        return driver.findElements(lstSuggestedText).get(0).getText();
    }

    public void setTextAndClickSearchContaining(String input) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(input);
        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(txtSearchContaining));
        driver.findElement(txtSearchContaining).click();
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation =
                driver ->
                        ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState")
                                .toString()
                                .equals("complete");
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
