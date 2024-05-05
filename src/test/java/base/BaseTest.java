package base;

import common.CommonActions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.adduser.AddUserPage;
import pages.auth.AuthPage;

public class BaseTest {
    protected final WebDriver driver = CommonActions.createDriver();
    protected AuthPage authPage = new AuthPage(driver);
    protected AddUserPage addUserPage = new AddUserPage(driver);

    @BeforeMethod
    public void clearFields() {
        if (addUserPage.isPageVisible())
            addUserPage.clear();
        else
            authPage.clear();
    }

    @AfterClass (alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    public void checkAuthEmailField(String text, String expectedAlertText) {
        if (text != "")
            authPage.setLoginEmail(text).clickAuthButton();
        else
            authPage.clickAuthButton();

        if (authPage.isAuthAlertsHolderVisible())
            Assert.assertEquals(authPage.getAuthAlertsHolderText(), expectedAlertText);
        else
            Assert.fail("Alert message didn't appear");
    }

    public void checkAuthPasswordField(String text, String expectedAlertText) {
        if (text != "")
            authPage.setLoginPassword(text).clickAuthButton();
        else
            authPage.clickAuthButton();

        if (authPage.isAuthAlertsHolderVisible())
            Assert.assertEquals(authPage.getAuthAlertsHolderText(), expectedAlertText);
        else
            Assert.fail("Alert message didn't appear");
    }

    public void checkAddUserEmailField(String text, String expectedAlertText) {
        if (text != "")
            addUserPage.setEmail(text).clickButtonSend();
        else
            addUserPage.clickButtonSend();

        if (addUserPage.isAddUserAlertsHolderVisible())
            Assert.assertEquals(addUserPage.getAddUserAlertsHolderText(), expectedAlertText);
        else
            Assert.fail("Alert message didn't appear");
    }

    public void checkAddUserNameField(String text, String expectedAlertText) {
        if (text != "")
            addUserPage.setName(text).clickButtonSend();
        else
            addUserPage.clickButtonSend();

        if (addUserPage.isAddUserAlertsHolderVisible())
            Assert.assertEquals(addUserPage.getAddUserAlertsHolderText(), expectedAlertText);
        else
            Assert.fail("Alert message didn't appear");
    }
}
