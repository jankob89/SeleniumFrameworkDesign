package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pages.AccountCreatedPage;
import com.pages.AccountDeletedPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.SignupPage;
import com.testcomponents.BaseTests;
import com.util.UniqueNumberCreator;

public class SignupTests extends BaseTests {

	private String email;
	private String password = "Password12#";

	@Test
	public void signinWithCorrectData() {
		final String accountCreatedMsg = "ACCOUNT CREATED!";	
		String name = "Testname_" + UniqueNumberCreator.getNumber();
		email = "correct.email" + UniqueNumberCreator.getNumber() + "@gmail.com";	;
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		SignupPage signupPage = loginPage.signup(name, email);
		signupPage.enterPassword(password);
		signupPage.selectTitle("Mr.");
		signupPage.setDateOfBirth("11", "June", "1989");
		signupPage.checkNewsletter();
		signupPage.checkSpecialOffers();
		signupPage.enterFirstName("John");
		signupPage.enterLastName("Doe");
		signupPage.enterCompany("Some IT Company");
		signupPage.enterAddress1("Robson Street, Vancouver");
		signupPage.enterAddress2("British Columbia");
		signupPage.selectCountry("Canada");
		signupPage.enterState("Nova Scotia");
		signupPage.enterCity("Toronto");
		signupPage.enterZipcode("33-411");
		signupPage.enterMobileNumber("+44664031359");

		AccountCreatedPage accountCreatedPage = signupPage.clickCreateAccount();
		Assert.assertEquals(accountCreatedPage.getAccountCreatedMsg(), accountCreatedMsg);
		homePage = accountCreatedPage.clickContinue();
	}

	@Test(dependsOnMethods = "signinWithCorrectData")
	public void removeCreatedAccount() {
		final String accountDeletedMsg = "ACCOUNT DELETED!";

		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email, password);
		AccountDeletedPage accountDeletedPage = homePage.deleteAccount();
		Assert.assertEquals(accountDeletedPage.getAccountDeletedMsg(), accountDeletedMsg);
		accountDeletedPage.clickContinue();
	}

	@Test
	public void signinWithExsistingEmail() {
		final String exsistingEmailMsg = "Email Address already exist!";
		String existingName = "Exsistingname";
		String existingEmail = "exsisting.email@gmail.com";
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		loginPage.signupWithIncorrectData(existingName, existingEmail);
		Assert.assertEquals(loginPage.getExsitingEmailMsg(), exsistingEmailMsg);
	}

	@Test
	public void verifySignupFieldsAreRequired() {
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(loginPage.getSingupNameFieldRequiredParamValue(), "Field Name is not required!");
		softAssert.assertTrue(loginPage.getSignupEmailFieldRequiredParamValue(), "Field Email is not required!");
		softAssert.assertAll();
	}

}
