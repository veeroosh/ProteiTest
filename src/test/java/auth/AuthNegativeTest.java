package auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthNegativeTest extends BaseTest {

    @Test
    public void checkCorrectEmailAndEmptyPassword() {
        checkAuthEmailField("test@protei.ru", "Неверный E-Mail или пароль");
    }

    @Test
    public void checkEmptyEmailAndCorrectPassword() {
        checkAuthPasswordField("test",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkCorrectEmailAndIncorrectPassword() {
        authPage.setLoginEmail("test@protei.ru");
        checkAuthPasswordField("test ", "Неверный E-Mail или пароль");
    }

    @Test
    public void checkIncorrectEmailAndCorrectPassword() {
        authPage.setLoginEmail("test@protei.ru ");
        checkAuthPasswordField("test", "Неверный E-Mail или пароль");
    }

    @Test
    public void checkIncorrectEmailAndIncorrectPassword() {
        authPage.setLoginEmail("test@protei.ru ");
        checkAuthPasswordField("test ", "Неверный E-Mail или пароль");
    }

    @Test
    public void checkAppearingAlertTwice() throws InterruptedException {
        checkAuthEmailField("test@", "Неверный формат E-Mail");
        checkAuthEmailField("test@test.ru", "Неверный E-Mail или пароль");
        authPage.closeAuthAlertsHolder();
        Thread.sleep(2000);
        if (authPage.isAuthAlertsHolderVisible())
            Assert.fail("Alert holder wasn't closed - probably there were two of them");
    }
}
