package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0;
	private static int maxTry = 3;

	@Override
	public boolean retry(ITestResult result) {

		if (!result.isSuccess()) { // check if test is not successful
			if (count < maxTry) { // check if max retry is reached
				count++; // increase the count by 1
				result.setStatus(ITestResult.FAILURE); // mark test case failed
				return true; // inform TestNG to retry/re-run the test.
			} else {
				result.setStatus(ITestResult.FAILURE); // if max try limit is reached, then mark test case as Failed
			}
		} else {
			result.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}
}