package com.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.factory.DriverFactory;
import com.pages.HomePage;
import com.testcomponents.BaseTests;

public class ExternalLinksTests extends BaseTests {

	@Test
	public void verifyVideoTutorialsResponseCode() throws IOException {
		HomePage homePage = new HomePage(DriverFactory.getDriver());
		homePage.getVideoTutorialsStatusCode();
		Assert.assertTrue(homePage.getVideoTutorialsStatusCode() < 300,
				"Response code " + homePage.getVideoTutorialsStatusCode() + " is incorrect,");
	}
}
