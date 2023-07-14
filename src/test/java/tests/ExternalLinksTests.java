package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import testinit.BaseTests;

public class ExternalLinksTests extends BaseTests {

	@Test
	public void verifyVideoTutorialsResponseCode() throws IOException {
		HomePage homePage = openSite();
		homePage.getVideoTutorialsStatusCode();
		Assert.assertTrue(homePage.getVideoTutorialsStatusCode() < 300,
				"Response code " + homePage.getVideoTutorialsStatusCode() + " is incorrect,");
	}
}
