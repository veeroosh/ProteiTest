package auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthFieldsFormatNegativeTest extends BaseTest {

    /**
     * Negative tests for email
     * It's supposed that password field is empty
     */

    @Test
    public void checkEmptyEmail() {
        checkAuthEmailField("",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameOnly() {
        checkAuthEmailField("test",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameAt() {
        checkAuthEmailField("test@",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameAtDomain() {
        checkAuthEmailField("test@test",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameAtDomainDot() {
        checkAuthEmailField("test@test.",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithTwoAts() {
        checkAuthEmailField("test@@test.ru",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithTwoDots() {
        checkAuthEmailField("test@test..ru",
                "Неверный формат E-Mail");
    }

    // email length = 255 symbols
    @Test
    public void checkLongEmail() {
        checkAuthEmailField("ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjz" +
                "iLLis5U3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq@ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx" +
                "7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjziLLis5U3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq.com",
                "Неверный формат E-Mail");
    }

    /**
     * Negative tests for password
     * It's supposed that email field is empty
     */

    @Test
    public void checkEmptyPassword() {
        checkAuthPasswordField("",
                "Неверный формат E-Mail");
    }

    // rule: password >= 3 symbols
    @Test
    public void checkShortPassword() {
        checkAuthPasswordField("tt",
                "Неверный E-Mail или пароль");
    }

    // rule: password <= 30 symbols
    @Test
    public void checkLongPassword() {
        checkAuthPasswordField("d.cfSlTkOaAFKtcI0moHCiRderlE8b",
                "Неверный E-Mail или пароль");
    }

    @Test
    public void pasteLongPasswordFromClipboard() {
        authPage.setLoginPasswordFromClipboard("d.cfSlTkOaAFKtcI0moHCiRderlE8b0");
        Assert.assertEquals(authPage.getEnteredPassword(), "d.cfSlTkOaAFKtcI0moHCiRderlE8b"); // cut string
    }
}
