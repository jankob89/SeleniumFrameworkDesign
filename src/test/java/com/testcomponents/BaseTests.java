package com.testcomponents;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.factory.DriverFactory;
import com.util.ConfigReader;
import com.util.ExtensionTabCloser;
import com.util.GoogleAdsDisabler;

public class BaseTests {

	private static final String URL = "https://automationexercise.com";

	@BeforeMethod
	public void lanuchApplication() {
		DriverFactory.initializeDriver();
		DriverFactory.getDriver().get(URL);
		if (ConfigReader.getProperty("browser").equals("chrome")) {
			GoogleAdsDisabler.disableGoogleAds();
			ExtensionTabCloser.closeExtensionInstallTab(DriverFactory.getDriver());
			DriverFactory.getDriver().navigate().refresh();
		}
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getDriver().quit();
		DriverFactory.unload();
	}
}
