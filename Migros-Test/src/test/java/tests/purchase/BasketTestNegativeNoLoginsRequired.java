package tests.purchase;

import org.junit.Assert;
import org.junit.Test;
import pages.common.CategoryPage;
import pages.common.MainPage;
import tests.AbstractTest;

import java.util.concurrent.TimeUnit;

public class BasketTestNegativeNoLoginsRequired extends AbstractTest {

    @Test
    public void testInvalidPhoneNumber() {

        MainPage mainPage = new MainPage(browser);
        String number = context.getInternalProps().getInvalidPhoneNumber();
        login_wrongInput(number);

        // ist the website showing an error message?
        String phoneNumberError = mainPage.phoneNumberError.getText();
        Assert.assertEquals("Telefon numaranızı (5XX) XXX XX XX şeklinde giriniz.", phoneNumberError);

    }

    @Test
    public void testIncompletePhoneNumber() {

        MainPage mainPage = new MainPage(browser);
        String number = context.getInternalProps().getIncompletePhoneNumber();
        login_wrongInput(number);

        // ist the website showing an error message?
        String phoneNumberError = mainPage.phoneNumberError.getText();
        Assert.assertEquals("Lütfen bu alanı boş bırakmayın", phoneNumberError);

    }

    @Test
    public void testNotLoggedInBasketOperation() {

        MainPage mainPage = new MainPage(browser);
        CategoryPage categoryPage = new CategoryPage(browser);
        if (categoryPage.cookieDismissButton.isDisplayed()) {
            browser.waitAndClick(categoryPage.cookieDismissButton);
        }
        browser.waitAndClick(mainPage.babyToyButton);
        browser.waitAndClick(mainPage.babyNapyButton);
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.waitAndClick(categoryPage.addBasket);

        // is the website prompting the login form?
        boolean loginField = mainPage.loginField.isEnabled();
        Assert.assertTrue(loginField);

    }
}
