
package tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import java.util.concurrent.ThreadFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.CategoryPage;
import pages.common.MainPage;
import pages.purchase.BasketPage;
import pages.user.LoginPage;
import utils.Browser;
import utils.TestContext;
// Imported for implicit wait
import java.util.concurrent.TimeUnit;


public class AbstractTest
{
    public static TestContext context = new TestContext();
    public static Browser browser = context.doCreateBrowser();

    @BeforeClass
    public static void setUpClass()
    {
        browser.get("https://www.migros.com.tr");
        browser.manage().window().maximize() ;
    }

    @AfterClass
    public static void tearDownClass()
    {
        if (null != browser)
            browser.close();
    }

    public void login_adjust(String phone_number)
    {
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(mainPage.loginButton);
        LoginPage loginPage = new LoginPage(browser);
        browser.waitAndSendKeys(loginPage.phone_number_input, phone_number);
        browser.waitAndClick(loginPage.loginButton);
        /*wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeSMSNotification));
        if (mainPage.closeSMSNotification.isEnabled()){
            mainPage.closeSMSNotification.click();
        }

         */
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));
        browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        Assert.assertNotNull(loginPage.displayName.getText());
    }

    public void login_negativeTests(String phone_number) {
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);
        LoginPage loginPage = new LoginPage(browser);
        browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        browser.waitAndClick(loginPage.phone_number_input);
        browser.waitAndSendKeys(loginPage.phone_number_input, phone_number);
        browser.waitAndClick(loginPage.loginButton);
       /*wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeSMSNotification));
        if (mainPage.closeSMSNotification.isEnabled()){
            mainPage.closeSMSNotification.click();
        }*/
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));

        Assert.assertNotNull(loginPage.displayName.getText());
    }

    public void logout() {
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.userDropdown);
        browser.waitAndClick(mainPage.logout);
    }

    public void login_wrongInput(String phone_number) {
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);
        LoginPage loginPage = new LoginPage(browser);
        browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        browser.waitAndClick(loginPage.phone_number_input);
        browser.waitAndSendKeys(loginPage.phone_number_input, phone_number);
        browser.waitAndClick(loginPage.loginButton);
    }


    public void login_timeout(String phone_number){
        WebDriverWait wait = new WebDriverWait(browser, 60);
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);
        LoginPage loginPage = new LoginPage(browser);
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.waitAndSendKeys(loginPage.phone_number_input, phone_number);
        browser.waitAndClick(loginPage.loginButton);
        try { Thread.sleep(65000); } catch (InterruptedException e){System.out.print("e");}
        System.out.print("i");
        browser.waitAndClick(loginPage.sendOTPAgain);
    }

    public void login(String username, String password)
    {

        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);

        LoginPage loginPage = new LoginPage(browser);

        browser.waitAndSendKeys(loginPage.inputEmail, username);
        browser.waitAndSendKeys(loginPage.inputPassword, password);

        browser.waitAndClick(loginPage.loginButton);


        Assert.assertNotNull(loginPage.displayName.getText());
    }

    public void clearBasket()
    {
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.shoppingBasketButton);

        while (browser.isElementDisplayed(mainPage.trashButton))
        {
            browser.waitAndClick(mainPage.trashButton);
        }

        browser.waitAndClick(mainPage.shoppingBasketButton);
    }
}

