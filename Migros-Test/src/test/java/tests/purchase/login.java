package tests.purchase;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.CategoryPage;
import pages.common.MainPage;
import pages.user.LoginPage;
import utils.Browser;
import utils.TestContext;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class login {

    private static TestContext context = new TestContext();
    private Browser browser = context.doCreateBrowser();
    @Before
    public void setUpClass() {

        browser.get("https://www.migros.com.tr");
        browser.manage().window().maximize();

    }

    @After
    public void tearDownClass()
    {
        if (null != browser)
            browser.quit();
    }

    private Boolean login()
    {WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);
        LoginPage loginPage = new LoginPage(browser);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        /*wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeSMSNotification));
        if (mainPage.closeSMSNotification.isEnabled()){
            mainPage.closeSMSNotification.click();
        }
        */
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));

        if(loginPage.displayName.isDisplayed())
            return true;
        return false;
    }

    @Test
    public void test_1_login_after_select_category(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        Boolean condition = login();

        Assert.assertTrue(condition);
    }

    @Test
    public void test_2_login_after_select_brand(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        Boolean condition = login();

        Assert.assertTrue(condition);
    }

    @Test
    public void test_3_login_after_select_size(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        Boolean condition = login();

        Assert.assertTrue(condition);
    }

    @Test
    public void test_4_login_after_price_order(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        Boolean condition = login();

        Assert.assertTrue(condition);
    }
}
