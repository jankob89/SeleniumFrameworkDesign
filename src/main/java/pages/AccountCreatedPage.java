package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage extends BasePage {

	public AccountCreatedPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Account Created"))
			throw new IllegalStateException(
					"This is not Account Created Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "h2[data-qa='account-created']")
	WebElement lblAccountCreated;

	@FindBy(css = "a[data-qa='continue-button']")
	WebElement btnContinue;

	public String getAccountCreatedMsg() {
		waitUntilVisibilityOf(lblAccountCreated);
		return lblAccountCreated.getText();
	}

	public HomePage clickContinue() {
		waitUntilVisibilityOf(btnContinue);
		btnContinue.click();
		return new HomePage(driver);
	}

}
