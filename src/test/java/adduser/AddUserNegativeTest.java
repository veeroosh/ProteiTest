package adduser;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddUserNegativeTest extends BaseTest {

    @BeforeClass
    public void openAddUserPage() {
        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
        driver.findElement(By.id("loginPassword")).sendKeys("test");
        driver.findElement(By.id("authButton")).click();
    }

    @Test (priority = 1)
    public void allFilledExceptEmail() {
        int quantity = addUserPage.countRowsInTable();
        addUserPage.setName("Test User")
                .chooseGender("Женский")
                .changeCheckbox11(true)
                .clickRadio23()
                .clickButtonSend();

        if (addUserPage.isAddUserModalDialogVisible()) {
            addUserPage.closeAddUserModalDialog();
            Assert.fail("Incorrect user was added");
        }

        if (addUserPage.isAddUserAlertsHolderVisible()) {
            if(addUserPage.getAddUserAlertsHolderText() == "Неверный формат E-Mail")
                addUserPage.closeAddUserAlertsHolder();
            else
                Assert.fail("Wrong error message for incorrect email");
        }
        else
            Assert.fail("Alert with error message wasn't represented");

        Assert.assertEquals(addUserPage.countRowsInTable(), quantity);
    }

    @Test (priority = 2)
    public void allFilledExceptName() {
        int quantity = addUserPage.countRowsInTable();
        addUserPage.setEmail("test2@test.ru")
                .chooseGender("Женский")
                .changeCheckbox11(true)
                .clickRadio21()
                .clickButtonSend();

        if (addUserPage.isAddUserModalDialogVisible()) {
            addUserPage.closeAddUserModalDialog();
            Assert.fail("Incorrect user was added");
        }

        if (addUserPage.isAddUserAlertsHolderVisible()) {
            if(addUserPage.getAddUserAlertsHolderText() == "Поле имя не может быть пустым")
                addUserPage.closeAddUserAlertsHolder();
            else
                Assert.fail("Wrong error message for incorrect email");
        }
        else
            Assert.fail("Alert with error message wasn't represented");

        Assert.assertEquals(addUserPage.countRowsInTable(), quantity);
    }

    @Test (priority = 3)
    public void filledOnlyUnnecessaryFields() {
        int quantity = addUserPage.countRowsInTable();
        addUserPage.chooseGender("Женский")
                .changeCheckbox12(true)
                .clickRadio22()
                .clickButtonSend();

        if (addUserPage.isAddUserModalDialogVisible()) {
            addUserPage.closeAddUserModalDialog();
            Assert.fail("Incorrect user was added");
        }

        if (addUserPage.isAddUserAlertsHolderVisible()) {
            if(addUserPage.getAddUserAlertsHolderText() == "Неверный формат E-Mail")
                addUserPage.closeAddUserAlertsHolder();
            else
                Assert.fail("Wrong error message for incorrect email");
        }
        else
            Assert.fail("Alert with error message wasn't represented");

        Assert.assertEquals(addUserPage.countRowsInTable(), quantity);
    }

    @Test (priority = 4)
    public void addFullDuplicate() {
        int quantity = addUserPage.countRowsInTable();
        addUserPage.setEmail("test@test.com")
                .setName("Name")
                .chooseGender("Женский")
                .changeBothCheckboxes(true, true)
                .clickRadio21()
                .clickButtonSend();

        addUserPage.closeAddUserModalDialog();

        addUserPage.setEmail("test@test.com")
                .setName("Name")
                .chooseGender("Женский")
                .changeBothCheckboxes(true, true)
                .clickRadio21()
                .clickButtonSend();

        if (addUserPage.isAddUserModalDialogVisible()) {
            addUserPage.closeAddUserModalDialog();
            Assert.fail("Incorrect user was added");
        }

        if (addUserPage.isAddUserAlertsHolderVisible())
            addUserPage.closeAddUserAlertsHolder();
        else
            Assert.fail("Alert with error message wasn't represented");

        Assert.assertEquals(addUserPage.countRowsInTable(), quantity);
    }

    @Test (priority = 5)
    public void addDuplicateEmail() {
        int quantity = addUserPage.countRowsInTable();
        addUserPage.setEmail("test@test.com")
                .setName("Name")
                .chooseGender("Женский")
                .changeBothCheckboxes(true, true)
                .clickRadio21()
                .clickButtonSend();

        addUserPage.closeAddUserModalDialog();

        addUserPage.setEmail("test@test.com")
                .setName("something")
                .chooseGender("Мужской")
                .clickButtonSend();

        if (addUserPage.isAddUserModalDialogVisible()) {
            addUserPage.closeAddUserModalDialog();
            Assert.fail("Incorrect user was added");
        }

        if (addUserPage.isAddUserAlertsHolderVisible())
            addUserPage.closeAddUserAlertsHolder();
        else
            Assert.fail("Alert with error message wasn't represented");

        Assert.assertEquals(addUserPage.countRowsInTable(), quantity);
    }
}
