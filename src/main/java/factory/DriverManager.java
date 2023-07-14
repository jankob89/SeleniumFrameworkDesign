package factory;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private DriverManager() {
		throw new IllegalStateException("DriverManager class");
	}

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	public static WebDriver get() {
		return DRIVER.get();
	}

	public static void set(WebDriver driver) {
		DRIVER.set(driver);
	}

	public static void unload() {
		DRIVER.remove();
	}
}
