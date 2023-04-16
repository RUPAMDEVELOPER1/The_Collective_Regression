package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartpage {
    public WebDriver driver;
    public ShoppingCartpage(WebDriver driver) {
        this.driver = driver;
    }
    public By shoopingcarxpath = By.xpath("(//span[@class='spriteIcon'])[4]");
    public By shopingCartCount = By.xpath("//span[@class='cart_count']");
}
