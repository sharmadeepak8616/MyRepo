package framework.web_pages.Hotels;

import framework.BasePage;
import framework.WrapperWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import util.DoVerification;
import util.GetValuesUsingRegex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchPage extends BasePage {

    private String starLocatorStarts = "//span[@aria-label='";
    private String starLocatorEnds = "-star']";
    private String starLocatorStarts_2 = "f-star-rating-";
    private By expandedAreaMessageLocator = By.xpath("//li[@class='expanded-area-message']");
    private By unavailableInfoLocator = By.xpath("//div[@class='info unavailable-info']");
    private By additionalInfoLocator = By.xpath("//div[@class='additional-info']//h3");
    private By hotelsRatingLocator = By.xpath("//span[@class='star-rating-text star-rating-text-strong']");
    private By sortByDistanceLocator = By.xpath("//li[@class='sort-option']//a[@data-menu='sort-submenu-distance']");
    private By sortByDistanceSubMenuLocator = By.xpath("//ul[@id='sort-submenu-distance']//a");
    private String hotelDistanceFromAirport_LocatorStarts = "//ul[@class='property-landmarks']//li[contains(text(),'";
    private String hotelDistanceFromAirport_LocatorEnds = "')]";


    private JavascriptExecutor js = (JavascriptExecutor) WrapperWebDriver.getDriver();

    public void clickStar(String star) {
        By starLocator = By.xpath(starLocatorStarts + star + starLocatorEnds);
        try {
            clickOn(starLocator);
        } catch (Exception e) {
            starLocator = By.id(starLocatorStarts_2 + star);
            clickOn(starLocator);
        }
    }

    public void getAllSearchHotels() {
        //WrapperWebDriver.getDriver().manage().window().maximize();
        boolean check = true;
        while (check) {
            try {
                WebElement bottom = WrapperWebDriver.getDriver().findElement(additionalInfoLocator);
                if (bottom.isDisplayed()) {
                    check = false;
                }
            } catch (Exception e) {
                //JavascriptExecutor js = (JavascriptExecutor) WrapperWebDriver.getDriver();
                //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                js.executeScript("window.scrollBy(0,1500)");
                js.executeScript("window.scrollBy(0,-300)");
            }
        }
    }


    public void verifyAllHotelsRating(String selectedStar) {
        getAllSearchHotels();
        List<WebElement> allHotelsRating = webActions(hotelsRatingLocator);
        Set<String> allHotelsRatingText_Unique = new HashSet<>();
        for (WebElement hotelRating : allHotelsRating) {
            allHotelsRatingText_Unique.add(getTextFromElement(hotelRating));
        }
        for (String rating : allHotelsRatingText_Unique) {
            DoVerification.isStringStartsWith(rating, selectedStar);
        }
    }

    public void clickToSortByDistance() {
        clickOn(sortByDistanceLocator);
    }

    public void selectLocationToSortByDistance(String locationToSelect) {
        clickToSortByDistance();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> locations = webActions(sortByDistanceSubMenuLocator);
        for(WebElement location : locations) {
            if(getTextFromElement(location).equalsIgnoreCase(locationToSelect)) {
                clickOn(location);
                break;
            }
        }
    }

    public void verifyHotelsWithInDistanceFromLocation(int distanceMax, String location) {
        By hotelDistanceFromAirport = By.xpath(hotelDistanceFromAirport_LocatorStarts + location + hotelDistanceFromAirport_LocatorEnds);
        getAllSearchHotels();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> distances = webActions(hotelDistanceFromAirport);
        System.out.println("All hotels : " + distances.size());
        for(WebElement distance : distances) {
            String distanceText = getTextFromElement(distance);
            System.out.println(GetValuesUsingRegex.getDistance(distanceText));
            DoVerification.isvalueLessOrEqual(GetValuesUsingRegex.getDistance(distanceText), distanceMax);
        }
    }




}
