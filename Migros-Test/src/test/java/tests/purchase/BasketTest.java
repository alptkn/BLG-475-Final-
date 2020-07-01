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


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasketTest
{
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

    public Boolean login_timeout(){
        WebDriverWait wait = new WebDriverWait(browser, 60);
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);
        LoginPage loginPage = new LoginPage(browser);
        browser.waitAndSendKeys(loginPage.phone_number_input, context.getInternalProps().getPhone_number());
        browser.waitAndClick(loginPage.loginButton);
        try { Thread.sleep(65000); } catch (InterruptedException e){System.out.print("e");}

        return loginPage.sendOTPAgain.isEnabled();
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
    public void test_a_login_timeout(){
        Boolean condition = login_timeout();

        Assert.assertTrue(condition);
    }


    @Test
    public void test_b_clear_basket(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();

        Assert.assertNotNull(mainPage.clearBasketMessage.getText());
    }

    @Test
    public void test_c_cookie_dismiss(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        browser.waitAndClick(categoryPage.cookieDismissButton);
        Boolean condition_2 = !(categoryPage.cookieDismissButton.isDisplayed());
        Assert.assertTrue(condition_2);
    }

    @Test
    public void test_d_select_baby_napy(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        Boolean condition_2 = categoryPage.currentCategoryName.getText().contentEquals("Bebek Bezi") == true ? true: false;
        Assert.assertTrue(condition_2);
    }

    @Test
    public void test_e_brand(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);

        browser.waitAndClick(mainPage.selectBrand);
        List<WebElement> descriptions = browser.findElements(By.xpath("//h5[contains(@class, 'title product-card-title')]/a"));
        System.out.print(descriptions.size());
        ArrayList<String> description_list = new ArrayList<String>();
        for(int i = 0; i < descriptions.size(); i++)
            description_list.add(descriptions.get(i).getText());

        Boolean condition_2 = brand_chechk(description_list);

        Assert.assertTrue(condition_2);

    }

    @Test
    public void test_f_size(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        List<WebElement> descriptions = browser.findElements(By.xpath("//h5[contains(@class, 'title product-card-title')]/a"));
        System.out.print(descriptions.size());
        ArrayList<String> description_list = new ArrayList<String>();
        for(int i = 0; i < descriptions.size(); i++)
            description_list.add(descriptions.get(i).getText());

        Boolean condition_2 = brand_chechk(description_list);
        Boolean condition_3 = size_check(description_list);

        Assert.assertTrue(condition_2 && condition_3);


    }

    @Test
    public void test_g_descending_order(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        List<WebElement> descriptions = browser.findElements(By.xpath("//h5[contains(@class, 'title product-card-title')]/a"));
        System.out.print(descriptions.size());
        ArrayList<String> description_list = new ArrayList<String>();
        for(int i = 0; i < descriptions.size(); i++)
            description_list.add(descriptions.get(i).getText());

        Boolean condition_2 = brand_chechk(description_list);
        Boolean condition_3 = size_check(description_list);
        ArrayList<Float> prices_float = new ArrayList<Float>();
        List<WebElement> prices = browser.findElements(By.xpath("//div[contains(@class, 'price-tag')]/span"));
        for(int i = 0; i < prices.size(); i++){
            String temp = prices.get(i).getText();
            temp = temp.substring(0, temp.length()-3);
            temp = temp.replace(",",".");
            prices_float.add(Float.parseFloat(temp));

        }

        Boolean condition_4 = descending_order(prices_float);

        Assert.assertTrue(condition_2 && condition_3 && condition_4);
    }

    @Test
    public void test_h_adding_to_basket(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);

        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
        browser.waitAndClick(mainPage.shoppingBasketButton);
        String description_1 = mainPage.product_description.getText();
        String description_2 = basketPage.basket_product_description.getText();
        Boolean condition_2 = description_1.equals(description_2) ? true: false;
        Assert.assertTrue(condition_2);

    }

    @Test
    public void test_i_minimum_price_limit(){
        Boolean condition = login();
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);
        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);

        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasketButton);
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
        Boolean condition_2 = price_int >= 60 ? true: false;
        Assert.assertTrue(condition_2);
    }

    @Test
    public void test_j_go_to_shopping_basket(){
        WebDriverWait wait = new WebDriverWait(browser, 30);
        LoginPage loginPage = new LoginPage(browser);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        Boolean condition = login();
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);



        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);


        browser.waitAndClick(mainPage.babyToyButton);

        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
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
        try { Thread.sleep(30000); } catch (InterruptedException e){System.out.print("e");}
        browser.waitAndClick(basketPage.closeAdButton_2);
        Boolean condition_2 = basketPage.price_summary.isDisplayed() == true ? true: false;

        Assert.assertTrue(condition_2);
    }

    @Test
    public void test_k_last_check_price(){
        WebDriverWait wait = new WebDriverWait(browser, 30);
        LoginPage loginPage = new LoginPage(browser);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        Boolean condition = login();
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);



        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);


        browser.waitAndClick(mainPage.babyToyButton);

        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
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
        try { Thread.sleep(30000); } catch (InterruptedException e){System.out.print("e");}
        browser.waitAndClick(basketPage.closeAdButton_2);
        String last_price = basketPage.price_summary.getText();
        Boolean condition_2 = price_string.equals(last_price) == true ? true : false;

        Assert.assertTrue(condition_2);
    }
    @Test
    public void test_l_end_shopping(){
        WebDriverWait wait = new WebDriverWait(browser, 30);
        LoginPage loginPage = new LoginPage(browser);
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        Boolean condition = login();
        if(mainPage.closeAdButton.isDisplayed())
            browser.waitAndClick(mainPage.closeAdButton);



        clearBasket();
        browser.waitAndClick(categoryPage.cookieDismissButton);


        browser.waitAndClick(mainPage.babyToyButton);

        browser.waitAndClick(mainPage.babyNapyButton);
        browser.waitAndClick(mainPage.selectBrand);
        browser.waitAndClick(mainPage.selectSizeButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
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
            Boolean condition_2 = basketPage.teslimat_sekli.isDisplayed() == true ? true: false;

            Assert.assertTrue(condition_2);

        }
    }

}
