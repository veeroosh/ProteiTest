package pages.adduser;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.auth.AuthPage;
import pages.base.BasePage;

import java.util.List;

public class AddUserPage extends BasePage {
    public AddUserPage(WebDriver driver) {
        super(driver);
    }

    private final By dataEmail = By.id("dataEmail");
    private final By dataName = By.id("dataName");
    private final By dataGender = By.id("dataGender");
    private final By checkbox11 = By.xpath("//input[@id='dataCheck11']");
    private final By checkbox12 = By.id("dataCheck12");
    private final By radio21 = By.id("dataSelect21");
    private final By radio22 = By.id("dataSelect22");
    private final By radio23 = By.id("dataSelect23");
    private final By buttonSend = By.id("dataSend");
    private final By dataAlertsHolder = By.id("dataAlertsHolder"); // alerts dialog which appears in case of
                                                                    // wrong entered data
    private final By modalDialog = By.cssSelector(".uk-modal-dialog"); // modal dialog which appears
                                                                        // after adding new user

    public AddUserPage setEmail(String email) {
        driver.findElement(dataEmail).sendKeys(email);
        return this;
    }

    public AddUserPage setEmailFromClipboard(String email) {
        CommonActions.copyToClipboard(email);
        driver.findElement(dataEmail).sendKeys(Keys.CONTROL + "V");
        return this;
    }

    public String getEnteredEmail() {
        return driver.findElement(dataEmail).getAttribute("value");
    }

    public AddUserPage setName(String name) {
        driver.findElement(dataName).sendKeys(name);
        return this;
    }

    public AddUserPage setNameFromClipboard(String email) {
        CommonActions.copyToClipboard(email);
        driver.findElement(dataName).sendKeys(Keys.CONTROL + "V");
        return this;
    }

    public String getEnteredName() {
        return driver.findElement(dataName).getAttribute("value");
    }

    public AddUserPage chooseGender (String gender) {
        // method doesn't change gender, if it already has necessary option
        if (!driver.findElement(dataGender).getAttribute("value").equals(gender)) {
            driver.findElement(dataGender).click();
            driver.findElement(By.xpath("//option[text()='" + gender + "']")).click();
        }
        return this;
    }

    public boolean isCheckbox11Selected() {
        return driver.findElement(checkbox11).isSelected();
    }

    public boolean isCheckbox12Selected() {
        return driver.findElement(checkbox12).isSelected();
    }

    public AddUserPage changeCheckbox11(boolean state) {
        // method doesn't change checkbox, if it already has necessary state
        if (isCheckbox11Selected() != state)
            driver.findElement(checkbox11).click();

        return this;
    }

    public AddUserPage changeCheckbox12(boolean state) {
        // method doesn't change checkbox, if it already has necessary state
        if (isCheckbox12Selected() != state)
            driver.findElement(checkbox12).click();

        return this;
    }

    public AddUserPage changeBothCheckboxes(Boolean state1, Boolean state2) {
        changeCheckbox11(state1);
        changeCheckbox12(state2);
        return this;
    }

    public AddUserPage clickRadio21() {
        driver.findElement(radio21).click();
        return this;
    }

    public AddUserPage clickRadio22() {
        driver.findElement(radio22).click();
        return this;
    }

    public AddUserPage clickRadio23() {
        driver.findElement(radio23).click();
        return this;
    }

    public boolean isRadio21Selected() {
        return driver.findElement(radio21).isSelected();
    }

    public boolean isRadio22Selected() {
        return driver.findElement(radio22).isSelected();
    }

    public boolean isRadio23Selected() {
        return driver.findElement(radio23).isSelected();
    }

    // click button, which add new user
    public AddUserPage clickButtonSend() {
        driver.findElement(buttonSend).click();
        return this;
    }

    public boolean isAddUserAlertsHolderVisible() {
        return driver.findElement(dataAlertsHolder).findElements(By.xpath("*")).size() > 0;
    }

    public String getAddUserAlertsHolderText() {
        if (isAddUserAlertsHolderVisible())
            return driver.findElement(dataAlertsHolder).getText();
        else
            return null;
    }

    public AddUserPage closeAddUserAlertsHolder() {
        if (isAddUserAlertsHolderVisible())
            driver.findElement(By.cssSelector(".uk-alert-close")).click();

        return this;
    }

    public boolean isAddUserModalDialogVisible() {
        return !driver.findElements(modalDialog).isEmpty();
    }

    public AddUserPage closeAddUserModalDialog() {
        if (isAddUserModalDialogVisible())
            waitElementIsVisible(driver.findElement(By.cssSelector(".uk-modal-close"))).click();

        return this;
    }

    // method convert last row in table (= last added user) to string array
    public String[] getDataInLastTableRow() {
        List<WebElement> row = driver.findElement(By.xpath("//table/tbody/tr[last()]")).findElements(By.xpath("td"));

        String[] rowText = new String[5];
        for (int i = 0; i < row.size(); i++)
            rowText[i] = row.get(i).getText();

        return rowText;
    }

    public int countRowsInTable() {
        return driver.findElements(By.xpath("//table/tbody/tr")).size();
    }

    // checking by email input visibility
    public Boolean isPageVisible() {
        return driver.findElement(dataEmail).isDisplayed();
    }

    public boolean areFieldsEmpty() {
        return (driver.findElement(dataEmail).getText() == ""
                && driver.findElement(dataName).getText() == ""
                && driver.findElement(dataGender).getText() == "Мужской"
                && driver.findElement(checkbox11).getAttribute("checked") == "false"
                && driver.findElement(checkbox12).getAttribute("checked") == "false"
                && driver.findElement(radio21).getAttribute("checked") == "false"
                && driver.findElement(radio22).getAttribute("checked") == "false"
                && driver.findElement(radio23).getAttribute("checked") == "false");
    }

    // clearing fields after each method for proper testing
    public AddUserPage clear() {
        driver.findElement(dataEmail).clear();
        driver.findElement(dataName).clear();
        this.chooseGender("Мужской");
        this.changeBothCheckboxes(false, false);

        return this;
    }
}
