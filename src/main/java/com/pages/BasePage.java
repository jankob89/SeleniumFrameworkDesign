package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	private static final int DEFAULT_DURATION = 5;	

	public void waitUntilElementToBeClickable(WebElement webElement, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitUntilVisibilityOf(WebElement webElement, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitUntilVisibilityOf(WebElement webElement) {
		waitUntilVisibilityOf(webElement, DEFAULT_DURATION);
	}
	
	public void waitUntilInvisibilityOf(WebElement webElement, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.invisibilityOf(webElement));
	}

	public void waitUntilInvisibilityOf(WebElement webElement) {
		waitUntilInvisibilityOf(webElement, DEFAULT_DURATION);
	}

}
