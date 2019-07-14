package framework;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BasePage {

    // This is the most common wait function used in selenium
    public static WebElement webAction(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(WrapperWebDriver.getDriver())
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotVisibleException.class)
                .withMessage(
                        "Webdriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown");

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }

	public static List<WebElement> webActions(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(WrapperWebDriver.getDriver())
				.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class)
				.withMessage(
						"Webdriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown");

		List<WebElement> elements = wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(locator);
			}
		});
		return elements;
	}

	public void clickOn(By locator) {
            webAction(locator).click();
	}

	public void clickOn(WebElement element) {
		element.click();
	}

	public void sendText(By locator, String text) {
            webAction(locator).sendKeys(text);
	}

	public void setValue(By locator, String text) {
		webAction(locator).sendKeys(text);
	}

	public String getTextFromElement(By locator) {
		return webAction(locator).getText();
	}

	public String getTextFromElement(WebElement element) {
		return element.getText();
	}

	public boolean isSelected(By locator) {
		return webAction(locator).isSelected();
	}

	public void selectFromDropDown(By dropDownLocator, String option) {
		WebElement dropDownElement = webAction(dropDownLocator);
		Select dropDown = new Select(dropDownElement);
		dropDown.selectByVisibleText(option);
	}

	public WebElement getWebElement(By locator) {
		return webAction(locator);
	}

	public List<WebElement> getWebElements(By locator) {
		List<WebElement> elements = null;
		try {
			elements = WrapperWebDriver.getDriver().findElements(locator);
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
		return elements;
	}

	public void hoverOnElement(WebElement element) {
		Actions act = new Actions(WrapperWebDriver.getDriver());
		act.moveToElement(element).build().perform();
	}

	public void hoverOnElement(By locator) {
		Actions act = new Actions(WrapperWebDriver.getDriver());
		act.moveToElement(webAction(locator)).build().perform();
	}

	public void clickOnBrowserBackArrow() {
		WrapperWebDriver.getDriver().navigate().back();
	}

	public void clickOnBrowserForwardArrow() {
		WrapperWebDriver.getDriver().navigate().forward();
	}

	public void refreshBrowser() {
		WrapperWebDriver.getDriver().navigate().refresh();
	}

	public void clearText(By locator) {
            webAction(locator).clear();
	}

	public void scrollToElement(By locator) {
    	JavascriptExecutor js = (JavascriptExecutor) WrapperWebDriver.getDriver();
    	WebElement element = WrapperWebDriver.getDriver().findElement(locator);
    	js.executeScript("arguments[0].scrollIntoView(true);" , element);
	}

	public void scrollByPixel(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) WrapperWebDriver.getDriver();
		js.executeScript("window.scrollBy("+x+","+y+")","");
	}


	public void openBrowserAndWebpage() {
    	WrapperWebDriver.before();
	}


	public void closeBrowser() {
        WrapperWebDriver.after();
    }


    public void goToMainWindow() {
    	String parent = WrapperWebDriver.getDriver().getWindowHandle();
    	Set<String> allHandles = WrapperWebDriver.getDriver().getWindowHandles();

    	for (String handle : allHandles) {
    		if(handle.equals(parent)) {
    			WrapperWebDriver.getDriver().switchTo().window(handle);
			}
		}

	}

	public void closeAllPopUps() {
		String parent = WrapperWebDriver.getDriver().getWindowHandle();
		Set<String> allHandles = WrapperWebDriver.getDriver().getWindowHandles();

		for (String handle : allHandles) {
			if(!handle.equals(parent)) {
				WrapperWebDriver.getDriver().close();
			}
		}

	}

	public void selectFromSuggestion(By locator, String selectThis) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> suggestions = webActions(locator);
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equals(selectThis)) {
				clickOn(suggestion);
				break;
			}
		}
	}

}
