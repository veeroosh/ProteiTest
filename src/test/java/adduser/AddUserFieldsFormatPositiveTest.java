package adduser;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddUserFieldsFormatPositiveTest extends BaseTest {

    @BeforeClass
    public void openAddUserPage() {
        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
        driver.findElement(By.id("loginPassword")).sendKeys("test");
        driver.findElement(By.id("authButton")).click();
    }

    @Test
    public void checkCorrectEmail() {
        checkAddUserEmailField("Test_1.2@test.ru",
                "Поле имя не может быть пустым");
    }

    @Test
    public void checkCorrectShortEmail() {
        checkAddUserEmailField("t@t.r",
                "Поле имя не может быть пустым");
    }

    // email length = 254 symbols
    @Test
    public void checkCorrectLongEmail() {
        checkAddUserEmailField("ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCd" +
                "S9yJzYqy9TLjziLLis5U3cUTZKmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq@" +
                "ffpKufXLMv_mupzXJPtcmodCG.KF1wO6wISimULWZGLOlnvx7x2tawOmxNPlQoXjNkMCdS9yJzYqy9TLjziLLis5U3cUTZ" +
                "KmUbNfRZTK8rf1qVDRFnTOBCxDwBVcq.ru",
                "Поле имя не может быть пустым");
    }

    @Test
    public void pasteEmailFromClipboard() {
        addUserPage.setEmailFromClipboard("test@test.ru");
        Assert.assertEquals(addUserPage.getEnteredEmail(), "test@test.ru");
    }

    @Test
    public void checkCorrectEnName() {
        checkAddUserNameField("Nika", "Неверный формат E-Mail");
    }

    @Test
    public void checkCorrectRuName() {
        checkAddUserNameField("Ника", "Неверный формат E-Mail");
    }

    @Test
    public void checkCorrectNameWithSpace() {
        checkAddUserNameField("Nika Latysheva",
                "Неверный формат E-Mail");
    }

    @Test
    public void checkCorrectShortName() {
        checkAddUserNameField("N", "Неверный формат E-Mail");
    }

    // name length = 255 symbols
    @Test
    public void checkCorrectLongName() {
        checkAddUserNameField("yBLxKdlIDCDgxUfTemYSktqdPAoHmfodkVFTBSuRzVJRjZOKAXHDPuIBXXwjLiTmfJpwdFsHtVHpLo" +
                "kIHqIPXYxMFZvcvVGHQHISlUbKrGTgXJdHKBaUMNfTwdkkIyBLxKdlIDCDgxUfTemYSktqdPAoHmfodkVFTBSuRzVJRjZOKAX" +
                "HDPuIBXXwjLiTmfJpwdFsHtVHpLokIHqIPXYxMFZvcvVGHQHISlUbKrGTgXJdHKBaUMNfTwdkkIttttt",
                "Неверный формат E-Mail");
    }

    @Test
    public void pasteNameFromClipboard() {
        addUserPage.setNameFromClipboard("Name");
        Assert.assertEquals(addUserPage.getEnteredName(), "Name");
    }

    @Test
    public void checkCheckboxesAreSelectedAndDeselected() {
        addUserPage.changeBothCheckboxes(true, true);
        if (addUserPage.isCheckbox11Selected() && addUserPage.isCheckbox12Selected()) {
            addUserPage.changeBothCheckboxes(false, false);
            if (addUserPage.isCheckbox11Selected() || addUserPage.isCheckbox12Selected())
                Assert.fail("Checkboxes weren't deselected");
        }
        else
            Assert.fail("Checkboxes weren't selected");
    }

    @Test
    public void checkRadiobuttonSelection() throws InterruptedException {
        addUserPage.clickRadio21();
        if (addUserPage.isRadio21Selected()) {
            addUserPage.clickRadio22();

            if (!addUserPage.isRadio21Selected() && addUserPage.isRadio22Selected())
                Assert.assertTrue(true);
            else
                Assert.fail("Radiobutton wasn't changed");

        } else
            Assert.fail("Radiobutton wasn't selected");
    }
}
