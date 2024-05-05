package pages.auth;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class AuthPage extends BasePage {

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    private final By loginEmail = By.id("loginEmail");
    private final By loginPassword = By.id("loginPassword");
    private final By authButton = By.id("authButton");
    private final By authAlertsHolder = By.id("authAlertsHolder"); // alerts dialog which appears in case of
                                                                    // wrong entered data

    public AuthPage setLoginEmail(String email) {
        driver.findElement(loginEmail).sendKeys(email);
        return this;
    }

    public AuthPage setLoginEmailFromClipboard(String email) {
        CommonActions.copyToClipboard(email);
        driver.findElement(loginEmail).sendKeys(Keys.CONTROL + "V");
        return this;
    }

    public String getEnteredEmail() {
        return driver.findElement(loginEmail).getAttribute("value");
    }

    public AuthPage setLoginPassword(String password) {
        driver.findElement(loginPassword).sendKeys(password);
        return this;
    }

    public AuthPage setLoginPasswordFromClipboard(String password) {
        CommonActions.copyToClipboard(password);
        driver.findElement(loginPassword).sendKeys(Keys.CONTROL + "V");
        return this;
    }

    public String getEnteredPassword() {
        return driver.findElement(loginPassword).getAttribute("value");
    }

    public AuthPage clickAuthButton() {
        driver.findElement(authButton).click();
        return this;
    }

    public boolean isAuthAlertsHolderVisible() {
        return driver.findElement(authAlertsHolder).findElements(By.xpath("*")).size() > 0;
    }

    public String getAuthAlertsHolderText() {
        if (isAuthAlertsHolderVisible())
            return driver.findElement(authAlertsHolder).getText();
        else
            return null;
    }

    public AuthPage closeAuthAlertsHolder() {
        if (isAuthAlertsHolderVisible())
            driver.findElement(By.cssSelector(".uk-alert-close")).click();

        return this;
    }

    // clearing fields after each method for proper testing
    public AuthPage clear() {
        driver.findElement(loginEmail).clear();
        driver.findElement(loginPassword).clear();

        return this;
    }
}
