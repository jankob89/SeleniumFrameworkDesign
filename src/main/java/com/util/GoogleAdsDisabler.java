package com.util;

import org.openqa.selenium.JavascriptExecutor;
import com.factory.DriverManager;

public class GoogleAdsDisabler {

	public static void disableGoogleAds() {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.get();
		js.executeScript(
				"const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
	}

	private GoogleAdsDisabler() {
		throw new IllegalStateException("Utility class");
	}

}
