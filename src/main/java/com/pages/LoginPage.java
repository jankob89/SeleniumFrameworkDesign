package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Signup / Login"))
			throw new IllegalStateException("This is not Login Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "[data-qa='login-email']")
	WebElement txtLoginEmail;

	@FindBy(css = "[data-qa='login-password']")
	WebElement txtLoginPassword;

	@FindBy(css = "[data-qa='signup-name']")
	WebElement txtSignupName;

	@FindBy(css = "[data-qa='signup-email']")
	WebElement txtSignupEmail;

	@FindBy(css = "[data-qa='login-button']")
	WebElement btnLogin;

	@FindBy(css = "[data-qa='signup-button']")
	WebElement btnSignup;

	@FindBy(css = ".login-form h2")
	WebElement lblLoginUpFormTitle;

	@FindBy(css = ".signup-form h2")
	WebElement lblSignUpFormTitle;

	@FindBy(css = "form[action='/login'] p")
	WebElement lblIncorrectEmailPassMsg;

	@FindBy(css = "form[action='/signup'] p")
	WebElement lblExsistingEmailMsg;

	public HomePage login(String email, String password) {
		txtLoginEmail.sendKeys(email);
		txtLoginPassword.sendKeys(password);
		btnLogin.click();
		return new HomePage(driver);
	}

	public SignupPage signup(String name, String email) {
		txtSignupName.sendKeys(name);
		txtSignupEmail.sendKeys(email);
		btnSignup.click();
		return new SignupPage(driver);
	}

	public LoginPage loginWithIncorrectData(String email, String password) {
		txtLoginEmail.sendKeys(email);
		txtLoginPassword.sendKeys(password);
		btnLogin.click();
		return this;
	}

	public LoginPage signupWithIncorrectData(String name, String email) {
		txtSignupName.sendKeys(name);
		txtSignupEmail.sendKeys(email);
		btnSignup.click();
		return this;
	}

	public String getLoginFormTitle() {
		waitUntilVisibilityOf(lblLoginUpFormTitle);
		return lblLoginUpFormTitle.getText();
	}

	public String getSignupFormTitle() {
		waitUntilVisibilityOf(lblSignUpFormTitle);
		return lblSignUpFormTitle.getText();
	}

	public Boolean getSingupNameFieldRequiredParamValue() {
		return Boolean.parseBoolean(txtSignupName.getAttribute("required"));
	}

	public Boolean getSignupEmailFieldRequiredParamValue() {
		return Boolean.parseBoolean(txtSignupEmail.getAttribute("required"));
	}
	
	public Boolean getLoginEmailFieldRequiredParamValue() {
		return Boolean.parseBoolean(txtLoginEmail.getAttribute("required"));
	}

	public Boolean getLoginPasswordFieldRequiredParamValue() {
		return Boolean.parseBoolean(txtLoginPassword.getAttribute("required"));
	}

	public String getIncorrectEmaiPassMsg() {
		return lblIncorrectEmailPassMsg.getText();
	}

	public String getExsitingEmailMsg() {
		return lblExsistingEmailMsg.getText();
	}

}
