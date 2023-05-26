package com.factory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.util.ConfigReader;

public class DriverFactory {

	private DriverFactory() {
		throw new IllegalStateException("DriverFactory class");
	}

	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static WebDriver initializeDriver() {
		String browser = ConfigReader.getProperty("browser");
		switch (browser) {
		case "chrome" -> tlDriver.set(new ChromeDriver(getChromeOptions()));
		case "firefox" -> tlDriver.set(new FirefoxDriver(getFirefoxOptions()));
		case "edge" -> tlDriver.set(new EdgeDriver());
		default -> throw new IllegalArgumentException("Unexpected value: " + browser);
		}
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void unload() {
		tlDriver.remove();
	}

	private static ChromeOptions getChromeOptions() {
		File file = new File(System.getProperty("user.dir") + "\\drivers\\chrome_extensions\\extension_5_5_0_0.crx");
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("autofill.profile_enabled", false);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("disable-notifications");
		options.addExtensions(file);
		return options;
	}

	private static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("private");
		return options;
	}

}
