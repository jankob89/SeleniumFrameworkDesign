package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pages.CartPage;
import com.pages.CheckoutPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.ProductDetailsPage;
import com.pages.ProductsPage;
import com.testcomponents.BaseTests;
import com.util.ConfigReader;

public class CartTests extends BaseTests {

	String email1 = "cart.email1@gmail.com";
	String email2 = "cart.email2@gmail.com";
	String password = ConfigReader.getProperty("password");
	String productName = "Sleeveless Dress";
	String[] products = { "Stylish Dress", "Winter Top", "Fancy Green Top", "Full Sleeves Top Cherry - Pink" };

	@Test
	public void verifyProductIsAddedCorectlyToCart() {
		HomePage homePage = openSite();
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductByName(productName);
		CartPage cartPage = homePage.goToCartPage();
		Assert.assertEquals(cartPage.getProductsCount(), 1);
		Assert.assertEquals(cartPage.getProductName(), productName);
	}

	@Test
	public void verifyProductsAreAddedCorectlyToCart() {
		HomePage homePage = openSite();
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductsByName(products);
		CartPage cartPage = homePage.goToCartPage();
		Assert.assertEquals(cartPage.getProductsCount(), 4);
		Assert.assertEquals(cartPage.getProductsNames(), products);
	}

	@Test
	public void increaceAndVerifyQuantityOfProductInCart_byAddingTwiceTheSameProduct() {
		HomePage homePage = openSite();
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductByName(productName);
		productsPage.addProductByName(productName);
		CartPage cartPage = homePage.goToCartPage();
		Assert.assertEquals(cartPage.getProductQuantity(), 2);
	}

	@Test
	public void increaceAndVerifyQuantityOfProductInCart_whileIncreasedQuantityInProductDetails() {
		int quantity = 5;
		HomePage homePage = openSite();
		ProductsPage productsPage = homePage.goToProductsPage();
		ProductDetailsPage productDetailsPage = productsPage.goTintoProductDetailsByName(productName);
		productDetailsPage.increaseQuantityByValue(quantity);
		productDetailsPage.clickAddToCart();
		CartPage cartPage = productDetailsPage.goToCart();
		Assert.assertEquals(cartPage.getProductQuantity(), quantity);
	}

	@Test
	public void verifyrProduct_priceQuantityTotalMultiplied() {
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email1, password);
		ProductsPage productsPage = homePage.goToProductsPage();
		ProductDetailsPage productDetailsPage = productsPage.goTintoProductDetailsByName(productName);
		productDetailsPage.increaseQuantityByValue(3);
		productDetailsPage.clickAddToCart();
		CartPage cartPage = productDetailsPage.goToCart();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(cartPage.getProductPrice(), 1000);
		softAssert.assertEquals(cartPage.getProductQuantity(), 3);
		softAssert.assertEquals(cartPage.getProductTotal(), 3000);
		softAssert.assertAll();
	}

	@Test(dependsOnMethods = "verifyrProduct_priceQuantityTotalMultiplied")
	public void removeProductFromCart() {
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email1, password);
		CartPage cartPage = homePage.goToCartPage();
		cartPage.removeAllProducts();
		Assert.assertTrue(cartPage.getCartEmptyMsgVisibility(), "Products list is not empty!");
	}

	@Test
	public void verifyrProduct_TotalAmount() {
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email2, password);
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.addProductsByName(products);
		CartPage cartPage = homePage.goToCartPage();
		CheckoutPage checkoutPage = cartPage.clickProceedToCheckOut();
		Assert.assertEquals(checkoutPage.getTotalAmountValue(), 3479);
	}

	@Test(dependsOnMethods = "verifyrProduct_TotalAmount")
	public void removeAllProductFromCart() {
		HomePage homePage = openSite();
		LoginPage loginPage = homePage.goToLoginPage();
		homePage = loginPage.login(email2, password);
		CartPage cartPage = homePage.goToCartPage();
		cartPage.removeAllProducts();
		Assert.assertTrue(cartPage.getCartEmptyMsgVisibility(), "Products list is not empty!");
	}

}
