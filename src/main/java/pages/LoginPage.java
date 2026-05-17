package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends CommonPage {

    private By byTxtLoginAccount = By.id("taiKhoan");
    private By byTxtLoginPass = By.id("matKhau");
    private By byBtnLogin = By.xpath("//button[@type='submit']/span[text()='Đăng nhập']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

//    hàm Enter account
    public void enterLoginAccount(String accountName) {
//        lúc chưa refactor
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement txtLoginAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtLoginAccount));
//        txtLoginAccount.sendKeys(accountName);

//        sau refactor
        inputText(byTxtLoginAccount, accountName);
    }

//    hàm enter password
    public void enterLoginPassword(String password) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement txtLoginPass = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtLoginPass));
//        txtLoginPass.sendKeys(password);

        inputText(byTxtLoginPass, password);
    }

//    click button Login
    public void clickBtnLogin() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement btnLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byBtnLogin));
//        btnLogin.click();
        click(byBtnLogin);
    }
}
