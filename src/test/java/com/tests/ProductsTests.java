package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.factory.DriverFactory;
import com.pages.HomePage;
import com.pages.ProductDetailsPage;
import com.pages.ProductsPage;
import com.testcomponents.BaseTests;

public class ProductsTests extends BaseTests {

	@Test
	public void verifyProductDetailsVisibility() {
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		ProductsPage productsPage = homePage.goToProductsPage();
		ProductDetailsPage productDetailsPage = productsPage.goToFirstProductDetails();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(productDetailsPage.getVisibilityOfProductName());
		softAssert.assertTrue(productDetailsPage.getVisibilityOfAvailability());
		softAssert.assertTrue(productDetailsPage.getVisibilityOfBrand());
		softAssert.assertTrue(productDetailsPage.getVisibilityOfCategory());
		softAssert.assertTrue(productDetailsPage.getVisibilityOfCondition());
		softAssert.assertTrue(productDetailsPage.getVisibilityOfPrice());
		softAssert.assertAll();
	}

	@Test
	public void verifyProductDetailsValue() {
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		ProductsPage productsPage = homePage.goToProductsPage();
		ProductDetailsPage productDetailsPage = productsPage.goToFirstProductDetails();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productDetailsPage.getProductName(), "Blue Top");
		softAssert.assertEquals(productDetailsPage.getAvailability(), "In Stock");
		softAssert.assertEquals(productDetailsPage.getBrand(), "Polo");
		softAssert.assertEquals(productDetailsPage.getCategory(), "Women > Tops");
		softAssert.assertEquals(productDetailsPage.getCondition(), "New");
		softAssert.assertEquals(productDetailsPage.getPrice(), "Rs. 500");
		softAssert.assertAll();
	}

	@Test
	public void verifyAllProductsRelatedToSearchAreVisible() {
		int relatedProductsCount = 7;
		String partialProductName = "Blue";	
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		ProductsPage productsPage = homePage.goToProductsPage();
		productsPage.searchProduct(partialProductName);
		Assert.assertEquals(productsPage.getSerchedProductsCount(partialProductName), relatedProductsCount);
	}


}
