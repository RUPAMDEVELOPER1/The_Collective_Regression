package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	

	public WebDriver driver;

	public SeachPage seachPage;
	
	public PageObjectManager(WebDriver driver)
	{
		this.driver = driver;
	}

	
	

	public SeachPage getSearchPage(){
		seachPage = new SeachPage(driver);
		return seachPage;

	}
}
