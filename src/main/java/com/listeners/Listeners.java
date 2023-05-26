package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.util.ExtentReportsCreator;
import com.util.ScreenshotCreator;

public class Listeners implements ITestListener{

	ExtentTest extentTest;
	ExtentReports extentReports = ExtentReportsCreator.getExtentReport();
	ThreadLocal<ExtentTest> tlExtentTest = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		ITestListener.super.onTestStart(result);
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
		tlExtentTest.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		tlExtentTest.get().fail(result.getThrowable());
		String screenshotPath = ScreenshotCreator.getScreenshot(result.getMethod().getMethodName());
		tlExtentTest.get().addScreenCaptureFromPath(screenshotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
		tlExtentTest.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
		extentReports.flush();
	}

	
}
