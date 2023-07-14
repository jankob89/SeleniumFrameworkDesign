package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise"))
			throw new IllegalStateException("This is not Home Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "a[href='/delete_account']")
	WebElement menuDeleteAccount;

	@FindBy(css = "a[href='/login']")
	WebElement menuLoginSignin;

	@FindBy(xpath = "//a[contains(text(),'Logged in as')]")
	WebElement menuLoggedinAs;

	@FindBy(css = "a[href='/logout']")
	WebElement menuLogout;

	@FindBy(css = "a[href='/contact_us']")
	WebElement menuContactus;

	@FindBy(css = "a[href='/products']")
	WebElement menuProducts;

	@FindBy(css = "a[href='/view_cart']")
	WebElement menuCart;

	@FindBy(css = "a[href*='AutomationExercise']")
	WebElement menuVideoTutorials;

	public String getLogedinText() {
		waitUntilVisibilityOf(menuLoggedinAs);
		return menuLoggedinAs.getText();
	}

	public Boolean getLoginSigninVisibility() {
		try {
			return menuLoginSignin.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public LoginPage goToLoginPage() {
		waitUntilVisibilityOf(menuLoginSignin);
		menuLoginSignin.click();
		return new LoginPage(driver);
	}

	public ContactusPage goToContactusPage() {
		waitUntilVisibilityOf(menuContactus);
		menuContactus.click();
		return new ContactusPage(driver);
	}

	public ProductsPage goToProductsPage() {
		waitUntilVisibilityOf(menuProducts);
		menuProducts.click();
		return new ProductsPage(driver);
	}

	public CartPage goToCartPage() {
		waitUntilVisibilityOf(menuCart);
		menuCart.click();
		return new CartPage(driver);
	}

	public AccountDeletedPage deleteAccount() {
		waitUntilVisibilityOf(menuDeleteAccount);
		menuDeleteAccount.click();
		return new AccountDeletedPage(driver);
	}

	public LoginPage logout() {
		waitUntilVisibilityOf(menuLogout);
		menuLogout.click();
		return new LoginPage(driver);
	}

	public int getVideoTutorialsStatusCode() throws IOException {
		waitUntilVisibilityOf(menuVideoTutorials);
		URL url = new URL(menuVideoTutorials.getAttribute("href"));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		return conn.getResponseCode();
	}
}
