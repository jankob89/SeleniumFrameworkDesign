package util;

import java.util.Iterator;
import java.util.Set;

import factory.DriverManager;

public class ExtensionTabCloser {
	
	private static final int WAIT_DURATION = 1000;

	public static void closeExtensionInstallTab() {
		
		String mainTab = DriverManager.get().getWindowHandle();
		Set<String> windowHandles = DriverManager.get().getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		
		while (iterator.hasNext()) {
			String next = iterator.next();
			if (!mainTab.equals(next)) {
				DriverManager.get().switchTo().window(next).close();
				DriverManager.get().switchTo().window(mainTab);
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
