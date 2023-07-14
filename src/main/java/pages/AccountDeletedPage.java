package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDeletedPage extends BasePage {

	public AccountDeletedPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Account Created"))
			throw new IllegalStateException(
					"This is not Account Deleted Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "h2[data-qa='account-deleted']")
	WebElement lblAccountDeleted;

	@FindBy(css = "a[data-qa='continue-button']")
	WebElement btnContinue;

	public String getAccountDeletedMsg() {
		waitUntilVisibilityOf(lblAccountDeleted);
		return lblAccountDeleted.getText();
	}

	public HomePage clickContinue() {
		btnContinue.click();
		return new HomePage(driver);
	}
}
