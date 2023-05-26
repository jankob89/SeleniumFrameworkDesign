package com.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsCreator {
	
	public static ExtentReports getExtentReport() {
		String path = System.getProperty("user.dir") + "\\reports\\" + getFileName();
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Report name");
		reporter.config().setDocumentTitle("My first framework - extent report");
		reporter.config().setTheme(Theme.DARK);
		
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester", "Anusz");
		extentReports.createTest(path);
		return extentReports;
	}
	
	private static String getFileName() {
		return "report_" + UniqueNumberCreator.getNumber() + ".html";
	}
	
	private ExtentReportsCreator() {
		throw new IllegalStateException("ScreenshotCreator class");
	}
}
