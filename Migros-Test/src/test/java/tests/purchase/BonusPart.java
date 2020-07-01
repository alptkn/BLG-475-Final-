package tests.purchase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.CategoryPage;
import pages.common.MainPage;
import pages.purchase.BasketPage;
import pages.user.LoginPage;
import tests.AbstractTest;

import java.util.List;

public class BonusPart extends AbstractTest {
    @Before
    public void BonusPartSetUp(){

        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        String number = context.getInternalProps().getPhone_number();
        login_adjust(number);
        clearBasket();
    }

    @After
    public void BonusPartTearDown(){
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.returnMainPage);
        logout();
    }

    @Test
    public void test_bonus_part(){
        WebDriverWait wait = new WebDriverWait(browser, 30);
        MainPage mainPage = new MainPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        browser.waitAndSendKeys(mainPage.search_box,"Gazlı içecekler");
        browser.waitAndClick(mainPage.search_button);
        browser.waitAndClick(mainPage.brand_coca_cola);
        browser.waitAndClick(mainPage.ascendingOrderButton);
        browser.waitAndClick(mainPage.addToBasket_2);
        browser.waitAndClick(mainPage.other_button);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(mainPage.addToBasket_2);
        browser.waitAndClick(mainPage.allCategories);
        browser.waitAndClick(mainPage.sütKahvaltılıkButton);
        browser.waitAndClick(mainPage.peynirButton);
        browser.waitAndClick(mainPage.bestsellerButton);
        browser.waitAndClick(mainPage.getAddToBasket_3);
        browser.waitAndClick(mainPage.discountPercentage);
        browser.waitAndClick(mainPage.getAddToBasket_3);
        String price_string = mainPage.basketAmount.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0,index);
        int price_int = Integer.parseInt(price);
        System.out.print(price);
        while(price_int < 100){
            browser.waitAndClick(mainPage.increaseButton);
            price_string =  mainPage.basketAmount.getText();
            System.out.print(price_string);
            index = price_string.indexOf(",");
            price = price_string.substring(0,index);
            price_int = Integer.parseInt(price);
        }
        browser.waitAndClick(mainPage.shoppingBasketButton);
        browser.waitAndClick(mainPage.increaseFirstProduct);
        browser.waitAndClick(mainPage.increaseFirstProduct);
        browser.waitAndClick(mainPage.increaseFirstProduct);
        browser.waitAndClick(mainPage.decreaseFirstProduct);
        browser.waitAndClick(mainPage.goToBasketButton);
        wait.until(ExpectedConditions.elementToBeClickable(basketPage.closeAdButton_2));
        browser.waitAndClick(basketPage.closeAdButton_2);
        browser.waitAndClick(basketPage.productNote);
        browser.waitAndSendKeys(basketPage.productNoteTextArea, "ASD");
        browser.waitAndClick(basketPage.confirmBasket);
        browser.waitAndClick(basketPage.changeDeliveryAddress);
        browser.waitAndClick(basketPage.deliveryAddress);
        browser.waitAndSendKeys(basketPage.addressName, "Ev");
        browser.waitAndSendKeys(basketPage.Name, "Alpay");
        browser.waitAndSendKeys(basketPage.Surname, "Tekin");
        //browser.waitAndSendKeys(basketPage.phoneNumber, "5533046599");
        browser.waitAndClick(basketPage.city);
        browser.waitAndClick(basketPage.selectAnkara);
        browser.waitAndClick(basketPage.state);
        browser.waitAndClick(basketPage.selectCankaya);
        browser.waitAndClick(basketPage.neighboourhood);
        browser.waitAndClick(basketPage.selectRemziOguzArık);
        browser.waitAndClick(basketPage.selectStrret);
        browser.waitAndClick(basketPage.selectDefneStreet);
        browser.waitAndSendKeys(basketPage.appartmentNo, "10");
        browser.waitAndSendKeys(basketPage.additionalAddress, "ASD");
        System.out.print("4");
        browser.waitAndClick(basketPage.saveButton);
        browser.waitAndSendKeys(basketPage.orderNote, "ASD");
        System.out.print("5");
        browser.waitAndClick(basketPage.goToDeliveryTime);
        System.out.print("6");
        browser.waitAndClick(basketPage.selectDeliveryTime);
        System.out.print("7");
        browser.waitAndClick(basketPage.goToPayment);
        Boolean condition = basketPage.confirmOrder.isDisplayed() == true ? true: false;

        Assert.assertTrue(condition);



    }
}
