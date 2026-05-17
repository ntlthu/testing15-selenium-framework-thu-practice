package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends CommonPage{

//    Thuoc tinh (attribute): locators
    private By byTxtAccount = By.id("taiKhoan");
    private By byTxtPassword = By.id("matKhau");
    private By byTxtConfirmPassword = By.id("confirmPassWord");
    private By byTxtFullname = By.id("hoTen");
    private  By byTxtEmail = By.id("email");
    private By byBtnRegister = By.className("MuiButton-label");

//    Hàm khởi tạo, hàm này được tự động add sau khi bấm vào chữ driver chỗ private WebDriver driver;
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter account name into account textfield
     * @param accountName
     */
    public void enterAccount(String accountName) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement txtAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtAccount));
//        txtAccount.sendKeys(accountName);
        inputText(byTxtAccount, accountName);

    }

    public void enterPassword(String password){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtPassword));
//        txtPassword.sendKeys(password);
        inputText(byTxtPassword, password);
    }

    public void reEnterPassword(String password){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        By byTxtConfirmPassword = By.id("confirmPassWord");
//        WebElement txtConfirmPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtConfirmPassword));
//        txtConfirmPassword.sendKeys(password);
        inputText(byTxtConfirmPassword, password);

    }

    public void enterFullname(String fullName) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        By byTxtFullname = By.id("hoTen");
//        WebElement txtFullName = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtFullname));
//        txtFullName.sendKeys(fullName);
        inputText(byTxtFullname, fullName);
    }

    public void enterEmail(String email) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        By byTxtEmail = By.id("email");
//        WebElement txtEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtEmail));
//        txtEmail.sendKeys(email);
        inputText(byTxtEmail, email);
    }

    public void clickRegister(){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        By byBtnRegister = By.className("MuiButton-label");
//        WebElement btnRegister = wait.until(ExpectedConditions.elementToBeClickable(byBtnRegister));
//        btnRegister.click();
        click(byBtnRegister);
    }
}
