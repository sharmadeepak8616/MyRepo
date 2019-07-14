package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.web_pages.Threely.HomePage;
import framework.web_pages.Threely.LoginPage;

public class ThreelySD {

    HomePage homepage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Given("^I am on Threely login page$")
    public void i_am_on_Threely_webpage() {
        homepage.clickOnSignIn();
    }

    @When("^I enter username as (.+) and password as (.+)$")
    public void i_enter_username_as_n_test_com_and_password_as(String emailAddr, String password) {
        loginPage.enterEmailAddress(emailAddr);
        loginPage.enterPassword(password);
    }

    @And("^I click on submit button$")
    public void i_click_on_submit_button() {
        loginPage.clickSubmitButton();
    }

    @Then("^I verify logout button is displayed$")
    public void i_verify_logout_button_is_displayed()  {
        loginPage.verifySuccessfulLogin();
    }







}
