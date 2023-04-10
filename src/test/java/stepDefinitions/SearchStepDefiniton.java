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
    }
    @Then("^i select size and add to bag$")
    public void i_select_size_and_add_to_bag() {

    }

    @Then("^i copy my cart count$")
    public void i_copy_my_cart_count()  {

    }

    @Then("^i select another size and add to bag$")
    public void i_select_another_size_and_add_to_bag() {

    }
    @Then("^i verify cart count should not be same$")
    public void i_verify_cart_count_should_not_be_s(){

    }
}
