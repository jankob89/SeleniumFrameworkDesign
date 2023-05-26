package com.analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {
		if(maxTry > count) {
			count++;
			return true;
		}
		return false;
	}

}
