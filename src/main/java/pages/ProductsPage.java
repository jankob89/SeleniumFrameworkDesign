package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BasePage {

	public ProductsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - All Products"))
			throw new IllegalStateException(
					"This is not Products Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "a[href='/product_details/1']")
	WebElement btnFirstProductViewDeatails;

	@FindBy(id = "search_product")
	WebElement txtSearchProduct;

	@FindBy(id = "submit_search")
	WebElement btnSearch;

	@FindBy(css = "div[class='single-products'] div p")
	List<WebElement> lblSerchedProductNameList;

	@FindBy(css = "a[class*='add-to-cart']")
	WebElement btnAddToCart;

	@FindBy(css = "button[class*='close-modal btn-block']")
	WebElement btnContinueShopping;

	@FindBy(css = "h4[class='modal-title w-100']")
	WebElement lblAdded;

	@FindBy(css = "div[class='product-image-wrapper']")
	List<WebElement> productsList;


	public ProductDetailsPage goTintoProductDetailsByName(String product) {
		productsList.stream()
				.filter(p -> p.findElement(By.cssSelector("div[class='productinfo text-center'] p")).getText()
						.equalsIgnoreCase(product))
				.findFirst().map(p -> p.findElement(By.cssSelector("div[class='choose'] a"))).ifPresent(p -> p.click());
		return new ProductDetailsPage(driver);
	}

	public void addProductsByName(String[] products) {
		for (String product : products) {
			productsList.stream()
					.filter(p -> p.findElement(By.cssSelector("div[class='productinfo text-center'] p")).getText()
							.equalsIgnoreCase(product))
					.findFirst().map(p -> p.findElement(By.cssSelector("div[class='productinfo text-center'] a")))
					.ifPresent(p -> p.click());
			waitUntilVisibilityOf(btnContinueShopping);
			btnContinueShopping.click();
		}
	}


	public void addProductByName(String product) {
		productsList.stream().filter(p -> p.findElement(By.tagName("p")).getText().equalsIgnoreCase(product))
				.findFirst().map(p -> p.findElement(By.tagName("a"))).ifPresent(p -> p.click());
		waitUntilVisibilityOf(btnContinueShopping);
		btnContinueShopping.click();
	}

	public int getSerchedProductsCount(String productName) {
		int counter = 0;
		for (WebElement product : lblSerchedProductNameList)
			if (product.getText().toLowerCase().contains(productName.toLowerCase()))
				counter++;
		return counter;
	}

	public void searchProduct(String productName) {
		txtSearchProduct.sendKeys(productName);
		btnSearch.click();
	}

	public ProductDetailsPage goToFirstProductDetails() {
		btnFirstProductViewDeatails.click();
		return new ProductDetailsPage(driver);
	}

	public void clickAddToCart() {
		btnAddToCart.click();
	}

	public void clickContinueShopping() {
		waitUntilVisibilityOf(lblAdded);
		btnContinueShopping.click();
	}

}
