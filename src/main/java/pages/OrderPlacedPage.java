package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPlacedPage extends BasePage {

	public OrderPlacedPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Order Placed"))
			throw new IllegalStateException(
					"This is not Order Placed Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "a[data-qa='continue-button']")
	WebElement btnContinue;

	@FindBy(css = "a[data-qa='continue-button']")
	WebElement btnDownloadInvoice;

	@FindBy(css = "h2[data-qa='order-placed']")
	WebElement lblOrderPlaced;

	public HomePage clickContinue() {
		waitUntilVisibilityOf(btnContinue);
		btnContinue.click();
		return new HomePage(driver);
	}

	public void clickDownloadInvoice() {
		waitUntilVisibilityOf(btnDownloadInvoice);
		btnDownloadInvoice.click();
	}

	public String getOrderPalcedMsg() {
		waitUntilVisibilityOf(lblOrderPlaced);
		return lblOrderPlaced.getText();
	}

}
