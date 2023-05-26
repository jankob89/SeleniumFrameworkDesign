package com.util;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.factory.DriverFactory;

public class ScreenshotCreator {

	public static String  getScreenshot(String tcName) {
		TakesScreenshot takeSrcreenshot = (TakesScreenshot) DriverFactory.getDriver();
		File sourceFile = takeSrcreenshot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + getFileName(tcName));
		try {
			FileHandler.copy(sourceFile, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getPath();
	}

	private static String getFileName(String tcName) {
		return tcName + "_" + UniqueNumberCreator.getNumber() + ".png";
	}

	private ScreenshotCreator() {
		throw new IllegalStateException("ScreenshotCreator class");
	}
}
