package com.gi.de.full.testsuites.development;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.gi.de.base.BaseTestCase;
import com.gi.de.full.pages.LoginPage;
import com.gi.de.full.pages.RedirectSalesOrderPage;
import com.gi.de.full.pages.SalesOrderPage;
import com.gi.de.reporter.ExtentTestManager;
import com.gi.de.utililies.GnDUtils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author neerajm
 *
 */
public class SalesOrder extends BaseTestCase {

	@Test
	// Verify Search box , Filter icon ,Hamburger icon, Generate PO button ,
	// Redirect button are present
	public void salesOrderList_Screen_Contents_display() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		assertTrue(salesOrderPage.textBoxSearch.isDisplayed(), "Search box was not displayed");
		logger.log(LogStatus.PASS, "Search box is displaying");

		assertTrue(salesOrderPage.buttonFilter.isDisplayed(), "Filter button was not displayed");
		logger.log(LogStatus.PASS, "Filter button is displaying");

		assertTrue(salesOrderPage.buttonGeneratePO.isDisplayed(), "Generate PO button was not displayed");
		logger.log(LogStatus.PASS, "Generate PO button is displaying");

		assertTrue(salesOrderPage.buttonRedirect.isDisplayed(), "Redirect button was not displayed");
		logger.log(LogStatus.PASS, "Redirect button is displaying");
	}

	@Test
	// values being entered should be displayed in search box
	public void salesOrderList_Screen_SearchBox() {
		String orderID = "24";
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.textBoxSearch.sendKeys(orderID);
		logger.log(LogStatus.INFO, "Entered text in Search box");

		assertTrue(salesOrderPage.textBoxSearch.getAttribute("value").equals(orderID),
				"Entered text was not displayed in filter box");
		logger.log(LogStatus.PASS, "Entered text is displaying in filer box");
	}

	@Test
	public void salesOrderListScreen_Filtericon() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on Filter Icon");

		assertTrue(salesOrderPage.textBoxSoID.isDisplayed(), "SO ID field was not displayed");
		logger.log(LogStatus.PASS, "SO ID field is displaying");

		assertTrue(salesOrderPage.textBoxVolumeFrom.isDisplayed(), "Volumne from field was not displayed");
		logger.log(LogStatus.PASS, "Volumne from field is displaying");

		assertTrue(salesOrderPage.textBoxVolumeTo.isDisplayed(), "Volumne to field was not displayed");
		logger.log(LogStatus.PASS, "Volumne to field is displaying");

		assertTrue(salesOrderPage.textBoxFromDate.isDisplayed(), "From date field was not displayed");
		logger.log(LogStatus.PASS, "From date field is displaying");

		assertTrue(salesOrderPage.textBoxToDate.isDisplayed(), "To date field was not displayed");
		logger.log(LogStatus.PASS, "To date field is displaying");

		assertTrue(salesOrderPage.buttonClear.isDisplayed(), "Clear button was not displayed");
		logger.log(LogStatus.PASS, "Clear button  is displaying");

		assertTrue(salesOrderPage.buttonApply.isDisplayed(), "Apply button was not displayed");
		logger.log(LogStatus.PASS, "Apply button is displaying");
	}

	@Test
	public void salesOrderList_Screen_Filterpanel() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on Filter Icon to open filter panel");

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on Filter Icon again to close filter panel");

		assertFalse(GnDUtils.isElementPresent(salesOrderPage.panelFilter), "Filter panel was not hidden");
		logger.log(LogStatus.PASS, "Filter panel was hidden");
	}

	@Test
	public void salesOrderList_Screen_Action_buttons_Disabled() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		assertFalse(salesOrderPage.buttonGeneratePO.isEnabled(), "Generate PO button was enabled");
		logger.log(LogStatus.PASS, "Generate PO button is disabled");

		assertFalse(salesOrderPage.buttonRedirect.isEnabled(), "Redirect button was enabled");
		logger.log(LogStatus.PASS, "Redirect button is disabled");
	}

	@Test
	public void salesOrderList_Screen_Action_buttons_Enabled() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single SO");

		assertTrue(salesOrderPage.buttonGeneratePO.isEnabled(), "Generate PO button was not enabled");
		logger.log(LogStatus.PASS, "Generate PO button is enabled");

		assertTrue(salesOrderPage.buttonRedirect.isEnabled(), "Redirect button was not enabled");
		logger.log(LogStatus.PASS, "Redirect PO button is enabled");

		salesOrderPage.checkboxSelectAll.click();
		logger.log(LogStatus.INFO, "Selected all checkboxes");

		assertTrue(salesOrderPage.buttonGeneratePO.isEnabled(), "Generate PO button was not enabled");
		logger.log(LogStatus.PASS, "Generate PO button is enabled");

		assertTrue(salesOrderPage.buttonRedirect.isEnabled(), "Redirect button was not enabled");
		logger.log(LogStatus.PASS, "Redirect PO button is enabled");
	}

	@Test
	public void salesOrderList_Screen_Sales_OrderSTATUS() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		List<String> statusColumnData = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Status Of Order");
		Set<String> status = new HashSet<>();
		for (String s : statusColumnData) {
			status.add(s);
		}
		assertTrue(status.size() == 1, "Status column values were not Queued by default");
		logger.log(LogStatus.PASS, "Status column values are Queued by default");
	}

	@Test
	public void SalesOrderRedirectScreen_SOselection_forRedirect() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single SO");

		salesOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectSalesOrderPage redirectSalesOrderPage = new RedirectSalesOrderPage(driver);
		redirectSalesOrderPage.waitForPageToLoad();

		assertTrue(redirectSalesOrderPage.headerRedirectSalesOrders.isDisplayed(),
				"Failed to navigate to Redirect Sales Order Page");
		logger.log(LogStatus.PASS, "Navigated to Redirect Sales Order");
	}

	@Test
	public void salesOrderRedirectScreen_Redirect_contents() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single SO");

		salesOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectSalesOrderPage redirectSalesOrderPage = new RedirectSalesOrderPage(driver);
		redirectSalesOrderPage.waitForPageToLoad();

		assertTrue(redirectSalesOrderPage.headerRedirectSalesOrders.isDisplayed(),
				"Failed to navigate to Redirect Sales Order Page");
		logger.log(LogStatus.PASS, "Navigated to Redirect Sales Order");

		assertTrue(redirectSalesOrderPage.labelSelectedSOPanel.get(0).isDisplayed(),
				"Selected Sales Order was not displayed");
		logger.log(LogStatus.PASS, "Selected Sales Order is displaying");

		assertTrue(redirectSalesOrderPage.getSelectedSalesOrderCount() >= 1,
				"Selected Sales Order Count was not displayed");
		logger.log(LogStatus.PASS, "Selected Sales Order Count is displaying");

		assertTrue(redirectSalesOrderPage.radioButtonExternalSatellite.isDisplayed(),
				"External Satellite option was not displayed");
		logger.log(LogStatus.PASS, "External Satellite option is displaying");

		assertTrue(redirectSalesOrderPage.radioButtonExternalSatellite.isDisplayed(), "CDP option was not displayed");
		logger.log(LogStatus.PASS, "CDP option is displaying");

		assertTrue(redirectSalesOrderPage.buttonCancel.isDisplayed(), "Cancel button was not displayed");
		logger.log(LogStatus.PASS, "Cancel button is displaying");

		assertTrue(redirectSalesOrderPage.buttonRedirect.isDisplayed(), "Redirect button was not displayed");
		logger.log(LogStatus.PASS, "Redirect button is displaying");

		assertTrue(redirectSalesOrderPage.textBoxSatellite.isDisplayed(), "Satellite details was not displayed");
		logger.log(LogStatus.PASS, "Satellite details is displaying");
	}

	@Test
	public void salesOrderRedirectScreen_SO_Removal() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single SO");

		salesOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectSalesOrderPage redirectSalesOrderPage = new RedirectSalesOrderPage(driver);
		redirectSalesOrderPage.waitForPageToLoad();

		redirectSalesOrderPage.buttonCloseOrderPanel.get(0).click();
		logger.log(LogStatus.INFO, "Clicked on cross icon of selected sales order");

		assertTrue(redirectSalesOrderPage.getSelectedSalesOrderCount() == 0,
				"Selected sales order could not be removed");
		logger.log(LogStatus.PASS, "Selected sales order is removed");
	}

	@Test
	public void salesOrderRedirectScreen_External_Sateliite() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single SO");

		salesOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectSalesOrderPage redirectSalesOrderPage = new RedirectSalesOrderPage(driver);
		redirectSalesOrderPage.waitForPageToLoad();

		redirectSalesOrderPage.radioButtonExternalSatellite.click();
		logger.log(LogStatus.INFO, "Clicked on external satellite checkbox");

		assertFalse(redirectSalesOrderPage.textBoxSatellite.isEnabled(), "Existing satellite field was not disabled");
		logger.log(LogStatus.PASS, "Existing satellite field is disabled");

		assertTrue(redirectSalesOrderPage.listChooseSatellite.isDisplayed(),
				"Choose satellite dropdown was not displayed");
		logger.log(LogStatus.PASS, "Choose satellite dropdown is displaying");
	}

	@Test
	public void salesOrderRedirectScreen_Redirectback() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single SO");

		salesOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectSalesOrderPage redirectSalesOrderPage = new RedirectSalesOrderPage(driver);
		redirectSalesOrderPage.waitForPageToLoad();

		redirectSalesOrderPage.buttonBack.click();
		logger.log(LogStatus.INFO, "Clicked on back button");

		salesOrderPage.waitForPageToLoad();
		assertTrue(salesOrderPage.headerSalesOrder.isDisplayed(), "Could not navigate to Sales Order list page");
		logger.log(LogStatus.PASS, "Successfully navigated back to Sales Order list page");
	}

	@Test
	public void salesOrderRedirectScreen_SelctionSOdetail() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		String selectedOrder = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0);
		salesOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single SO");

		salesOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectSalesOrderPage redirectSalesOrderPage = new RedirectSalesOrderPage(driver);
		redirectSalesOrderPage.waitForPageToLoad();

		assertTrue(redirectSalesOrderPage.labelSelectedSOPanel.get(0).getText().contains(selectedOrder),
				"Selected Sales Order was not displayed");
		logger.log(LogStatus.PASS, "Selected sales order is displaying");

		assertTrue(redirectSalesOrderPage.getSelectedSalesOrderCount() == 1,
				"Selected Sales Order count was not matching");
		logger.log(LogStatus.PASS, "Selected Sales Order count is matching");
	}

	/////////////////////////////// Above test scripts have been executed

	@Test(groups = "ready")
	public void salesOrderList_Screen_TagChip() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		assertTrue(salesOrderPage.listSelectedFilters.get(0).getText().contains("Status: Queued"),
				"Status Default filter was not applied by default");
		logger.log(LogStatus.PASS, "Status Default filter is applied by default");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_SearchAnd_Filter() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		String orderID = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0);
		String volume = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Volume").get(0);
		String status = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Status Of Order").get(0);

		salesOrderPage.textBoxSearch.sendKeys(orderID);
		logger.log(LogStatus.INFO, "Entered text in Search box");

		assertTrue(GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0).equals(orderID),
				"Searched data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Search data is displaying in the grid");

		salesOrderPage.textBoxSearch.clear();
		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		assertTrue(salesOrderPage.panelFilter.isDisplayed(), "Filter panel was not displayed");
		logger.log(LogStatus.PASS, "Filter panel is displaying");

		salesOrderPage.textBoxSoID.sendKeys(orderID);
		salesOrderPage.textBoxVolumeTo.sendKeys(volume);
		salesOrderPage.selectStatus(status);
		logger.log(LogStatus.INFO, "Filter values entered");

		salesOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0).equals(orderID),
				"Filtered data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the grid");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_Filterapply() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		String orderID = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0);
		String volume = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Volume").get(0);
		String status = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Status Of Order").get(0);

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		salesOrderPage.textBoxSoID.sendKeys(orderID);
		salesOrderPage.textBoxVolumeTo.sendKeys(volume);
		salesOrderPage.selectStatus(status);
		logger.log(LogStatus.INFO, "Filter values entered");

		salesOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0).equals(orderID),
				"Filtered data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the grid");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_FilterClear() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		String orderID = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0);
		String volume = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Volume").get(0);
		String status = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Status Of Order").get(0);

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		salesOrderPage.textBoxSoID.sendKeys(orderID);
		salesOrderPage.textBoxVolumeTo.sendKeys(volume);
		salesOrderPage.selectStatus(status);
		logger.log(LogStatus.INFO, "Filter values entered");

		salesOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(GnDUtils.getColumnData(salesOrderPage.tableSoList, "Sales Order Id").get(0).equals(orderID),
				"Filtered data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the grid");

		if (!GnDUtils.isElementPresent(salesOrderPage.panelFilter)) {
			salesOrderPage.buttonFilter.click();
		}
		salesOrderPage.buttonClear.click();
		logger.log(LogStatus.INFO, "Clicked on Clear button");

		assertTrue(salesOrderPage.listSelectedFilters.size() == 0, "Grid contents failed to reset");
		logger.log(LogStatus.PASS, "Grid contents are now reset");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_Pagination() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		assertTrue(salesOrderPage.labelOrderCount.isDisplayed(), "Label order count was not displayed");
		logger.log(LogStatus.PASS, "Label Order Count is displaying");

		assertTrue(salesOrderPage.dropdownShow.isDisplayed(), "Dropdown showCount was not displayed");
		logger.log(LogStatus.PASS, "Dropdown showCount is displaying");

		assertTrue(salesOrderPage.buttonPrevious.isDisplayed(), "Button Previous was not displayed");
		logger.log(LogStatus.PASS, "Button Previous is displaying");

		assertTrue(salesOrderPage.buttonNext.isDisplayed(), "Button Next was not displayed");
		logger.log(LogStatus.PASS, "Button Next is displaying");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_FilterDate_fields_From() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		salesOrderPage.textBoxFromDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date from field");

		assertTrue(salesOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		salesOrderPage.selectDateInCalendar(driver, "24");
		assertTrue(salesOrderPage.textBoxFromDate.getAttribute("value").contains("24"),
				"Selected data was not displayed");
		logger.log(LogStatus.PASS, "Selected data is displaying");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_FilterDate_fields_To() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		salesOrderPage.textBoxToDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date to field");

		assertTrue(salesOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		salesOrderPage.selectDateInCalendar(driver, "24");
		assertTrue(salesOrderPage.textBoxToDate.getAttribute("value").contains("24"),
				"Selected data was not displayed");
		logger.log(LogStatus.PASS, "Selected data is displaying");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_FilterDate_fields_ValidationMin() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");// TODO add login code here

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		salesOrderPage.textBoxFromDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date from field");

		salesOrderPage.selectDateInCalendar(driver, "24");
		salesOrderPage.textBoxToDate.click();
		assertTrue(salesOrderPage.isDateDisabledInCalendar(driver, "23"),
				"User is able to select To Date less than From Date");
		logger.log(LogStatus.PASS, "User is not able to select To Date less than From Date");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_FilterDate_fields_ValidationMax() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		salesOrderPage.textBoxToDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date to field");

		salesOrderPage.selectDateInCalendar(driver, "23");
		salesOrderPage.textBoxFromDate.click();
		assertTrue(salesOrderPage.isDateDisabledInCalendar(driver, "24"),
				"User is able to select from Date greater than to Date");
		logger.log(LogStatus.PASS, "User is not able to select from Date greater than to Date");
	}

	@Test(groups = "ready")
	public void salesOrderList_Screen_FilterDate_fields_Both() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		salesOrderPage.textBoxFromDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date from field");

		assertTrue(salesOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		String deliveryDate = GnDUtils.getColumnData(salesOrderPage.tableSoList, "Delivery Date").get(0);

		salesOrderPage.setDate(driver, salesOrderPage.textBoxFromDate, deliveryDate);
		salesOrderPage.textBoxToDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date to field");

		assertTrue(salesOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		salesOrderPage.setDate(driver, salesOrderPage.textBoxToDate, deliveryDate);
		salesOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(GnDUtils.getColumnData(salesOrderPage.tableSoList, "Delivery Date").get(0).equals(deliveryDate),
				"Filter data was not showing");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the selected date range");
	}

	// Set - 3
	@Test(groups = { "Set3" })
	public void salesOrderList_Screen_Filter_Status_Field() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.operationsManagerLogin();
		logger.log(LogStatus.INFO, "Logged in with Operations Manager");

		SalesOrderPage salesOrderPage = new SalesOrderPage(driver);
		salesOrderPage.waitForPageToLoad();

		salesOrderPage.listSelectedFilters.get(0).click();

		Set<String> statuses = new HashSet<>(GnDUtils.getColumnData(salesOrderPage.tableSoList, "Status Of Order"));
		for (String status : statuses) {
			salesOrderPage.buttonFilter.click();
			salesOrderPage.selectStatus(status.toLowerCase());
			salesOrderPage.buttonApply.click();
			assertTrue(GnDUtils.getColumnData(salesOrderPage.tableSoList, "Status Of Order").get(0).equals(status),
					"Filtered data is not matching with applied filter");
		}
		logger.log(LogStatus.PASS, "Filtered data is displaying as per selected filter");
	}
}