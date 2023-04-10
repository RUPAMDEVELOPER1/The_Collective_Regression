package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SeachPage {
    WebDriver driver;

    public SeachPage(WebDriver driver) {
        this.driver = driver;
    }

    By search = By.xpath("");



    public void search(String searchitem) {
        try {
            driver.findElement(search).sendKeys(searchitem, Keys.ENTER);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }

    }
}
