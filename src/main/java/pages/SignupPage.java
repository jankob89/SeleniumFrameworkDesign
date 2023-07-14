package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {

	public SignupPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Signup")) {
			throw new IllegalStateException("This is not Signup Page," + " current page is: " + driver.getCurrentUrl());
		}
	}

	@FindBy(id = "id_gender1")
	WebElement rdoTitleMr;

	@FindBy(id = "id_gender2")
	WebElement rdoTitleMrs;

	@FindBy(css = "[data-qa='password']")
	WebElement txtSignupPassword;

	@FindBy(id = "days")
	WebElement ddlDays;

	@FindBy(id = "months")
	WebElement ddlMonths;

	@FindBy(id = "years")
	WebElement ddlYears;

	@FindBy(id = "newsletter")
	WebElement chkNewsletter;

	@FindBy(id = "optin")
	WebElement chkSpecialOffers;

	@FindBy(id = "first_name")
	WebElement txtFirstName;

	@FindBy(id = "last_name")
	WebElement txtLastName;

	@FindBy(id = "company")
	WebElement txtCompany;

	@FindBy(id = "address1")
	WebElement txtAddress1;

	@FindBy(id = "address2")
	WebElement txtAddress2;

	@FindBy(id = "country")
	WebElement ddlCountry;

	@FindBy(id = "state")
	WebElement txtState;

	@FindBy(id = "city")
	WebElement txtCity;

	@FindBy(id = "zipcode")
	WebElement txtZipcode;

	@FindBy(id = "mobile_number")
	WebElement txtMobileNumber;

	@FindBy(css = "[data-qa='create-account']")
	WebElement btnCreateAccount;

	@FindBy(css = "div[class='login-form'] h2")
	WebElement lblSignUpPageInfo;

	@FindBy(css = "a[data-qa='continue-button']")
	WebElement btnContinue;

	public void selectTitle(String title) {
		switch (title) {
		case "Mr." -> rdoTitleMr.click();
		case "Mrs." -> rdoTitleMrs.click();
		default -> throw new IllegalArgumentException("Unexpected value: " + title);
		}
	}

	public void enterPassword(String password) {
		txtSignupPassword.sendKeys(password);
	}

	public void setDateOfBirth(String day, String month, String year) {
		Select select;
		select = new Select(ddlDays);
		select.selectByVisibleText(day);
		select = new Select(ddlMonths);
		select.selectByVisibleText(month);
		select = new Select(ddlYears);
		select.selectByVisibleText(year);
	}

	public void checkNewsletter() {
		chkNewsletter.click();
	}

	public void checkSpecialOffers() {
		chkSpecialOffers.click();
	}

	public void enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}

	public void enterCompany(String company) {
		txtCompany.sendKeys(company);
	}

	public void enterAddress1(String address) {
		txtAddress1.sendKeys(address);
	}

	public void enterAddress2(String address) {
		txtAddress2.sendKeys(address);
	}

	public void selectCountry(String country) {
		Select select = new Select(ddlCountry);
		select.selectByVisibleText(country);
	}

	public void enterState(String state) {
		txtState.sendKeys(state);
	}

	public void enterCity(String city) {
		txtCity.sendKeys(city);
	}

	public void enterZipcode(String zipcode) {
		txtZipcode.sendKeys(zipcode);
	}

	public void enterMobileNumber(String mobileNumber) {
		txtMobileNumber.sendKeys(mobileNumber);
	}

	public AccountCreatedPage clickCreateAccount() {
		btnCreateAccount.click();
		return new AccountCreatedPage(driver);
	}

	public String getSignupPageInfo() {
		waitUntilVisibilityOf(lblSignUpPageInfo);
		return lblSignUpPageInfo.getText();
	}

	public void clickContinue() {
		btnContinue.click();
	}

}
