package auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthPositiveTest extends BaseTest {

    @Test
    public void authSuccess() throws InterruptedException {
        authPage.setLoginEmail("test@protei.ru").setLoginPassword("test").clickAuthButton();
        Thread.sleep(2000);
        Assert.assertTrue(addUserPage.isPageVisible());
    }
}
