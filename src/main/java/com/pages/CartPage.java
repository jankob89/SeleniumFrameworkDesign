package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Checkout"))
			throw new IllegalStateException("This is not Cart Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "a[class*='check_out']")
	WebElement btnProceedToCheckout;

	@FindBy(css = "button[class*='close-checkout-modal']")
	WebElement bntContinueOnCart;

	@FindBy(css = "div[class='modal-body'] a[href='/login']")
	WebElement bntRegisterLogin;

	@FindBy(css = "table[class='table table-condensed'] tbody tr")
	List<WebElement> productsList;
	
	@FindBy(id = "empty_cart")
	WebElement lblCartEpmtyMsg;
	
	
	public Boolean getCartEmptyMsgVisibility() {
		waitUntilVisibilityOf(lblCartEpmtyMsg);
		return lblCartEpmtyMsg.isDisplayed();
	}
	
	public int getProductsCount() {
		return productsList.size();
	}

	public String getProductName() {
		return productsList.stream().map(p -> p.findElement(By.cssSelector("td[class='cart_description'] h4 a")).getText())
				.findFirst().map(Object::toString).orElse(null);
	}
	
	public int getProductPrice() {
		return Integer.parseInt(productsList.stream().map(p -> p.findElement(By.cssSelector("td[class='cart_price'] p")).getText().split(". ")[1])
				.findFirst().map(Object::toString).orElse(null));
	}
	
	public int getProductQuantity() {
		return Integer.parseInt(productsList.stream().map(p -> p.findElement(By.cssSelector("td[class='cart_quantity'] button")).getText())
				.findFirst().map(Object::toString).orElse(null));
	}
	
	public int getProductTotal() {
		return Integer.parseInt(productsList.stream().map(p -> p.findElement(By.cssSelector("td[class='cart_total'] p")).getText().split(". ")[1])
				.findFirst().map(Object::toString).orElse(null));
	}
	
	public String[] getProductsNames() {
		List<String> productsNameList = productsList.stream().map(p -> p.findElement(By.tagName("h4")))
				.map(p -> p.getText()).toList();
		return productsNameList.toArray(new String[0]);
	}
	
	public void removeAllProducts() {
		productsList.stream()
				.map(p -> p.findElement(By.cssSelector("a[class='cart_quantity_delete']"))).forEach(p -> p.click());
	}

	public CheckoutPage clickProceedToCheckOut() {
		btnProceedToCheckout.click();
		return new CheckoutPage(driver);
	}

	public void clickContinueOnCart() {
		bntContinueOnCart.click();
	}

	public LoginPage clickRegisterLogin() {
		bntRegisterLogin.click();
		return new LoginPage(driver);
	}
}
