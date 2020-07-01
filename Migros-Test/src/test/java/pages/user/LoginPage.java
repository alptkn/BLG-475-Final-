package pages.user;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;

public class LoginPage extends AbstractPage
{
    public LoginPage(Browser browser)
    {
        super(browser);
    }

    @FindBy(css = "#signInForm > div.form-part.email-part > label > input[type=email]")
    public WebElement inputEmail;

    @FindBy(css = "#signInForm > div.form-part.password-part > label > input[type=password]")
    public WebElement inputPassword;

    @FindBy(id = "membership-modal-login-button")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"header-sticky\"]/div[1]/div/div[3]/div/div/div/div/a/span")
    public WebElement displayName;

    @FindBy(xpath = "//*[@id=\"header-sticky\"]/div[1]/div/div[3]/div/div/div/div/a")
    public WebElement nameButton;

    @FindBy(id = "phoneNumber")
    public WebElement phone_number_input;

    @FindBy(id = "sendAgainOtpPass")
    public WebElement sendOTPAgain;
}
