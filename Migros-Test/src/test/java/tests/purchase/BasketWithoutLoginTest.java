
package tests.purchase;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.CategoryPage;
import pages.common.MainPage;
import pages.purchase.BasketPage;
import pages.user.LoginPage;
import utils.Browser;
import utils.TestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasketWithoutLoginTest  {

    public static TestContext context = new TestContext();
    Browser browser = context.doCreateBrowser();
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



    private Boolean brand_chechk(ArrayList<String> data){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).indexOf("Prima") == -1)
                return false;
        }
        return true;
    }



    private Boolean size_check(ArrayList<String> data){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).indexOf(" 4 ") == -1)
                return false;
        }
        return true;
    }

    private Boolean descending_order(ArrayList<Float> data){
        for(int i = 0; i < data.size()-1; i++){
            if(data.get(i) < data.get(i+1))
                return false;
        }
        return true;
    }

    private Boolean login()
    {
        WebDriverWait wait = new WebDriverWait(browser, 40);
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

    @Test
    public void deneme(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndSendKeys(mainPage.search_box,"Gazlı içecekler");
        browser.waitAndClick(mainPage.search_button);
        browser.waitAndClick(mainPage.brand_coca_cola);
        browser.waitAndClick(mainPage.ascendingOrderButton);
        browser.waitAndClick(mainPage.other_button);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.allCategories);
        browser.waitAndClick(mainPage.sütKahvaltılıkButton);
        browser.waitAndClick(mainPage.peynirButton);
        //browser.waitAndClick(mainPage.bestsellerButton);
        browser.waitAndClick(mainPage.discountPercentage);
    }

    @Test
    public void test_a_cookie_dismiss(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        Boolean condition = categoryPage.cookieDismissButton.isDisplayed() == false ? true : false;
        Assert.assertTrue(condition);
    }

    @Test
    public void test_b_baby_napy(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        Boolean condition = categoryPage.currentCategoryName.getText().equals("Bebek Bezi") == true ? true : false;
        System.out.print(condition);
        Assert.assertTrue(condition);
    }

    @Test
    public void test_c_brand(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        List<WebElement> descriptions = browser.findElements(By.xpath("//h5[contains(@class, 'title product-card-title')]/a"));
        System.out.print(descriptions.size());
        ArrayList<String> description_list = new ArrayList<String>();
        for(int i = 0; i < descriptions.size(); i++)
            description_list.add(descriptions.get(i).getText());

        Boolean condition = brand_chechk(description_list);

        Assert.assertTrue(condition);
    }

    @Test
    public void test_d_size(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        List<WebElement> descriptions = browser.findElements(By.xpath("//h5[contains(@class, 'title product-card-title')]/a"));
        ArrayList<String> description_list = new ArrayList<String>();
        for(int i = 0; i < descriptions.size(); i++)
            description_list.add(descriptions.get(i).getText());

        Boolean condition = size_check(description_list);
        Boolean condition_2 = brand_chechk(description_list);

        Assert.assertTrue(condition);
    }

    @Test
    public void test_e_descending_order(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        Boolean condition_1, condition_2, condition_3;
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        List<WebElement> descriptions = browser.findElements(By.xpath("//h5[contains(@class, 'title product-card-title')]/a"));
        ArrayList<String> description_list = new ArrayList<String>();
        ArrayList<Float> prices_float = new ArrayList<Float>();
        ArrayList<String> prices_String = new ArrayList<String>();
        for(int i = 0; i < descriptions.size(); i++)
            description_list.add(descriptions.get(i).getText());
        condition_1 = brand_chechk(description_list);
        condition_2 = size_check(description_list);
        List<WebElement> prices = browser.findElements(By.xpath("//div[contains(@class, 'price-tag')]/span"));
        for(int i = 0; i < prices.size(); i++){
            String temp = prices.get(i).getText();
            temp = temp.substring(0, temp.length()-3);
            temp = temp.replace(",",".");
            prices_float.add(Float.parseFloat(temp));

        }

        condition_3 = descending_order(prices_float);
        Assert.assertTrue(condition_1 && condition_2 && condition_3);
    }

    @Test
    public void test_f_add_to_basket_without_login(){
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        LoginPage loginPage = new LoginPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        Boolean condition = loginPage.loginButton.isDisplayed() == true ? true: false;

        Assert.assertTrue(condition);
    }

    @Test
    public void test_g_login_after_add_to_basket_button(){
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        LoginPage loginPage = new LoginPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));

        Boolean condition = loginPage.displayName.isDisplayed() == true ? true : false;

        Assert.assertTrue(condition);
    }

    @Test
    public void test_h_clear_basket(){
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        LoginPage loginPage = new LoginPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();

        Assert.assertNotNull(mainPage.clearBasketMessage.getText());
    }
    @Test
    public void test_i_check_min_price_limit(){
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        LoginPage loginPage = new LoginPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);
        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0,index);
        int price_int = Integer.parseInt(price);
        while(price_int < 60){
            browser.waitAndClick(mainPage.plusButton);
            price_string =  mainPage.current_price.getText();
            index = price_string.indexOf(",");
            price = price_string.substring(0,index);
            price_int = Integer.parseInt(price);
        }
        System.out.print(price_int);
        Boolean condition = price_int >= 60 ? true: false;
        Assert.assertTrue(condition);
    }

    @Test
    public void test_j_go_to_shopping_basket(){
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        LoginPage loginPage = new LoginPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);
        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0,index);
        int price_int = Integer.parseInt(price);
        while(price_int < 60){
            browser.waitAndClick(mainPage.plusButton);
            price_string =  mainPage.current_price.getText();
            index = price_string.indexOf(",");
            price = price_string.substring(0,index);
            price_int = Integer.parseInt(price);
        }
        browser.waitAndClick(mainPage.goToBasketButton);
        wait.until(ExpectedConditions.elementToBeClickable(basketPage.closeAdButton_2));
        browser.waitAndClick(basketPage.closeAdButton_2);
        Boolean condition = basketPage.price_summary.isDisplayed() == true ? true: false;

        Assert.assertTrue(condition);
    }

    @Test
    public void test_k_last_price_check(){
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        LoginPage loginPage = new LoginPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        //browser.waitAndClick(browser.findElement(By.xpath("//*[@id=\"header-menu\"]/div/nav/div[2]/ul/li[2]/a")));

        //browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);
        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0,index);
        int price_int = Integer.parseInt(price);
        while(price_int < 60){
            browser.waitAndClick(mainPage.plusButton);
            price_string =  mainPage.current_price.getText();
            index = price_string.indexOf(",");
            price = price_string.substring(0,index);
            price_int = Integer.parseInt(price);
        }
        browser.waitAndClick(mainPage.goToBasketButton);
        wait.until(ExpectedConditions.elementToBeClickable(basketPage.closeAdButton_2));
        browser.waitAndClick(basketPage.closeAdButton_2);
        String last_price = basketPage.price_summary.getText();
        Boolean condition = price_string.equals(last_price) == true ? true : false;

        Assert.assertTrue(condition);
    }

    @Test
    public void test_l_end_shopping(){
        WebDriverWait wait = new WebDriverWait(browser, 40);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        LoginPage loginPage = new LoginPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.closeAdButton));
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);
        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0,index);
        int price_int = Integer.parseInt(price);
        while(price_int < 60){
            browser.waitAndClick(mainPage.plusButton);
            price_string =  mainPage.current_price.getText();
            index = price_string.indexOf(",");
            price = price_string.substring(0,index);
            price_int = Integer.parseInt(price);
        }
        browser.waitAndClick(mainPage.goToBasketButton);
        wait.until(ExpectedConditions.elementToBeClickable(basketPage.closeAdButton_2));
        browser.waitAndClick(basketPage.closeAdButton_2);
        String last_price = basketPage.price_summary.getText();
        if(price_string.equals(last_price)){
            if(basketPage.bagChoiceButton.isSelected() == false){
                Actions action = new Actions(browser);
                action.moveToElement(basketPage.bagChoiceButton).click().build().perform();
            }

            browser.waitAndClick(basketPage.completeShoppingButton);
            Boolean condition = basketPage.teslimat_sekli.isDisplayed() == true ? true: false;

            Assert.assertTrue(condition);

        }
    }

}

