package com.qa.opencart.base;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {

	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	DriverFactory df;

	@Parameters({ "browser", "browserversion", "testname" })
	@BeforeTest
	public void setup(String browserName, String browserVersion, String testName) {
		df = new DriverFactory();
		prop = df.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}

		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);

	}

//	@AfterMethod
//	public void attachScreenshot(ITestResult result) {
//		if(!result.isSuccess()) {//only for failuer test cases
//			//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
//			//ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
//			TestAllureListener.embed(DriverFactory.getScreenshotBase64(), "image/png");
//		}
//	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}