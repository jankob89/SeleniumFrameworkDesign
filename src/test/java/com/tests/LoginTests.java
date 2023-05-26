package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.factory.DriverFactory;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.testcomponents.BaseTests;
import com.util.ConfigReader;

public class LoginTests extends BaseTests {
	
	private String name = ConfigReader.getProperty("name");
	private String email = ConfigReader.getProperty("email");
	private String password = ConfigReader.getProperty("password");
	
	@Test
	public void loginWithCorrectData() {
		final String loggedInMsg = "Logged in as " + name;	
		
		HomePage homePage = new HomePage(DriverFactory.getDriver());	
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email, password);
		Assert.assertEquals(homePage.getLogedinText(), loggedInMsg);
	}
	
	@Test
	public void loginWithIncorrectData() {
	    String incorrectEmail = "incorrect.email@gmail.com";
		String incorrectPassword = "incorrect.email@gmail.com";
		final String incorrectEmailOrPassMsg  = "Your email or password is incorrect!";
		
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		LoginPage loginPage = homePage.goToLoginPage();
		loginPage.loginWithIncorrectData(incorrectEmail, incorrectPassword);
		Assert.assertEquals(loginPage.getIncorrectEmaiPassMsg(), incorrectEmailOrPassMsg);
	}
	
	@Test
	public void logoutLoggedUser() {
		HomePage homePage = new HomePage(DriverFactory.getDriver());		
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email, password);
		loginPage = homePage.logout();
		Assert.assertTrue(homePage.getLoginSigninVisibility(), "User did not logout correctly!");
	}
	
	@Test
	public void verifyLoginFieldsAreRequired() {
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		LoginPage loginPage = homePage.goToLoginPage();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(loginPage.getLoginEmailFieldRequiredParamValue(), "Field Name is not required!");
		softAssert.assertTrue(loginPage.getLoginPasswordFieldRequiredParamValue(), "Field Email is not required!");
		softAssert.assertAll();
	}
}
