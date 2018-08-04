package com.gi.de.full.testsuites.development;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.gi.de.base.BaseTestCase;
import com.gi.de.full.pages.LoginPage;
import com.gi.de.full.pages.ProductionOrderPage;
import com.gi.de.full.pages.SalesOrderPage;
import com.gi.de.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author neerajm
 *
 */
public class Login extends BaseTestCase {

	@Test
	public void LoginScreen_ApplicationUrl() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();

		assertTrue(loginPage.textBoxUserName.isDisplayed(), "UserName field was not displayed");
		logger.log(LogStatus.PASS, "UserName field is displaying");

		assertTrue(loginPage.textBoxPassword.isDisplayed(), "Password field was not displayed");
		logger.log(LogStatus.PASS, "Password field is displaying");

		assertTrue(loginPage.buttonSignIn.isDisplayed(), "Sign In button was not displayed");
		logger.log(LogStatus.PASS, "Sign In button is displaying");
	}

	@Test(enabled = false)
	public void loginScreen_ValidUser_OperationManager() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		assertTrue(salesOrderPage.headerSalesOrder.isDisplayed(), "Failed to navigate to Sales Order list page");
		logger.log(LogStatus.PASS, "Successfully navigated to Sales Order list page");
	}

	@Test(enabled = false)
	public void loginScreen_ValidUser_ProductionStaff() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		assertTrue(productionOrderPage.headerProductionOrder.isDisplayed(),
				"Failed to navigate to Production Order list page");
		logger.log(LogStatus.PASS, "Successfully navigated to Production Order list page");
	}
}
