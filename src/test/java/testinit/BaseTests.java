package testinit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import factory.DriverFactory;
import factory.DriverManager;
import pages.HomePage;
import util.ConfigReader;
import util.ExtensionTabCloser;
import util.GoogleAdsDisabler;

public class BaseTests {

	private static final String URL = "https://automationexercise.com";

	@BeforeMethod
	public void initializeDriver() {
		DriverManager.set(DriverFactory.createDriverInstance());
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.get().quit();
		DriverManager.unload();
	}

	public HomePage openSite() {
		DriverManager.get().get(URL);
		if (ConfigReader.getProperty("browser").equals("chrome")) {
			GoogleAdsDisabler.disableGoogleAds();
			ExtensionTabCloser.closeExtensionInstallTab();
			DriverManager.get().navigate().refresh();
		}
		return new HomePage(DriverManager.get());
	}
}
