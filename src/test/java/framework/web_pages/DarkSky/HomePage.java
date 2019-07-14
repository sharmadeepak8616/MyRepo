package framework.web_pages.DarkSky;

import framework.BasePage;
import framework.WrapperWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.DateUtils;
import util.DoVerification;
import util.GetValuesUsingRegex;

import java.text.SimpleDateFormat;
import java.util.*;

public class HomePage  extends BasePage {

    private By textInputField = By.xpath("//form[@id='searchForm']//input");
    private By searchButton = By.xpath("//a[@class='searchButton']");
    private By presentTemperature = By.xpath("//span[@class='currently']//span[@class='summary swap']");
    private By temperatureLow = By.xpath("//span[@class='summary-high-low']/span[2]");
    private By temperatureHigh = By.xpath("//span[@class='summary-high-low']/span[3]");
    private By todaysTimeline = By.xpath("//a[@class='day'][@data-day='0']//span[@class='toggle']");
    private By todaysTimeLineMinTemp = By.xpath("//a[@class='day revealed'][@data-day='0']//span[@class='minTemp']");
    private By todaysTimeLineMaxTemp = By.xpath("//a[@class='day revealed'][@data-day='0']//span[@class='maxTemp']");
    private By lowTempFromTodaysDetail = By.xpath("//div[@class='dayDetails revealed']//div[@class='dayExtras']//span[@class='highTemp swip']//span[@class='temp']");
    private By highTempFromTodaysDetail = By.xpath("//div[@class='dayDetails revealed']//div[@class='dayExtras']//span[@class='lowTemp swap']//span[@class='temp']");
    private By timeMachineButtonLocator = By.xpath("//a[contains(text(), 'Time Machine')]");

    private String tempBarValuesStart = "//div[@class='hours']//span[";
    private String tempBarValuesEnd = "]/span";
    private By tempBarValues = By.xpath("//div[@class='hours']//span//span");
    //public static int incrementTempBy;
    //public static int forFutureHours;

    public void enterLocation(int enterLocation) {
        String location = Integer.toString(enterLocation);
        clearText(textInputField);
        sendText(textInputField, location);
    }

    public void enterLocation(String enterLocation) {
        clearText(textInputField);
        sendText(textInputField, enterLocation);
        clickOnSearchButton();
    }

    public void clickOnSearchButton() {
        clickOn(searchButton);
    }

    private int getPresentTemperature() {
        return GetValuesUsingRegex.getCurrentTempValueUsingRegEx(getTextFromElement(presentTemperature));
    }

    private int getLowTemperature() {
        return GetValuesUsingRegex.getLowHighTempValueUsingRegEx(getTextFromElement(temperatureLow));
    }

    private int getHighTemperature() {
        return GetValuesUsingRegex.getLowHighTempValueUsingRegEx(getTextFromElement(temperatureHigh));
    }
/*
    private int getLowHighTempValue(String line) {
        String temp = line.split(":")[1];
        temp = temp.split("˚")[0];
        return Integer.parseInt(temp.trim());
    }
*/

    public void verifyPresentTempBetweenLowHigh() {
        try {
            Thread.sleep(5000);
            int present = getPresentTemperature();
            int low = getLowTemperature();
            int high = getHighTemperature();
            Assert.assertTrue(low <= present && present <= high, "Present temp (" + present + "˚) is not in between low (" + low + "˚) and high (" + high + "˚)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyTemperatureBarValues(int incrementBy, int futureHours) {
        DoVerification.isTwoArrayListEquals(getExpectedTemperatureTimeline(incrementBy, futureHours), getActualTemperatureTimeline());
    }

    public void verifyTemperatureBarValues(int incrementBy, int futureHours, String location) {
        try {
            String checkForLocation = GetValuesUsingRegex.getLocation(location);
            DoVerification.isTwoArrayListEquals(getExpectedTemperatureTimeline(incrementBy, futureHours, checkForLocation), getActualTemperatureTimeline(checkForLocation));
        } catch (NullPointerException e) {
            DoVerification.isTwoArrayListEquals(getExpectedTemperatureTimeline(incrementBy, futureHours), getActualTemperatureTimeline());
        }
    }

    private ArrayList<String> getActualTemperatureTimeline() {
        ArrayList<String> actualTemperatureBar = new ArrayList<String>();
        List<WebElement> temperatures = webActions(tempBarValues);
        for (WebElement temperature : temperatures) {
            actualTemperatureBar.add(getTextFromElement(temperature));
        }
        return actualTemperatureBar;
    }

    private ArrayList<String> getActualTemperatureTimeline(String location) {
        enterLocation(location);
        ArrayList<String> actualTemperatureBar = new ArrayList<String>();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> temperatures = webActions(tempBarValues);
        for (WebElement temperature : temperatures) {
            actualTemperatureBar.add(getTextFromElement(temperature));
        }
        //System.out.println("Actual : " + actualTemperatureBar);
        return actualTemperatureBar;
    }

    private ArrayList<String> getExpectedTemperatureTimeline(int incrementBy, int futureHours) {
        ArrayList<String> expectedTemperatureBar = new ArrayList<String>();
        expectedTemperatureBar.add("Now");
        SimpleDateFormat format = new SimpleDateFormat("ha");
        Calendar cl = Calendar.getInstance();
        for (int i = 0; i < futureHours; i++) {
            cl.add(Calendar.HOUR_OF_DAY, incrementBy);
            Date date = cl.getTime();
            String TimeAfterTwoHours = format.format(date).toLowerCase();
            expectedTemperatureBar.add(TimeAfterTwoHours);
        }
        return expectedTemperatureBar;
    }

    private ArrayList<String> getExpectedTemperatureTimeline(int incrementBy, int futureHours, String location) {
        ArrayList<String> expectedTemperatureBar = new ArrayList<String>();
        expectedTemperatureBar.add("Now");
        Date date;
        SimpleDateFormat newformat = new SimpleDateFormat("ha");
        Calendar cl = Calendar.getInstance();
        location = location.replace(" ", "_");
        newformat.setTimeZone(TimeZone.getTimeZone(location));
        date = cl.getTime();
        for (int i = 0; i < futureHours; i++) {
            cl.add(Calendar.HOUR_OF_DAY, incrementBy);
            date = cl.getTime();
            String TimeAfterTwoHours = newformat.format(date).toLowerCase();
            expectedTemperatureBar.add(TimeAfterTwoHours);
        }
        //System.out.println("Expected : " + expectedTemperatureBar);
        return expectedTemperatureBar;
    }

    public void clickOnTodaysTimeline() {
        //scrollToElement(todaysTimeline);
        scrollByPixel(0,900);
        clickOn(todaysTimeline);
    }

    public String getMinTempFromTodaysTimeline() {
        return getTextFromElement(todaysTimeLineMinTemp).trim();
    }

    public String getMaxTempFromTodaysTimeline() {
        return getTextFromElement(todaysTimeLineMaxTemp).trim();
    }

    public String getLowTempFromTodaysDetail() {
        return getTextFromElement(lowTempFromTodaysDetail).trim();
    }

    public String getHighTempFromTodaysDetail() {
        return getTextFromElement(highTempFromTodaysDetail).trim();
    }

    public void verifyLowHighTempIsDisplayedCorrectlyInTodaysDetail() {
        String lowTempFromTodaysDetail = getLowTempFromTodaysDetail();
        String highTempFromTodaysDetail = getHighTempFromTodaysDetail();

        String lowTempFromTodaysTimeline = getMinTempFromTodaysTimeline();
        String highTempFromTodaysTimeline = getMaxTempFromTodaysTimeline();

        DoVerification.isTwoStringsEquals(getLowTempFromTodaysDetail(), getMinTempFromTodaysTimeline());
        DoVerification.isTwoStringsEquals(getHighTempFromTodaysDetail(), getMaxTempFromTodaysTimeline());
/*
        if(!lowTempFromTodaysDetail.equals(lowTempFromTodaysTimeline) || !highTempFromTodaysDetail.equals(highTempFromTodaysTimeline)) {
            *//*System.out.println(lowTempFromTodaysDetail);
            System.out.println(highTempFromTodaysDetail);
            System.out.println(lowTempFromTodaysTimeline);
            System.out.println(highTempFromTodaysTimeline);*//*
            Assert.fail("Low or high temperature in today's detail is not displayed as expected");
        }
*/
    }


}
