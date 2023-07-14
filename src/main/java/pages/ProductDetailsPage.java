package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Product Details"))
			throw new IllegalStateException(
					"This is not Product Details Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "div[class='product-information'] h2")
	WebElement lblProductName;

	@FindBy(css = "div[class='product-information'] p:nth-child(3)")
	WebElement lblCategory;

	@FindBy(css = "div[class='product-information'] span span")
	WebElement lblPrice;

	@FindBy(css = "div[class='product-information'] p:nth-child(6)")
	WebElement lblAvailability;

	@FindBy(css = "div[class='product-information'] p:nth-child(7)")
	WebElement lblCondition;

	@FindBy(css = "div[class='product-information'] p:nth-child(8)")
	WebElement lblBrand;
	
	@FindBy(id = "quantity")
	WebElement inputQuantity;
	
	@FindBy(css = "button[class*='btn-default cart']")
	WebElement btnAddToCart;
	
	@FindBy(css = "p[class='text-center'] a[href='/view_cart']")
	WebElement btnViewCart;

	
	public Boolean getVisibilityOfProductName() {
		return lblProductName.isDisplayed();
	}

	public Boolean getVisibilityOfCategory() {
		return lblCategory.isDisplayed();
	}

	public Boolean getVisibilityOfPrice() {
		return lblPrice.isDisplayed();
	}

	public Boolean getVisibilityOfAvailability() {
		return lblAvailability.isDisplayed();
	}

	public Boolean getVisibilityOfCondition() {
		return lblCondition.isDisplayed();
	}

	public Boolean getVisibilityOfBrand() {
		return lblBrand.isDisplayed();
	}

	public String getProductName() {
		return lblProductName.getText();
	}

	public String getCategory() {
		return lblCategory.getText().split(": ")[1];
	}

	public String getPrice() {
		return lblPrice.getText();
	}

	public String getAvailability() {
		return lblAvailability.getText().split(": ")[1];
	}

	public String getCondition() {
		return lblCondition.getText().split(": ")[1];
	}

	public String getBrand() {
		return lblBrand.getText().split(": ")[1];
	}
	
	public void increaseQuantityByValue(int value) {
		for (int i = 1; i < value; i++) 
			inputQuantity.sendKeys(Keys.ARROW_UP);
	}
	
	public void clickAddToCart() {
		btnAddToCart.click();
	}
	
	public CartPage goToCart() {
		waitUntilVisibilityOf(btnViewCart);
		btnViewCart.click();
		return new CartPage(driver);
	}

}
