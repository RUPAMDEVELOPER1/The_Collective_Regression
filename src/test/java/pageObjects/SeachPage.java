package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.GenericUtils;

public class SeachPage {



    WebDriver driver;

    public SeachPage(WebDriver driver) {

        this.driver = driver;

    }

    public By search = By.xpath("//input[@id='top_search']");
    By commonlinksproucts = By.xpath("//div[contains(@class,'categoryProduct')]");
    public By commonsizexpath = By.xpath("//div[@class='sizeDetails online-size']//li[not(contains(@class,'size_disabled strikethrough disabled'))]");
    public By commonlinksproductsXpath = By.xpath("//div[contains(@class,'categoryProduct')]");

    public By addtoBag = By.xpath("(//a[@id='btnAddToBagPage'])[1]");

    public  By cartCount = By.xpath("//span[@class='cart_count']");




    public void search(String searchitem) {
        try {
            driver.findElement(search).sendKeys(searchitem, Keys.ENTER);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }

    }




}
