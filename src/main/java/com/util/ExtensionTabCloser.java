package com.util;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class ExtensionTabCloser {
	
	private static final int WAIT_DURATION = 1000;

	public static void closeExtensionInstallTab(WebDriver driver) {
		
		String mainTab = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		
		while (iterator.hasNext()) {
			String next = iterator.next();
			if (!mainTab.equals(next)) {
				driver.switchTo().window(next).close();
				driver.switchTo().window(mainTab);
				try {
					Thread.sleep(WAIT_DURATION);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				break;
			}
		}
	}

	private ExtensionTabCloser() {
		throw new IllegalStateException("Utility class");
	}

}
