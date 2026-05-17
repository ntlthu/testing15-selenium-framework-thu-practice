package testcases.folder2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Register01_Verify_Valid_Register_BeforRefactor {
    @Test

//    public void test_valid_register() {
//        Assert.assertEquals("Hello", "Hello", "Verify Text Message");
//
//    }
//    public void test_valid_register() {
//        Assert.assertEquals("Hello", "Hello123", "Verify Text Message");
//
//    }

    public void test_valid_register() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // tat automation bar
        options.setExperimentalOption("useAutomationExtension", false);             // tat automation bar

        WebDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize(); // maximize windows
        chromeDriver.get("https://demo1.cybersoft.edu.vn"); // home page

        //khai bao WebDriverWait
//        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));

        //khai bao FluentWait
        FluentWait<WebDriver> wait = new FluentWait<>(chromeDriver);
        wait.withTimeout(Duration.ofSeconds(30)); // set time out chờ
        wait.pollingEvery(Duration.ofSeconds(1)); // sau bao lâu kiểm tra lại
        wait.ignoring(NoSuchElementException.class); // khai báo exception bỏ qua

        //Pre-condition: User is on Register page
        //Click on 'Đăng Ký' link on the top right
        By byLnkRegister = By.linkText("Đăng Ký");
        WebElement lnkRegister = wait.until(ExpectedConditions.visibilityOfElementLocated(byLnkRegister));
        lnkRegister.click();

        //Step 1: Enter account on 'Tài Khoản' textbox
        String accountName = UUID.randomUUID().toString(); // 32 ky tu
        String email = accountName + "@example.com";
        System.out.println("Account: " + accountName);
        System.out.println("Email: " + email);

        //khai bao locator kieu By (chưa tìm element)
        By byTxtAccount = By.id("taiKhoan");
        //khai báo web element
        WebElement txtAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtAccount));
        txtAccount.sendKeys(accountName);

        //Step 2: Enter password
        By byTxtPassword = By.id("matKhau");
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtPassword));
        txtPassword.sendKeys("123456");

        //Step 3: Re-enter password
        By byTxtConfirmPassword = By.id("confirmPassWord");
        WebElement txtConfirmPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtConfirmPassword));
        txtConfirmPassword.sendKeys("123456");

        //Step 4: Enter fullname
        By byTxtFullname = By.id("hoTen");
        WebElement txtFullName = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtFullname));
        txtFullName.sendKeys("John Johnson");

        //Step 5: Enter email
        By byTxtEmail = By.id("email");
        WebElement txtEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtEmail));
        txtEmail.sendKeys(email);

        //Step 6: Click register
        By byBtnRegister = By.className("MuiButton-label");
        WebElement btnRegister = wait.until(ExpectedConditions.elementToBeClickable(byBtnRegister));
        btnRegister.click();

//       Step7:  Verify valid register successfully
//        Verify point 1: 'Đăng ký thành công' message display

        By byLblRegisterMessage = By.id("swal2-title");
       WebElement lblWait = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblRegisterMessage));
       String getTextRecord = lblWait.getText();
       Assert.assertEquals(getTextRecord, "Đăng ký thành công", "Register test message fail!");

//       wait cho message dialog biến mất
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLblRegisterMessage));

// Verify point 2: Login success with registered account (có the khong can vi có thể verify DB hoặc API là được
//        Click đăng nhập link
        By byLnkLogin = By.linkText("Đăng Nhập");
        WebElement lnkLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byLnkLogin));
        lnkLogin.click();

//        Enter account login
        By byTxtLoginAccount = By.id("taiKhoan");
        WebElement txtLoginAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtLoginAccount));
        txtLoginAccount.sendKeys(accountName);

//        Enter passowrd
        By byTxtLoginPass = By.id("matKhau");
        WebElement txtLoginPass = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtLoginPass));
        txtLoginPass.sendKeys("123456");

//        Click button "Dang Nhap"
        By byBtnLogin = By.xpath("//button[@type='submit']/span[text()='Đăng nhập']");
        WebElement btnLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byBtnLogin));
        btnLogin.click();

//        VP: "Đăng nhập thành công" message display
        By byLblLoginMessage = By.id("swal2-title");
        WebElement lblLoginMessageWait = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblLoginMessage));
        String getMessageLogin = lblLoginMessageWait.getText();
        Assert.assertEquals(getMessageLogin, "Đăng nhập thành công", "Login text message fail!");

//        Để tắt pop-up save password:
        // Disable password save dialog
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        chromeDriver.quit(); // Close browser & Quit chromedriver process
    }
}
