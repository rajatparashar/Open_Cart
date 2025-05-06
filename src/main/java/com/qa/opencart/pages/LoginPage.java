package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	By email = By.id("input-email");
	By password = By.id("input-password");
	By submitBtn = By.xpath("//input[@value='Login']");
	By forgotPwdLink = By.linkText("Forgotten Password");

	public HomePage doLogin(String uname, String pwd) {
		System.out.println("Logining in with username " + uname + " and password: " + pwd);
		eleUtil.doSendKeys(email, uname);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(submitBtn);
		return new HomePage(driver);
	}

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		System.out.println("title is == " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIMEOUT);
		System.out.println("url is == " + url);
		return url;
	}
}