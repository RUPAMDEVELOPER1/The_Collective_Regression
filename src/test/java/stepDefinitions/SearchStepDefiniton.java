package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.SeachPage;
import utils.TestContextSetup;

public class SearchStepDefiniton {
    public WebDriver driver;
    public SeachPage seachPage;
    public TestContextSetup testContextSetup;

    public SearchStepDefiniton(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.seachPage = testContextSetup.pageObjectManager.getSearchPage();
    }
    @When("^i search as '(.+)'$")
    public void i_search_as_(String searchitem) {
        seachPage.search(searchitem);
    }
    @Then("^i click on random product$")
    public void i_click_on_random_product()  {
        testContextSetup.genericUtils.clickRandom(seachPage.commonlinksproductsXpath);
    }
    @Then("^i select size and add to bag$")
    public void i_select_size_and_add_to_bag() throws InterruptedException {
        testContextSetup.genericUtils.SwitchWindowToChild();
        testContextSetup.genericUtils.clickRandom(seachPage.commonsizexpath);
        testContextSetup.genericUtils.addToBag();
        Thread.sleep(5000);

    }

    @Then("^i copy my cart count$")
    public void i_copy_my_cart_count()  {
       // testContextSetup.genericUtils.SwitchWindowToChild();
       testContextSetup.genericUtils.copyCartcount();
    }

    @Then("^i select another size and add to bag$")
    public void i_select_another_size_and_add_to_bag() throws InterruptedException {
        //testContextSetup.genericUtils.SwitchWindowToChild();
        testContextSetup.genericUtils.clickRandom(seachPage.commonsizexpath);
        testContextSetup.genericUtils.addToBag();


    }
    @Then("^i verify cart count should not be same$")
    public void i_verify_cart_count_should_not_be_s() throws InterruptedException {
        //testContextSetup.genericUtils.SwitchWindowToChild();
        Thread.sleep(2000);
       testContextSetup.genericUtils.verifyCopiedCountIsNotSame();
    }
}
