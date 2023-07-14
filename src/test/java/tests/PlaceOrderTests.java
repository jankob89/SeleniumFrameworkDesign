package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AccountCreatedPage;
import pages.AccountDeletedPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderPlacedPage;
import pages.PaymentPage;
import pages.ProductsPage;
import pages.SignupPage;
import testinit.BaseTests;
import util.ConfigReader;
import util.UniqueNumberCreator;

public class PlaceOrderTests extends BaseTests {

	//Register data
	String name;
	String email;
	String password = "Password12#";
	String title = "Mr.";
	String firstName = "John";
	String lastName = "Doe";
	String companyName = "Some IT Company";
	String address1 = "Robson Street, Vancouver";
	String address2 = "British Columbia";
	String countryName = "Canada";
	String stateName = "Nova Scotia";
	String cityName = "Toronto";
	String zipcode = "33-411";
	String phoneNumber = "+44664031359";
	
	//Login email and password
	String emailForLogin = ConfigReader.getProperty("email");
	String passwordForLogin = ConfigReader.getProperty("password");
	
	//Payment data
	String cardNumber = "1234 5678 9012 3456";
	String nameOnCard = "Jan Kowalski";
	String CVC = "009";
	String expiryMonth = "11";
	String expiryYear = "2028";
	
	//Others
	String product = "Stylish Dress";
	final String orderPlacedMsg = "ORDER PLACED!";
	
	@Test
	public void placeOrderAndRegisterWhileCheckout() {

		name = "Testname_" + UniqueNumberCreator.getNumber();
		email = "correct.email" + UniqueNumberCreator.getNumber() + "@gmail.com";
		HomePage homePage = openSite();
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductByName(product);
		CartPage cartPage = homePage.goToCartPage();
		cartPage.clickProceedToCheckOut();	
		LoginPage loginPage = cartPage.clickRegisterLogin();
		SignupPage signupPage = loginPage.signup(name, email);
		signupPage.enterPassword(password);
		signupPage.selectTitle(title);
		signupPage.setDateOfBirth("11", "June", "1989");
		signupPage.checkNewsletter();
		signupPage.checkSpecialOffers();
		signupPage.enterFirstName(firstName);
		signupPage.enterLastName(lastName);
		signupPage.enterCompany(companyName);
		signupPage.enterAddress1(address1);
		signupPage.enterAddress2(address2);
		signupPage.selectCountry(countryName);
		signupPage.enterState(stateName);
		signupPage.enterCity(cityName);
		signupPage.enterZipcode(zipcode);
		signupPage.enterMobileNumber(phoneNumber);

		AccountCreatedPage accountCreatedPage = signupPage.clickCreateAccount();
		homePage = accountCreatedPage.clickContinue();
		cartPage = homePage.goToCartPage();
		CheckoutPage checkoutPage = cartPage.clickProceedToCheckOut();		
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		paymentPage.enterCardNumber(cardNumber);
		paymentPage.enterNameOnCard(nameOnCard);
		paymentPage.enterCVC(CVC);
		paymentPage.enterExpiryMonth(expiryMonth);
		paymentPage.enterExpiryYear(expiryYear);
		OrderPlacedPage orderPlacedPage = paymentPage.clickPayAndConfirmOrder();
		Assert.assertEquals(orderPlacedPage.getOrderPalcedMsg(), orderPlacedMsg);	
		orderPlacedPage.clickContinue();
	}
	
	@Test
	public void placeOrderAndLoginWhileCheckout() {

		HomePage homePage = openSite();
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductByName(product);
		CartPage cartPage = homePage.goToCartPage();
		cartPage.clickProceedToCheckOut();	
		LoginPage loginPage = cartPage.clickRegisterLogin();
		homePage = loginPage.login(emailForLogin, passwordForLogin);
		cartPage = homePage.goToCartPage();
		CheckoutPage checkoutPage = cartPage.clickProceedToCheckOut();		
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		paymentPage.enterCardNumber(cardNumber);
		paymentPage.enterNameOnCard(nameOnCard);
		paymentPage.enterCVC(CVC);
		paymentPage.enterExpiryMonth(expiryMonth);
		paymentPage.enterExpiryYear(expiryYear);
		OrderPlacedPage orderPlacedPage = paymentPage.clickPayAndConfirmOrder();
		Assert.assertEquals(orderPlacedPage.getOrderPalcedMsg(), orderPlacedMsg);	
		orderPlacedPage.clickContinue();
	}
	
	@Test
	public void placeOrderBeeingLogin() {	
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(emailForLogin, passwordForLogin);
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductByName(product);
		CartPage cartPage = homePage.goToCartPage();
		CheckoutPage checkoutPage = cartPage.clickProceedToCheckOut();	
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		paymentPage.enterCardNumber(cardNumber);
		paymentPage.enterNameOnCard(nameOnCard);
		paymentPage.enterCVC(CVC);
		paymentPage.enterExpiryMonth(expiryMonth);
		paymentPage.enterExpiryYear(expiryYear);
		OrderPlacedPage orderPlacedPage = paymentPage.clickPayAndConfirmOrder();
		Assert.assertEquals(orderPlacedPage.getOrderPalcedMsg(), orderPlacedMsg);	
		orderPlacedPage.clickContinue();	
	}
	
	@Test(dependsOnMethods = {"placeOrderAndRegisterWhileCheckout"})
	public void removeCreatedAccount() {
		final String accountDeletedMsg = "ACCOUNT DELETED!";

		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email, password);
		AccountDeletedPage accountCreatedDeletedPage = homePage.deleteAccount();
		Assert.assertEquals(accountCreatedDeletedPage.getAccountDeletedMsg(), accountDeletedMsg);
		accountCreatedDeletedPage.clickContinue();
	}
	
	public void verifyOrderDetailsOnCheckoutPage() {
		String[] products = { "Stylish Dress", "Winter Top", "Fancy Green Top", "Full Sleeves Top Cherry - Pink" };
		String fullName = title + " " + firstName + " " + lastName;
		String fullResidence = cityName + " " + stateName + " " + zipcode;

		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(emailForLogin, passwordForLogin);
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductsByName(products);
		CartPage cartPage = homePage.goToCartPage();
		CheckoutPage checkoutPage = cartPage.clickProceedToCheckOut();		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(checkoutPage.getDeliveryAddressName(), fullName);
		softAssert.assertEquals(checkoutPage.getDeliveryAddressCompanyName(), companyName);
		softAssert.assertEquals(checkoutPage.getDeliveryAddressAddress1(), address1);
		softAssert.assertEquals(checkoutPage.getDeliveryAddressAddress2(), address2);
		softAssert.assertEquals(checkoutPage.getDeliveryAddressCountryName(), countryName);
		softAssert.assertEquals(checkoutPage.getDeliveryAddressResidence(), fullResidence);
		softAssert.assertEquals(checkoutPage.getDeliveryAddressPhoneNumber(), phoneNumber);
		softAssert.assertEquals(checkoutPage.getBillingAddressName(), fullName);
		softAssert.assertEquals(checkoutPage.getBillingAddressCompanyName(), companyName);
		softAssert.assertEquals(checkoutPage.getBillingAddressAddress1(), address1);
		softAssert.assertEquals(checkoutPage.getBillingAddressAddress2(), address2);
		softAssert.assertEquals(checkoutPage.getBillingAddressCountryName(), countryName);
		softAssert.assertEquals(checkoutPage.getBillingAddressResidence(), fullResidence);
		softAssert.assertEquals(checkoutPage.getBillingAddressPhoneNumber(), phoneNumber);			
		softAssert.assertEquals(checkoutPage.getProductsNames(), products);
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		paymentPage.enterCardNumber(cardNumber);
		paymentPage.enterNameOnCard(nameOnCard);
		paymentPage.enterCVC(CVC);
		paymentPage.enterExpiryMonth(expiryMonth);
		paymentPage.enterExpiryYear(expiryYear);
		OrderPlacedPage orderPlacedPage = paymentPage.clickPayAndConfirmOrder();
		Assert.assertEquals(orderPlacedPage.getOrderPalcedMsg(), orderPlacedMsg);
		orderPlacedPage.clickContinue();
	}
}
