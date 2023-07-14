package com.browser.options;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {
	
	private BrowserOptions() {
		throw new IllegalStateException("BrowserOptions class");
	}

	public static ChromeOptions getChromeOptions() {
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

	public static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("private");
		return options;
	}
}
