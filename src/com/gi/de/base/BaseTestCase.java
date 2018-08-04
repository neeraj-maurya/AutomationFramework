package com.gi.de.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.gi.de.browsers.BrowserTypes;
import com.gi.de.constants.AppConstants;
import com.gi.de.reporter.ExtentManager;
import com.gi.de.reporter.ExtentTestManager;
import com.gi.de.utililies.GnDUtils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class BaseTestCase {

	protected WebDriver driver;
	//private ExtentTest reporter;

	@BeforeTest
	public void test(ITestContext context) {
		//reporter = ExtentTestManager.startTest(context.getName(), "Multi Browser Tests");
	}

	/**
	 * This method sets some system property based on the values passed from
	 * testng.xml then initializes the browser
	 * 
	 * @param browser
	 *            - name of the browser
	 * @param deviceName
	 *            - deviceID
	 * @param screenHeight
	 *            - vertical resolution
	 * @param screenWidth
	 *            - horizontal resolution
	 */
	@Parameters({ "browser", "deviceName", "screenHeight", "screenWidth" })
	@BeforeMethod
	protected void setup(@Optional("chrome") String browser, @Optional("NA") String deviceName,
			@Optional("NA") String screenHeight, @Optional("NA") String screenWidth) {

		System.setProperty("browser.type", browser);
		if (deviceName != "NA") {
			System.setProperty("device.name", deviceName);
		}
		if (screenHeight != "NA" && screenWidth != "NA") {
			System.setProperty("screen.height", screenHeight);
			System.setProperty("screen.width", screenWidth);
		}

		try {
			driver = BrowserTypes.getDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(AppConstants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(AppConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(AppConstants.SCRIPT_TIMEOUT, TimeUnit.SECONDS);
			driver.get(AppConstants.APPLICATION_URL);
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starting the extent report
	 * 
	 * @param method
	 */
	@BeforeMethod
	protected void startTest(Method method, ITestContext context) {
		//reporter.appendChild(ExtentTestManager.startTest(method.getName()));
		ExtentTestManager.startTest(method.getName(), context.getName());
	}

	/**
	 * This method prepares the extent report using ITestResult interface of TestNG
	 * 
	 * @param result
	 */
	@AfterMethod
	protected void prepareReport(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Testcase Failed");
			String methodName = result.getName().toString().trim();
			String imagePath = ExtentTestManager.getTest()
					.addScreenCapture(new GnDUtils().takeScreenShot(methodName, driver));
			ExtentTestManager.getTest().log(LogStatus.FAIL, methodName, imagePath);
			ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Testcase skipped " + result.getThrowable());
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Testcase Passed");
		}

		ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
		ExtentManager.getReporter().flush();
	}

	/**
	 * quitting the driver here after each test annotated method
	 */
	@AfterMethod
	protected void quitDriver() {
		driver.quit();
	}

	@AfterTest
	protected void endTest() {
		//ExtentManager.getReporter().endTest(reporter);
		//ExtentManager.getReporter().flush();
	}
}