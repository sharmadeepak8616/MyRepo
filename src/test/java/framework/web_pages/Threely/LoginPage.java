package framework.web_pages.Threely;

import framework.BasePage;
import org.openqa.selenium.By;
import util.DoVerification;

public class LoginPage extends BasePage {

    private By emailAddrLocator = By.id("email");
    private By passwordLocator = By.id("password");
    private By submitButtonLocator = By.xpath("//button[@type='submit']");
    private By profilePictureLocator = By.xpath("//img[@class='img-circle']");
    private By logoutLocator = By.xpath("//a[contains(text(),'Logout')]");


    public void enterEmailAddress(String emailAddr) {
        sendText(emailAddrLocator, emailAddr);
    }

    public void enterPassword(String password) {
        sendText(passwordLocator, password);
    }

    public void clickSubmitButton() {
        clickOn(submitButtonLocator);
    }

    public void verifySuccessfulLogin() {
        clickOn(profilePictureLocator);
        DoVerification.isTwoStringsEquals(getTextFromElement(logoutLocator).trim(), "Logout");
        /*if(DoVerification.isTwoStringsEquals(getTextFromElement(logoutLocator).trim(), "Logout")) {
            System.out.println("Logout is displayed");
        } else {
            System.out.println("Logout is not displayed");
        }*/
    }










}
