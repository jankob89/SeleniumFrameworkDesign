package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BasePage {

	public PaymentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Payment"))
			throw new IllegalStateException(
					"This is not Payment Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "input[name='name_on_card']")
	WebElement txtNameOnCard;

	@FindBy(css = "input[name='card_number']")
	WebElement txtCardNumber;

	@FindBy(css = "input[name='cvc']")
	WebElement txtCVC;

	@FindBy(css = "input[name='expiry_month']")
	WebElement txtExpiryMonth;

	@FindBy(css = "input[name='expiry_year']")
	WebElement txtExpiryYear;

	@FindBy(id = "submit")
	WebElement btnPayAndConfirmOrder;

	@FindBy(id = "success_message")
	WebElement lblOrderPlacedSuccessfullyMsg;

	public void enterNameOnCard(String nameOnCard) {
		txtNameOnCard.sendKeys(nameOnCard);
	}

	public void enterCardNumber(String cardNumber) {
		txtCardNumber.sendKeys(cardNumber);
	}

	public void enterCVC(String cvc) {
		txtCVC.sendKeys(cvc);
	}

	public void enterExpiryMonth(String expiryMonth) {
		txtExpiryMonth.sendKeys(expiryMonth);
	}

	public void enterExpiryYear(String expiryYear) {
		txtExpiryYear.sendKeys(expiryYear);
	}

	public OrderPlacedPage clickPayAndConfirmOrder() {
		btnPayAndConfirmOrder.click();
		return new OrderPlacedPage(driver);
	}

	public String getOrderPlacedSuccessfullyMsg() {
		waitUntilVisibilityOf(lblOrderPlacedSuccessfullyMsg);
		return lblOrderPlacedSuccessfullyMsg.getText();
	}
}
