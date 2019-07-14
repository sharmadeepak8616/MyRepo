package framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import util.ConfigReader;

public class WrapperWebDriver {


	private static ConfigReader configReader = new ConfigReader();
	private static final String SAUCE_URL = "https://" + configReader.getSauceUsername() + ":" + configReader.getSauceKey() + "@ondemand.saucelabs.com:443/wd/hub";
	private static WebDriver driver = null;

	@Before()
	public static void before() {

		try {
			if (configReader.getEnvironment().equals("local")) {
				//System.setProperty("webdriver.chrome.driver", configReader.getChromeDriverPath());
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} else if (configReader.getEnvironment().equals("sauce")) {
				DesiredCapabilities caps = null;
				if (configReader.getSauceBrowser().equals("chrome")) {
					caps = DesiredCapabilities.chrome();
				} else if (configReader.getSauceBrowser().equals("firefox")) {
					caps = DesiredCapabilities.firefox();
				} else if (configReader.getSauceBrowser().equals("safari")) {
					caps = DesiredCapabilities.safari();
				} else {
					throw new UnsupportedOperationException("Invalid Browser name");
				}
				caps.setCapability("platform", configReader.getPlatform());
				caps.setCapability("version", configReader.getVersion());

				driver = new RemoteWebDriver(new URL(SAUCE_URL), caps);

			} else {
				throw new UnsupportedOperationException("Invalid Environment name");
			}

			driver.get(configReader.getUrl());
			//driver.manage().window().maximize();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}


	@After()
	public static void after() {
		if (driver != null) {
			//driver.manage().deleteAllCookies();
			//driver.quit();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
