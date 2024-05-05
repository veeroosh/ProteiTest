package adduser;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddUserPositiveTest extends BaseTest {

    @BeforeClass
    public void openAddUserPage() {
        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
        driver.findElement(By.id("loginPassword")).sendKeys("test");
        driver.findElement(By.id("authButton")).click();
    }

    @Test (priority = 1)
    public void necessaryFieldsFilledCorrect() {
        addUserPage.setEmail("test1@test.ru")
                .setName("Test User")
                .clickButtonSend();

        addUserPage.closeAddUserModalDialog();
        Assert.assertEquals(addUserPage.getDataInLastTableRow(), new String[] {"test1@test.ru", "Test User", "Мужской",
                "Нет", ""});
        // Assert.assertTrue(addUserPage.areFieldsEmpty());
    }

    @Test (priority = 2)
    public void allFieldsFilledCorrect() throws InterruptedException {
        addUserPage.setEmail("test@test.ru")
                .setName("Test User")
                .chooseGender("Женский")
                .changeBothCheckboxes(true, true)
                .clickRadio21()
                .clickButtonSend();

        addUserPage.closeAddUserModalDialog();
        Assert.assertEquals(addUserPage.getDataInLastTableRow(), new String[] {"test@test.ru", "Test User", "Женский",
                "1.1, 1.2", "2.1"});
        // Assert.assertTrue(addUserPage.areFieldsEmpty());
    }
}
