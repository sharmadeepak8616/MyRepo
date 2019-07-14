package framework.web_pages.Hotels;

import framework.BasePage;
import framework.WrapperWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.DoVerification;
import util.GetValuesUsingRegex;

import java.text.SimpleDateFormat;
import java.util.*;

public class HomePage extends BasePage {

    private By destinationLocator = By.id("qf-0q-destination");
    private By autoSuggestions = By.xpath("//tbody[starts-with(@class,'autosuggest-')]//div[@class='autosuggest-category-result']");
    private By searchLocator = By.xpath("//button[@type='submit']");


    public void enterDestination(String destination) {
        sendText(destinationLocator, destination);
    }

    public void selectFromAutoSuggestion(String selectThis) {
        selectFromSuggestion(autoSuggestions, selectThis);
    }


    public void clickSearchButton() {
        clickOn(searchLocator);
    }





}
