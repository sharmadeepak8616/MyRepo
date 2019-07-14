package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.web_pages.Hotels.HomePage;
import framework.web_pages.Hotels.SearchPage;

public class HotelsSD {

    HomePage homepage = new HomePage();
    SearchPage searchpage = new SearchPage();

    @Given("^I am on default locations search result screen$")
    public void goToDefaultSearchPage() {
        homepage.enterDestination("New York");
        homepage.selectFromAutoSuggestion("New York, New York, United States of America");
        homepage.clickSearchButton();
    }


    @When("^I select property star (.*)$")
    public void selectStarClass(String starToSelect) {
        searchpage.clickStar(starToSelect);
    }

    @Then("^I verify system displays only (.*) hotels on search result$")
    public void verifyOnlySelectedStarHotelsArePresent(String selectStar) {
        //searchpage.getAllSearchHotels();
        searchpage.verifyAllHotelsRating(selectStar);
    }

    @Then("^I verify system displays all hotels within (\\d+) miles radius of (.*) airport$")
    public void verifyHotelsWithinMentionedDistance(int distance, String airport) {
        searchpage.selectLocationToSortByDistance(airport);
        searchpage.verifyHotelsWithInDistanceFromLocation(distance, airport);

    }

    @And("^I verify (.*) Hotel is within radius$")
    public void verifyMentionedHotelWithinDistance(String hotelname) {

    }


}
