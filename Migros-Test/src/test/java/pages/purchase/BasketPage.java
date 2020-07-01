
package pages.purchase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;

public class BasketPage extends AbstractPage {
    public BasketPage(Browser browser) {
        super(browser);
    }

    @FindBy(linkText = "Ürün Notu")
    public WebElement purchaseNote;

    @FindBy(className = "product_note")
    public WebElement inputNote;

    @FindBy(id = "summaryRevenue")
    public WebElement basketTotal;

    @FindBy(id = "summaryRevenue")
    public WebElement price_summary;

    @FindBy(id = "cloth-bag-input")
    public WebElement bagChoiceButton;

    @FindBy(id = "in-cart-next-button")
    public WebElement completeShoppingButton;

    @FindBy(css = "#cartCampaignModal > div > div > div.modal-header > button")
    public WebElement closeAdButton_2;

    @FindBy(className = "title-text")
    public WebElement basket_product_description;

    @FindBy(className = "step-title")
    public WebElement teslimat_sekli;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[2]/div/button")
    public WebElement confirmBasketButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div[3]/button")
    public WebElement dataPaymentContinueButton;

    @FindBy(xpath = "/html/body/div[8]/div/div/div[2]")
    public WebElement addressErrorMessage;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div[2]/div/div[1]/div[2]/div/button")
    public WebElement supplyNewAddressButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div[2]/div/div[1]/div[2]/div/ul/li[1]/a")
    public WebElement addAddressLinkButton;

    @FindBy(xpath = "/html/body/div[3]/div/div/div[2]/form/div[6]/div/div/div[2]/label/button")
    public WebElement saveAddressButton;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[1]/div[3]/div/div/form/div/div[1]/div/div/div[5]/div/a[2]")
    public WebElement deleteItemButton;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[2]/div/div[3]/div[2]/div/div/label[2]/div/ins")
    public WebElement shoppingBagButton;

    @FindBy(xpath = "/html/body/div[10]/div/div/div[2]/div/div[2]/label/button")
    public WebElement confirmDeletionButton;

    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div/div/h4")
    public WebElement emptyBasketNotification;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[3]/span")
    public WebElement minimumOrderValueNotReachedNotification;

    @FindBy(xpath = "/html/body/div[3]/div/div/div[2]/form/div[1]/div/label/label")
    public WebElement addressRequiredFieldsErrorLabel;

    @FindBy(xpath = "/html/body/div[3]/div/div/div[1]/button")
    public WebElement closeAddressForm;

    @FindBy(xpath = "/html/body/div[8]/div/div/div[1]/button")
    public WebElement closeMissingAddressNotification;

    @FindBy(xpath = "/html/body/header/div/div/div/div/div")
    public WebElement homeButton;

    @FindBy(xpath = "/html/body/header/div[1]/div/div/div/div[1]/div/a")
    public WebElement homeButtonfromMainPage;

    // Bonus Part
    @FindBy(xpath = "//*[@id=\"cart-info-content\"]/div/div[1]/div[2]/div/div/form/div[1]/div[1]/div/div/div[5]/div/a[1]/span")
    public WebElement productNote;

    @FindBy(xpath = "//*[@id=\"cart-info-content\"]/div/div[1]/div[2]/div/div/form/div[1]/div[2]/label[2]/textarea")
    public WebElement productNoteTextArea;

    @FindBy(id = "in-cart-next-button")
    public WebElement confirmBasket;

    @FindBy(xpath = "//*[@id=\"add-address-dropdown-button\"]/span")
    public WebElement changeDeliveryAddress;

    @FindBy(xpath = "//*[@id=\"add-delivery-address-list-item\"]/a")
    public WebElement deliveryAddress;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[1]/div/label/input")
    public WebElement addressName;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[2]/div[1]/label/input")
    public WebElement Name;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[2]/div[2]/label/input")
    public WebElement Surname;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[3]/div[1]/label/input")
    public WebElement phoneNumber;

    @FindBy(xpath = "//*[@id=\"city_chosen\"]/a")
    public WebElement city;
    @FindBy(xpath = "//*[@id=\"city_chosen\"]/div/ul/li[3]")
    public WebElement selectAnkara;
    @FindBy(xpath = "//*[@id=\"town_chosen\"]/a")
    public WebElement state;
    @FindBy(xpath = "//*[@id=\"town_chosen\"]/div/ul/li[8]")
    public WebElement selectCankaya;

    @FindBy(xpath = "//*[@id=\"district_chosen\"]/a")
    public WebElement neighboourhood;
    @FindBy(xpath = "//*[@id=\"district_chosen\"]/div/ul/li[102]")
    public WebElement selectRemziOguzArık;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[5]/div[1]/label/div/a")
    public WebElement selectStrret;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[5]/div[1]/label/div/div/ul/li[8]")
    public WebElement selectDefneStreet;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[5]/div[2]/label/input")
    public WebElement appartmentNo;
    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[6]/div/label/textarea")
    public WebElement additionalAddress;

    @FindBy(xpath = "//*[@id=\"delivery-address-form\"]/div[6]/div/div/div[2]/label/button")
    public WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"orderNote\"]")
    public WebElement orderNote;

    @FindBy(xpath = "//*[@id=\"cart-info\"]/div[2]/div/div[3]/button")
    public WebElement goToDeliveryTime;

    @FindBy(xpath = "//*[@id=\"deliveryTimeSelection\"]/div/div/div/table/tbody/tr[6]/td[4]/label")
    public WebElement selectDeliveryTime;

    @FindBy(xpath = "//*[@id=\"cart-info\"]/div[2]/div/div[3]/button")
    public WebElement goToPayment;

    @FindBy(xpath = "//*[@id=\"cart-info\"]/div[2]/div/div[4]/button")
    public WebElement confirmOrder;
}

