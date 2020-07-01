
package pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;




public class MainPage extends AbstractPage
{
    public MainPage(Browser browser)
    {
        super(browser);
    }

    @FindBy(id = "membership-login-link")
    public WebElement loginButton;

    // Button for baby, toys
    @FindBy(linkText = "Bebek, Oyuncak")
    public WebElement babyToyButton;

    // Button for baby's nappy
    @FindBy(css = "a[data-monitor-ga-action='Bebek Bezi']")
    public WebElement babyNapyButton;

    // Select brand check-box (Prima)
    @FindBy(xpath = "//*[@id=\"page-area\"]/div/div/div[1]/div[1]/div/div[3]/ul/li/ul/li[1]/a")
    public WebElement selectBrand;

    // Select size check-box
    @FindBy(css = "span[data-request-param='216']")
    public WebElement selectSizeButton;

    // Descending order of the selected products
    @FindBy(linkText = "Önce En Yüksek Fiyat")
    public WebElement descendingOrderButton;

    // Text field that shows the current price
    @FindBy(className = "current-price")
    public WebElement current_price;

    // Close the ad "Marketten Teslim AL"
    @FindBy(xpath = "//*[@id=\"deliveryFromStoreAnnouncement\"]/div/div/div/button")
    public WebElement closeAdButton;

    @FindBy(css = ".shoping-cart-icon-block .fa-shopping-cart")
    public WebElement shoppingBasketButton;

    @FindBy(className = "progress-bar-text")
    public WebElement progressBarText;

    @FindBy(css = ".action-td .plus-orange")
    public WebElement plusButton;

    @FindBy(className = "rubbish")
    public WebElement trashButton;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div/div[2]/div[1]/div/form/div[2]/div[3]/button")
    public WebElement addToBasketButton;

    @FindBy(css = "#cart-bar > div > div > div > div > h3")
    public WebElement clearBasketMessage;

    @FindBy (xpath = "//*[@id=\"product-list\"]/div[2]/div[1]/div/form/div[1]/h5/a")
    public WebElement product_description;

    @FindBy(className = "title product-card-title")
    public WebElement product_card_title;

    @FindBy(xpath = "//*[@id=\"phoneNumber-error\"]")
    public WebElement phoneNumberError;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[2]/div[1]/div/div[2]/div[1]/form/div[2]")
    public WebElement loginField;

    @FindBy(xpath = "/html/body/div[2]/div/div[1]/button")
    public WebElement closeSMSNotification;

    @FindBy(xpath = "/html/body/header/div[2]/div[2]/div/div[1]/div/div[3]/div/div[1]/div/div/a")
    public WebElement userDropdown;

    @FindBy(linkText = "Çıkış Yap")
    public WebElement logout;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div/div[3]/nav/button/div")
    public WebElement sortMenuButton;

    @FindBy(linkText = "Önce En Düşük Fiyat")
    public WebElement ascendingOrderButtonByText;

    // Elements for Bonus Part
    @FindBy(id = "search")
    public WebElement search_box;

    @FindBy(xpath = "//*[@id=\"main-search\"]/button")
    public WebElement search_button;

    @FindBy(xpath = "//*[@id=\"page-area\"]/div/div/div[1]/div[1]/div/div[3]/ul/li/ul/li[1]/a/div/span[1]")
    public WebElement brand_coca_cola;

    @FindBy(linkText = "Önce En Düşük Fiyat")
    public WebElement ascendingOrderButton;

    @FindBy(xpath = "//*[@id=\"page-area\"]/div/div/div[3]/div[3]/div[1]/div/form/div[2]/div[3]/button")
    public WebElement addToBasket_2;

    @FindBy(xpath = "//*[@id=\"page-area\"]/div/div/div[3]/div[2]/div/div[2]/nav/button")
    public WebElement other_button;

    @FindBy(xpath = "//*[@id=\"header-menu\"]/div/nav/div[2]/ul/li[3]/a")
    public WebElement sütKahvaltılıkButton;

    @FindBy(css = "a[data-monitor-ga-action='Peynir']")
    public WebElement peynirButton;

    @FindBy(linkText = "Çok Satanlar")
    public WebElement bestsellerButton;

    @FindBy(xpath = "//*[@id=\"product-list\"]/div[2]/div[1]/div/form/div[2]/div[3]/button")
    public WebElement getAddToBasket_3;

    @FindBy(linkText = "İndirim Yüzdesine Göre")
    public WebElement discountPercentage;

    @FindBy(xpath = "//*[@id=\"headerCartContainer\"]/div[1]/div[1]/button/span")
    public WebElement basketAmount;

    @FindBy(xpath = "//*[@id=\"product-list\"]/div[2]/div[1]/div/form/div[2]/div[3]/div/div/button[2]")
    public WebElement increaseButton;

    @FindBy(linkText = "Tüm Kategoriler")
    public WebElement allCategories;

    @FindBy(xpath = "//*[@id=\"cart-bar\"]/div/div[2]/ul/li/form/table[3]/tbody/tr[1]/td[3]/div/div/button[2]")
    public WebElement increaseFirstProduct;

    @FindBy(xpath = "//*[@id=\"cart-bar\"]/div/div[2]/ul/li/form/table[3]/tbody/tr[1]/td[3]/div/div/button[1]")
    public WebElement decreaseFirstProduct;

    @FindBy(className = "go-to-basket-button")
    public WebElement goToBasketButton;

    @FindBy(xpath = "//*[@id=\"checkout-header\"]/div/div/div/div/a")
    public WebElement returnMainPage;








}
