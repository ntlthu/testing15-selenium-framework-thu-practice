package testcases.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CommonDialog;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.components.TopBarNavigation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Register01_Verify_Valid_Register {
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


//        Khai bao page object model
        HomePage homePage = new HomePage(chromeDriver);
        RegisterPage registerPage = new RegisterPage(chromeDriver);
        LoginPage loginPage = new LoginPage(chromeDriver);
        TopBarNavigation topBarNavigation = new TopBarNavigation(chromeDriver);

        //Pre-condition: User is on Register page
        //Click on 'Đăng Ký' link on the top right
        homePage.getTopBarNavigation().navigateToRegisterPage();

        //Step 1: Enter account on 'Tài Khoản' textbox
        String accountName = UUID.randomUUID().toString(); // 32 ky tu
        String email = accountName + "@example.com";
        System.out.println("Account: " + accountName);
        System.out.println("Email: " + email);

        //khai bao locator kieu By (chưa tìm element)
        registerPage.enterAccount(accountName);

        //Step 2: Enter password
        String passWord = "123456";
        registerPage.enterPassword(passWord);

        //Step 3: Re-enter password
        registerPage.reEnterPassword(passWord);

        //Step 4: Enter fullname
        registerPage.enterFullname("Alibaba");

        //Step 5: Enter email
        registerPage.enterEmail(email);

        //Step 6: Click register
        registerPage.clickRegister();

//       Step7:  Verify valid register successfully
//        Verify point 1: 'Đăng ký thành công' message display

//        By byLblRegisterMessage = By.id("swal2-title");
//       WebElement lblWait = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblRegisterMessage));
//       String getTextRecord = lblWait.getText();
        CommonDialog commonDialog = new CommonDialog(chromeDriver);
        String getTextRecord = commonDialog.getMsg();
        Assert.assertEquals(getTextRecord, "Đăng ký thành công", "Register test message fail!");

//       wait cho message dialog biến mất
        commonDialog.waitForDialogDisappear();
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLblRegisterMessage));
//        chờ cho đến khi button được click
//        commonDialog.clickBtnAgree();

// Verify point 2: Login success with registered account (có the khong can vi có thể verify DB hoặc API là được
//        Click đăng nhập link
        registerPage.getTopBarNavigation().navigateToLoginPage();

//        Enter account login
        loginPage.enterLoginAccount(accountName);

//        Enter passowrd
        loginPage.enterLoginPassword(passWord);

//        Click button "Dang Nhap"
        loginPage.clickBtnLogin();

//        VP: "Đăng nhập thành công" message display
//        By byLblLoginMessage = By.id("swal2-title");
//        WebElement lblLoginMessageWait = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblLoginMessage));
//        String getMessageLogin = lblLoginMessageWait.getText();
        String getMessageLogin = commonDialog.getMsg();
        Assert.assertEquals(getMessageLogin, "Đăng nhập thành công", "Login text message fail!");

////        Để tắt pop-up save password:
//        // Disable password save dialog
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        options.setExperimentalOption("prefs", prefs);

        chromeDriver.quit(); // Close browser & Quit chromedriver process
    }
}
