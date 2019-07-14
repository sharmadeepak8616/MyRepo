package framework.web_pages.Threely;

import framework.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By signInLocator = By.xpath("//a[contains(text(),'Sign In')]");

    public void clickOnSignIn() {
        clickOn(signInLocator);
    }





}
