package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Checkout"))
			throw new IllegalStateException(
					"This is not Checkout Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "ul[id='address_delivery'] li:nth-child(2)")
	WebElement lblNameDA;

	@FindBy(css = "ul[id='address_delivery'] li:nth-child(3)")
	WebElement lblCompanyNameDA;

	@FindBy(css = "ul[id='address_delivery'] li:nth-child(4)")
	WebElement lblAddress1DA;

	@FindBy(css = "ul[id='address_delivery'] li:nth-child(5)")
	WebElement lblAddress2DA;

	@FindBy(css = "ul[id='address_delivery'] li:nth-child(6)")
	WebElement lblCityStatePostcodeDA;

	@FindBy(css = "ul[id='address_delivery'] li:nth-child(7)")
	WebElement lblCountryNameDA;

	@FindBy(css = "ul[id='address_delivery'] li:nth-child(8)")
	WebElement lblPhoneNumberDA;

	@FindBy(css = "ul[id='address_invoice'] li:nth-child(2)")
	WebElement lblNameBA;

	@FindBy(css = "ul[id='address_invoice'] li:nth-child(3)")
	WebElement lblCompanyNameBA;

	@FindBy(css = "ul[id='address_invoice'] li:nth-child(4)")
	WebElement lblAddress1BA;

	@FindBy(css = "ul[id='address_invoice'] li:nth-child(5)")
	WebElement lblAddress2BA;

	@FindBy(css = "ul[id='address_invoice'] li:nth-child(6)")
	WebElement lblCityStatePostcodeBA;

	@FindBy(css = "ul[id='address_invoice'] li:nth-child(7)")
	WebElement lblCountryNameBA;

	@FindBy(css = "ul[id='address_invoice'] li:nth-child(8)")
	WebElement lblPhoneNumberBA;

	@FindBy(css = "textarea[name='message']")
	WebElement txtComment;

	@FindBy(css = "a[class*='check_out']")
	WebElement btnPlaceOrder;
	
	@FindBy(css = "table tbody tr:nth-last-child(1) p")
	WebElement lblTotalAmountValue;

	@FindBy(css = "table[class='table table-condensed'] tbody h4 a")
	List<WebElement> productsList;

	public String[] getProductsNames() {
		List<String> productsNameList = productsList.stream().map(p -> p.getText()).toList();
		return productsNameList.toArray(new String[0]);
	}

	public void enterComment(String comment) {
		txtComment.sendKeys(comment);
	}

	public PaymentPage clickPlaceOrder() {
		waitUntilVisibilityOf(btnPlaceOrder);
		btnPlaceOrder.click();
		return new PaymentPage(driver);
	}

	public String getDeliveryAddressName() {
		return lblNameDA.getText();
	}

	public String getDeliveryAddressCompanyName() {
		return lblCompanyNameDA.getText();
	}

	public String getDeliveryAddressAddress1() {
		return lblAddress1DA.getText();
	}

	public String getDeliveryAddressAddress2() {
		return lblAddress2DA.getText();
	}

	public String getDeliveryAddressResidence() {
		return lblCityStatePostcodeDA.getText();
	}

	public String getDeliveryAddressCountryName() {
		return lblCountryNameDA.getText();
	}

	public String getDeliveryAddressPhoneNumber() {
		return lblPhoneNumberDA.getText();
	}

	public String getBillingAddressName() {
		return lblNameBA.getText();
	}

	public String getBillingAddressCompanyName() {
		return lblCompanyNameBA.getText();
	}

	public String getBillingAddressAddress1() {
		return lblAddress1BA.getText();
	}

	public String getBillingAddressAddress2() {
		return lblAddress2BA.getText();
	}

	public String getBillingAddressResidence() {
		return lblCityStatePostcodeBA.getText();
	}

	public String getBillingAddressCountryName() {
		return lblCountryNameBA.getText();
	}

	public String getBillingAddressPhoneNumber() {
		return lblPhoneNumberBA.getText();
	}
	
	public int getTotalAmountValue() {
		return Integer.parseInt(lblTotalAmountValue.getText().split(". ")[1]);
	}
	

}
