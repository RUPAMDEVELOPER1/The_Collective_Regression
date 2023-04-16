package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.PageObjectManager;
import pageObjects.SeachPage;

public class GenericUtils {
	public String copiedCount;
	public String verifyCopiedCount;
	public WebDriver driver;
	public PageObjectManager pageObjectManager;
	
	public GenericUtils(WebDriver driver,PageObjectManager pageObjectManager)
	{

		this.driver = driver;
		this.pageObjectManager = pageObjectManager;
	}



	public void SwitchWindowToChild()
	{
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1 =s1.iterator();
		String parentWindow = i1.next();
		String childWindow = i1.next();
		driver.switchTo().window(childWindow);
	}

//	public void removeAllitem(){
//		List<WebElement> item = driver.findElements(By.xpath("remove"));
//
//		for(int i = 0; i<item.size();i++){
//			WebElement element = item.get(i);
//			element.click();
//		}
//
//	}
	public void clickRandom(By commonxpath) {

		try {
			List<WebElement> products  = driver.findElements(commonxpath);
			//to genrate number incluing the min and max value
//		      Random rand = new Random();
//		      int randomNum = rand.nextInt(max – min + 1) + min;
			if(products.size()!=0){
			System.out.println("size of product"+products.size());
			int min = 0;
			int max = products.size()-1;
			Random rand = new Random();
			int randomNum = rand.nextInt((max - min) + 1) + min;
			products.get(randomNum).click();}
			else {
				System.out.println("your list is empty no element found");
			}
		} catch (Exception e) {
			System.out.println("xpath is not correct");
			throw new RuntimeException(e);
		}


	}
	public void addToBag() throws InterruptedException {

		Thread.sleep(2000);
		WebElement element = null;
		try {
			Thread.sleep(4000);
			element = driver.findElement(pageObjectManager.seachPage.addtoBag);
		} catch (Exception e) {
			System.out.println("element not found for add to bag");
			throw new RuntimeException(e);
		}
		try {
			element.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public void copyCartcount(){
		//copiedCount = driver.findElement(pageObjectManager.seachPage.cartCount).getText()
		try {

			 String copiednumbercount = driver.findElement(pageObjectManager.getShoppingCartpage().shopingCartCount).getText();
			driver.findElement(pageObjectManager.getShoppingCartpage().shoopingcarxpath).click();
			System.out.println("copiednumbercount"+copiednumbercount);
		} catch (Exception e) {
			System.out.println("bag is not clicked");
			throw new RuntimeException(e);
		}

		System.out.println("copied value"+ copiedCount);
	}
	public void verifyCopiedCountIsNotSame(){
		verifyCopiedCount = driver.findElement(pageObjectManager.seachPage.cartCount).getText();
		System.out.println("earlier copied count is "+copiedCount);
		System.out.println("my now count is"+verifyCopiedCount);
		if(copiedCount!= verifyCopiedCount){
			System.out.println("the count is different test is pass");
		}
		else {
			throw  new RuntimeException("count is not chanign its same");
		}
}
}
