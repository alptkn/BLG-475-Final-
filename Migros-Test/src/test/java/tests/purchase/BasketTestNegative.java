package tests.purchase;

import org.junit.*;
import pages.common.CategoryPage;
import pages.common.MainPage;
import pages.purchase.BasketPage;
import tests.AbstractTest;

import java.util.concurrent.TimeUnit;

public class BasketTestNegative extends AbstractTest {

    @Before
    public void testNegativeSetup() {

        // Setting up all required pages and login
        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        String number = context.getInternalProps().getPhone_number();
        login_negativeTests(number);

        // removing Ads and Cookies
        if (mainPage.closeAdButton.isDisplayed()) {
            browser.waitAndClick(mainPage.closeAdButton);
        }
        if (categoryPage.cookieDismissButton.isDisplayed()) {
            browser.waitAndClick(categoryPage.cookieDismissButton);
        }

        // in case the basket was not emptied correctly
        clearBasket();
    }

    @After
    public void testNegativeTeardown() {

        // Clean up after all testing: Clear basket and logout
        clearBasket();
        logout();

    }


    @Test
    public void testNoDeliveryAddressSubmitted() {

        MainPage mainPage = new MainPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);

        // Filling the basket
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);

        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0, index);
        int price_int = Integer.parseInt(price);
        while (price_int < 60) {
            browser.waitAndClick(mainPage.plusButton);
            price_string = mainPage.current_price.getText();
            index = price_string.indexOf(",");
            price = price_string.substring(0, index);
            price_int = Integer.parseInt(price);
        }

        browser.waitAndClick(mainPage.goToBasketButton);
        if (basketPage.closeAdButton_2.isDisplayed()) {
            basketPage.closeAdButton_2.click();
        }
        browser.waitAndClick(basketPage.confirmBasketButton);
        browser.waitAndClick(basketPage.dataPaymentContinueButton);

        // is the website showing the notification?
        boolean addresserrormessage = basketPage.addressErrorMessage.isDisplayed();
        Assert.assertTrue(addresserrormessage);

        // going back to Homepage after test
        browser.waitAndClick(basketPage.closeMissingAddressNotification);
        browser.waitAndClick(basketPage.homeButton);

    }

    @Test
    public void testLeavingRequiredFieldsOutWhileSupplyingANewAddress() {
        MainPage mainPage = new MainPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);

        // Filling the basket
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);

        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0, index);
        int price_int = Integer.parseInt(price);
        while (price_int < 60) {
            browser.waitAndClick(mainPage.plusButton);
            price_string = mainPage.current_price.getText();
            index = price_string.indexOf(",");
            price = price_string.substring(0, index);
            price_int = Integer.parseInt(price);
        }

        browser.waitAndClick(mainPage.goToBasketButton);
        if (basketPage.closeAdButton_2.isDisplayed()) {
            basketPage.closeAdButton_2.click();
        }
        browser.waitAndClick(basketPage.completeShoppingButton);
        browser.waitAndClick(basketPage.supplyNewAddressButton);
        browser.waitAndClick(basketPage.addAddressLinkButton);
        browser.waitAndClick(basketPage.saveAddressButton);

        // is the website showing the notification?
        String requiredFieldsErrorMessage = basketPage.addressRequiredFieldsErrorLabel.getText();
        Assert.assertEquals("Lütfen bu alanı boş bırakmayın", requiredFieldsErrorMessage);

        // going back to Homepage after test
        browser.waitAndClick(basketPage.closeAddressForm);
        browser.waitAndClick(basketPage.homeButton);

    }

    @Test
    public void testCheckoutWithoutAnyItemInTheBasket() {
        MainPage mainPage = new MainPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);

        // Filling the basket
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        //browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.descendingOrderButton);
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);

        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0, index);
        int price_int = Integer.parseInt(price);
        while (price_int < 60) {
            browser.waitAndClick(mainPage.plusButton);
            price_string = mainPage.current_price.getText();
            index = price_string.indexOf(",");
            price = price_string.substring(0, index);
            price_int = Integer.parseInt(price);
        }

        browser.waitAndClick(mainPage.goToBasketButton);
        if (basketPage.closeAdButton_2.isDisplayed()) {
            basketPage.closeAdButton_2.click();
        }

        // Emptying the basket
        while (browser.isElementDisplayed(basketPage.deleteItemButton)) {
            browser.waitAndClick(basketPage.deleteItemButton);
            if (basketPage.confirmDeletionButton.isDisplayed()) {
                browser.waitAndClick(basketPage.confirmDeletionButton);
            }
        }


        // is the website showing the notification?
        boolean emptyBasketNotification = basketPage.emptyBasketNotification.isDisplayed();
        Assert.assertTrue(emptyBasketNotification);

        // going back to Homepage after test
        browser.waitAndClick(basketPage.homeButtonfromMainPage);

    }

    @Test
    public void testCheckoutWithoutMinimumOrderValue() {
        MainPage mainPage = new MainPage(browser);
        BasketPage basketPage = new BasketPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);

        // Filling the basket
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       // browser.waitAndClick(mainPage.sortMenuButton);
        browser.waitAndClick(mainPage.ascendingOrderButtonByText);
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.waitAndClick(categoryPage.addBasket);
        browser.waitAndClick(mainPage.shoppingBasketButton);

        String price_string = mainPage.current_price.getText();
        int index = price_string.indexOf(",");
        String price = price_string.substring(0, index);
        int price_int = Integer.parseInt(price);

        if (price_int < 60) {
            browser.waitAndClick(mainPage.goToBasketButton);
            if (basketPage.closeAdButton_2.isDisplayed()) {
                basketPage.closeAdButton_2.click();
            }

            // is the notification showing the right text?
            String minimumOderValueNotification = basketPage.minimumOrderValueNotReachedNotification.getText();
            Assert.assertEquals("Market sepet tutarınız minimum 60,00 TL olmalıdır.", minimumOderValueNotification);

            // is it not possible to confirm the basket without minimum order value?
            boolean basketNotConfirmable = !basketPage.completeShoppingButton.isEnabled();
            Assert.assertTrue(basketNotConfirmable);

            // going back to Homepage after test
            browser.waitAndClick(basketPage.homeButtonfromMainPage);

        }


    }


}

