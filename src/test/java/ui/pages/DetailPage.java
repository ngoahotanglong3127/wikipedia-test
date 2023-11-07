/* (C)2023 */
package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailPage {

    private final WebDriver driver;

    private final By contentTitle = By.cssSelector("#firstHeading > span");

    public DetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getContentTitle() {
        return driver.findElement(contentTitle).getText();
    }
}
