package auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthFieldsFormatPositiveTest extends BaseTest {

    @Test
    public void checkCorrectEmail() {
        checkAuthEmailField("Test_1.2@test.ru", "Неверный E-Mail или пароль");
    }

    @Test
    public void checkCorrectShortEmail() {
        checkAuthEmailField("t@t.r", "Неверный E-Mail или пароль");
    }

    // email length = 254 symbols
    @Test
    public void checkCorrectLongEmail() {
        checkAuthEmailField("ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCd" +
                "S9yJzYqy9TLjziLLis5U3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq@" +
                "ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjziLLis5U3cUTZ" +
                "KmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq.ru", "Неверный E-Mail или пароль");
    }

    @Test
    public void pasteEmailFromClipboard() {
        authPage.setLoginEmailFromClipboard("test@test.ru");
        Assert.assertEquals(authPage.getEnteredEmail(), "test@test.ru");
    }

    @Test
    public void checkCorrectPassword() {
        checkAuthPasswordField("Password_123!@.",
                "Неверный формат E-Mail");
    }

    // rule: password >= 3 symbols
    @Test
    public void checkShortPassword() {
        checkAuthPasswordField("pas",
                "Неверный формат E-Mail");
    }

    // rule: password <= 30 symbols
    @Test
    public void checkLongPassword() {
        checkAuthPasswordField("\n" +
                        "d.cfSlTkOaAFKtcI0moHCiRderlE8b", // string length = 30
                "Неверный формат E-Mail");
    }

    @Test
    public void pastePasswordFromClipboard() {
        authPage.setLoginPasswordFromClipboard("test");
        Assert.assertEquals(authPage.getEnteredPassword(), "test");
    }
}
