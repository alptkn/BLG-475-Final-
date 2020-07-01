package pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;

public class CategoryPage extends AbstractPage
{
    public CategoryPage(Browser browser)
    {
        super(browser);
    }

    @FindBy(className = "cookie-popup-dismiss")
    public WebElement cookieDismissButton;

    @FindBy(css = ".sub-category-product-list .list:nth-of-type(1) .product-card-button")
    public WebElement addBasket;

    @FindBy(className = "current-category--title")
    public WebElement currentCategoryName;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/div[1]/div/form/div[2]/div[3]/button")
    public WebElement addToBasketButton;


}
