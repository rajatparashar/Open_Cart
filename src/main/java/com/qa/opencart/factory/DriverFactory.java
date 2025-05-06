package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browser = prop.getProperty("browser");

		optionsManager = new OptionsManager(prop);

		boolean remoteExecution = Boolean.parseBoolean(prop.getProperty("remote"));

		switch (browser.trim().toLowerCase()) {
		case "chrome":
			if (remoteExecution) {
				// run tests cases on remote/grid
				initRemoteDriver("chrome");
			} else {
				// local execution
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if (remoteExecution) {
				initRemoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "edge":
			if (remoteExecution) {
				initRemoteDriver("edge");
			} else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		default:
			throw new RuntimeException("Please enter a valid browser.. " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void initRemoteDriver(String browserName) {
		System.out.println("Running tests on grid: " + browserName);
		try {
			switch (browserName.trim().toLowerCase()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;

			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;
			default:
				System.out.println("The brower is not supported.. " + browserName);
				break;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// supply env name using maven command line
	// mvn clean install -Denv="qa"
	public Properties initProp() {
		String envName = System.getProperty("env");
		System.out.println("Running tests on " + envName + " environment.");
		FileInputStream fis = null;
		prop = new Properties();
		try {
			if (envName == null) {
				fis = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
			} else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					fis = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
					break;
				case "dev":
					fis = new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
					break;
				case "stage":
					fis = new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
					break;
				case "uat":
					fis = new FileInputStream(AppConstants.CONFIG_UAT_PROP_FILE_PATH);
					break;
				default:
					throw new FrameworkException("Invalid env entered....");
				}
			}
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir
	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir
	}
}