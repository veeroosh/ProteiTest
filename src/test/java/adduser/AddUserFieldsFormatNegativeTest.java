package adduser;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddUserFieldsFormatNegativeTest extends BaseTest {

    @BeforeClass
    public void openAddUserPage() {
        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
        driver.findElement(By.id("loginPassword")).sendKeys("test");
        driver.findElement(By.id("authButton")).click();
    }

    /*
     * Negative tests for email
     * It's supposed that other fields are empty
     */
    @Test
    public void checkEmptyEmail() {
        checkAddUserEmailField("", "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameOnly() {
        checkAddUserEmailField("test",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameAt() {
        checkAddUserEmailField("test@",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameAtDomain() {
        checkAddUserEmailField("test@test",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithNameAtDomainDot() {
        checkAddUserEmailField("test@test.",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithTwoAts() {
        checkAddUserEmailField("test@@test.ru",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithTwoDots() {
        checkAddUserEmailField("test@test..ru",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithSpacesOnly() {
        checkAddUserEmailField("  ",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithSpaceBefore() {
        checkAddUserEmailField(" test@test.ru",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithSpaceAfter() {
        checkAddUserEmailField("test@test.ru ",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkEmailWithSpaceInCenter() {
        checkAddUserEmailField("test @tes t.ru",
                "Неверный формат E-Mail");
    }

    // email length = 255 symbols
    @Test
    public void checkLongEmail() {
        checkAddUserEmailField("ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCd" +
                        "S9yJzYqy9TLjziLLis5U3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq@" +
                        "ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjziLLis5U" + "" +
                        "3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq.com",
                "Неверный формат E-Mail");
    }

    // email length = 255 symbols
    @Test
    public void pasteLongEmailFromClipboard() {
        addUserPage.setEmailFromClipboard("ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCd" +
                "S9yJzYqy9TLjziLLis5U3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq@" +
                "ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjziLLis5U" +
                "3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq.com");
        Assert.assertEquals(addUserPage.getEnteredEmail(), "ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOl" +
                "nvx7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjziLLis5U3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq@" +
                "ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjziLLis5U" +
                "3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq.com");
    }

    /*
     * Negative tests for name
     * It's supposed that other fields are empty
     */
    @Test
    public void checkEmptyName() {
        checkAddUserNameField("",
                "Поле имя не может быть пустым");
    }

    @Test
    public void checkNameWithSpacesOnly() {
        checkAddUserNameField("   ",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkNameWithSpaceBefore() {
        checkAddUserNameField(" Nika",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkNameWithSpaceAfter() {
        checkAddUserNameField("Nika ",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkNameWithSpecialSymbolsAndDigits() {
        checkAddUserNameField("!@#$.%^&*()-=0123456789",
                "Неверный формат E-Mail");
    }

    // name length = 256 symbols
    @Test
    public void checkLongName() {
        checkAddUserNameField("yBLxKdlIDCDgxUfTemYSktqdPAoHmfodkVFTBSuRzVJRjZOKAXHDPuIBXXwjLiTmfJpwdFsHtVHpLo" +
                        "kIHqIPXYxMFZvcvVGHQHISlUbKrGTgXJdHKBaUMNfTwdkkIyBLxKdlIDCDgxUfTemYSktqdPAoHmfodkVFTBSuRzVJRjZOKAX" +
                        "HDPuIBXXwjLiTmfJpwdFsHtVHpLokIHqIPXYxMFZvcvVGHQHISlUbKrGTgXJdHKBaUMNfTwdkkItttttw",
                "Неверный формат E-Mail");
    }


}
