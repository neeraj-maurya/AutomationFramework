package com.gi.de.full.testsuites.development;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.gi.de.base.BaseTestCase;
import com.gi.de.full.pages.LoginPage;
import com.gi.de.full.pages.ProductionOrderDetailsPage;
import com.gi.de.full.pages.ProductionOrderPage;
import com.gi.de.full.pages.RedirectProductionOrderPage;
import com.gi.de.reporter.ExtentTestManager;
import com.gi.de.utililies.GnDUtils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author neerajm
 *
 */
public class ProductionOrder extends BaseTestCase {

	@Test
	// Verify Search box , Filter icon ,Hamburger icon, Generate PO button ,
	// Redirect button are present
	public void productionOrderList_Screen_Contents_display() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		assertTrue(productionOrderPage.textBoxSearch.isDisplayed(), "Search box was not displayed");
		logger.log(LogStatus.PASS, "Search box is displaying");

		assertTrue(productionOrderPage.buttonFilter.isDisplayed(), "Filter button was not displayed");
		logger.log(LogStatus.PASS, "Filter button is displaying");

		assertTrue(productionOrderPage.buttonPersonalize.isDisplayed(), "Personalize button was not displayed");
		logger.log(LogStatus.PASS, "Personalize button is displaying");

		assertTrue(productionOrderPage.buttonRedirect.isDisplayed(), "Redirect button was not displayed");
		logger.log(LogStatus.PASS, "Redirect button is displaying");
	}

	@Test
	// values being entered should be displayed in search box
	public void productionOrderList_Screen_SearchBox() {
		String orderID = "24";
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.textBoxSearch.sendKeys(orderID);
		logger.log(LogStatus.INFO, "Entered text in filter box");

		assertTrue(productionOrderPage.textBoxSearch.getAttribute("value").equals(orderID),
				"Entered text was not displayed in filter box");
		logger.log(LogStatus.PASS, "Entered text is displaying in filer box");
	}

	@Test
	public void productionOrderListScreen_Filtericon() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on Filter Icon");

		assertTrue(productionOrderPage.textBoxPoID.isDisplayed(), "SO ID field was not displayed");
		logger.log(LogStatus.PASS, "SO ID field is displaying");

		assertTrue(productionOrderPage.textBoxVolumeFrom.isDisplayed(), "Volumne from field was not displayed");
		logger.log(LogStatus.PASS, "Volumne from field is displaying");

		assertTrue(productionOrderPage.textBoxVolumeTo.isDisplayed(), "Volumne to field was not displayed");
		logger.log(LogStatus.PASS, "Volumne to field is displaying");

		assertTrue(productionOrderPage.textBoxFromDate.isDisplayed(), "From date field was not displayed");
		logger.log(LogStatus.PASS, "From date field is displaying");

		assertTrue(productionOrderPage.textBoxToDate.isDisplayed(), "To date field was not displayed");
		logger.log(LogStatus.PASS, "To date field is displaying");

		assertTrue(productionOrderPage.buttonClear.isDisplayed(), "Clear button was not displayed");
		logger.log(LogStatus.PASS, "Clear button  is displaying");

		assertTrue(productionOrderPage.buttonApply.isDisplayed(), "Apply button was not displayed");
		logger.log(LogStatus.PASS, "Apply button is displaying");
	}

	@Test
	public void productionOrderList_Screen_Filterpanel() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on Filter Icon to open filter panel");

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on Filter Icon again to close filter panel");

		assertFalse(GnDUtils.isElementPresent(productionOrderPage.panelFilter), "Filter panel was not hidden");
		logger.log(LogStatus.PASS, "Filter panel was hidden");
	}

	@Test
	public void productionOrderList_Screen_Action_buttons_Disabled() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		assertFalse(productionOrderPage.buttonPersonalize.isEnabled(), "Personalize button was enabled");
		logger.log(LogStatus.PASS, "Generate PO button is disabled");

		assertFalse(productionOrderPage.buttonRedirect.isEnabled(), "Redirect button was enabled");
		logger.log(LogStatus.PASS, "Redirect button is disabled");
	}

	@Test
	public void productionOrderList_Screen_Action_buttons_Enabled() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		assertTrue(productionOrderPage.buttonPersonalize.isEnabled(), "Personalize button was not enabled");
		logger.log(LogStatus.PASS, "Generate PO button is enabled");

		assertTrue(productionOrderPage.buttonRedirect.isEnabled(), "Redirect button was not enabled");
		logger.log(LogStatus.PASS, "Redirect PO button is enabled");

		productionOrderPage.checkboxSelectAll.click();
		logger.log(LogStatus.INFO, "Selected all checkboxes");

		assertTrue(productionOrderPage.buttonPersonalize.isEnabled(), "Personalize button was not enabled");
		logger.log(LogStatus.PASS, "Generate PO button is enabled");

		assertTrue(productionOrderPage.buttonRedirect.isEnabled(), "Redirect button was not enabled");
		logger.log(LogStatus.PASS, "Redirect PO button is enabled");
	}

	@Test
	public void productionOrderList_Screen_Production_OrderSTATUS() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		List<String> statusColumnData = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Status Of Order");
		Set<String> status = new HashSet<>();
		for (String s : statusColumnData) {
			status.add(s);
		}
		assertTrue(status.size() == 1, "Status column values were not Queued by default");
		logger.log(LogStatus.PASS, "Status column values are Queued by default");
	}

	@Test
	public void ProductionOrderRedirectScreen_SOselection_forRedirect() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		productionOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectProductionOrderPage redirectProductionOrderPage = new RedirectProductionOrderPage(driver);
		redirectProductionOrderPage.waitForPageToLoad();

		assertTrue(redirectProductionOrderPage.headerRedirectProductionOrders.isDisplayed(),
				"Failed to navigate to Redirect Production Order Page");
		logger.log(LogStatus.PASS, "Navigated to Redirect Production Order");
	}

	@Test
	public void productionOrderRedirectScreen_Redirect_contents() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		productionOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectProductionOrderPage redirectProductionOrderPage = new RedirectProductionOrderPage(driver);
		redirectProductionOrderPage.waitForPageToLoad();

		assertTrue(redirectProductionOrderPage.headerRedirectProductionOrders.isDisplayed(),
				"Failed to navigate to Redirect Production Order Page");
		logger.log(LogStatus.PASS, "Navigated to Redirect Production Order");

		assertTrue(redirectProductionOrderPage.labelSelectedPOPanel.get(0).isDisplayed(),
				"Selected Production Order was not displayed");
		logger.log(LogStatus.PASS, "Selected Production Order is displaying");

		assertTrue(redirectProductionOrderPage.getSelectedProductionOrderCount() >= 1,
				"Selected Production Order Count was not displayed");
		logger.log(LogStatus.PASS, "Selected Production Order Count is displaying");

		assertTrue(redirectProductionOrderPage.radioButtonExternalSatellite.isDisplayed(),
				"External Satellite option was not displayed");
		logger.log(LogStatus.PASS, "External Satellite option is displaying");

		assertTrue(redirectProductionOrderPage.radioButtonExternalSatellite.isDisplayed(),
				"CDP option was not displayed");
		logger.log(LogStatus.PASS, "CDP option is displaying");

		assertTrue(redirectProductionOrderPage.buttonCancel.isDisplayed(), "Cancel button was not displayed");
		logger.log(LogStatus.PASS, "Cancel button is displaying");

		assertTrue(redirectProductionOrderPage.buttonRedirect.isDisplayed(), "Redirect button was not displayed");
		logger.log(LogStatus.PASS, "Redirect button is displaying");

		assertTrue(redirectProductionOrderPage.textBoxSatellite.isDisplayed(), "Satellite details was not displayed");
		logger.log(LogStatus.PASS, "Satellite details is displaying");
	}

	@Test
	public void productionOrderRedirectScreen_SO_Removal() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		productionOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectProductionOrderPage redirectProductionOrderPage = new RedirectProductionOrderPage(driver);
		redirectProductionOrderPage.waitForPageToLoad();

		redirectProductionOrderPage.buttonCloseOrderPanel.get(0).click();
		logger.log(LogStatus.INFO, "Clicked on cross icon of selected production order");

		assertTrue(redirectProductionOrderPage.getSelectedProductionOrderCount() == 0,
				"Selected production order could not be removed");
		logger.log(LogStatus.PASS, "Selected production order is removed");
	}

	@Test
	public void productionOrderRedirectScreen_External_Sateliite() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		productionOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectProductionOrderPage redirectProductionOrderPage = new RedirectProductionOrderPage(driver);
		redirectProductionOrderPage.waitForPageToLoad();

		redirectProductionOrderPage.radioButtonExternalSatellite.click();
		logger.log(LogStatus.INFO, "Clicked on external satellite checkbox");

		assertFalse(redirectProductionOrderPage.textBoxSatellite.isEnabled(),
				"Existing satellite field was not disabled");
		logger.log(LogStatus.PASS, "Existing satellite field is disabled");

		assertTrue(redirectProductionOrderPage.listChooseSatellite.isDisplayed(),
				"Choose satellite dropdown was not displayed");
		logger.log(LogStatus.PASS, "Choose satellite dropdown is displaying");
	}

	@Test
	public void productionOrderRedirectScreen_Redirectback() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		productionOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectProductionOrderPage redirectProductionOrderPage = new RedirectProductionOrderPage(driver);
		redirectProductionOrderPage.waitForPageToLoad();

		redirectProductionOrderPage.buttonBack.click();
		logger.log(LogStatus.INFO, "Clicked on back button");

		productionOrderPage.waitForPageToLoad();
		assertTrue(productionOrderPage.headerProductionOrder.isDisplayed(),
				"Could not navigate to Production Order list page");
		logger.log(LogStatus.PASS, "Successfully navigated back to Production Order list page");
	}

	@Test
	public void productionOrderRedirectScreen_SelctionPOdetail() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		String selectedOrder = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0);
		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		productionOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectProductionOrderPage redirectProductionOrderPage = new RedirectProductionOrderPage(driver);
		redirectProductionOrderPage.waitForPageToLoad();

		assertTrue(redirectProductionOrderPage.labelSelectedPOPanel.get(0).getText().contains(selectedOrder),
				"Selected Production Order was not displayed");
		logger.log(LogStatus.PASS, "Selected Production Order is displaying");

		assertTrue(redirectProductionOrderPage.getSelectedProductionOrderCount() == 1,
				"Selected Production Order count was not matching");
		logger.log(LogStatus.PASS, "Selected Production Order count is matching");
	}

	////////////////////////////// Above test scripts have been executed

	@Test(groups = "ready")
	public void production_OrderList_Screen_TagChip() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		assertTrue(productionOrderPage.listSelectedFilters.get(0).getText().contains("Status: Queued"),
				"Status Default filter was not applied by default");
		logger.log(LogStatus.PASS, "Status Default filter is applied by default");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_SearchAnd_Filter() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		String orderID = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0);
		String volume = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Volume").get(0);
		String status = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Status Of Order").get(0);

		productionOrderPage.textBoxSearch.sendKeys(orderID);
		logger.log(LogStatus.INFO, "Entered text in Search box");

		assertTrue(
				GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0).equals(orderID),
				"Searched data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Search data is displaying in the grid");

		productionOrderPage.textBoxSearch.clear();
		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		assertTrue(productionOrderPage.panelFilter.isDisplayed(), "Filter panel was not displayed");
		logger.log(LogStatus.PASS, "Filter panel is displaying");

		productionOrderPage.textBoxPoID.sendKeys(orderID);
		productionOrderPage.textBoxVolumeTo.sendKeys(volume);
		productionOrderPage.selectStatus(status);
		logger.log(LogStatus.INFO, "Filter values entered");

		productionOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(
				GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0).equals(orderID),
				"Filtered data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the grid");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_Filterapply() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		String orderID = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0);
		String volume = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Volume").get(0);
		String status = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Status Of Order").get(0);

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		productionOrderPage.textBoxPoID.sendKeys(orderID);
		productionOrderPage.textBoxVolumeTo.sendKeys(volume);
		productionOrderPage.selectStatus(status);
		logger.log(LogStatus.INFO, "Filter values entered");

		productionOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(
				GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0).equals(orderID),
				"Filtered data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the grid");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_FilterClear() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		String orderID = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0);
		String volume = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Volume").get(0);
		String status = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Status Of Order").get(0);

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		productionOrderPage.textBoxPoID.sendKeys(orderID);
		productionOrderPage.textBoxVolumeTo.sendKeys(volume);
		productionOrderPage.selectStatus(status);
		logger.log(LogStatus.INFO, "Filter values entered");

		productionOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(
				GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0).equals(orderID),
				"Filtered data was not displayed in the grid");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the grid");

		if (!GnDUtils.isElementPresent(productionOrderPage.panelFilter)) {
			productionOrderPage.buttonFilter.click();
		}
		productionOrderPage.buttonClear.click();
		logger.log(LogStatus.INFO, "Clicked on Clear button");

		assertTrue(productionOrderPage.listSelectedFilters.size() == 0, "Grid contents failed to reset");
		logger.log(LogStatus.PASS, "Grid contents are now reset");
	}

	@Test(groups = "ready")
	public void productionOrderRedirectScreen_Different_Client_Manager() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listCheckboxes.get(0).click();
		logger.log(LogStatus.INFO, "Selected single PO");

		productionOrderPage.buttonRedirect.click();
		logger.log(LogStatus.INFO, "Clicked on Redirect button");

		RedirectProductionOrderPage redirectProductionOrderPage = new RedirectProductionOrderPage(driver);
		redirectProductionOrderPage.waitForPageToLoad();

		assertTrue(GnDUtils.isElementPresent(redirectProductionOrderPage.radioButtonDifferentClientManager),
				"Client Manager radio button was not displayed");
		logger.log(LogStatus.PASS, "Client Manager radio button is displaying");

		redirectProductionOrderPage.radioButtonDifferentClientManager.click();
		logger.log(LogStatus.INFO, "Clicked on Different Client Manager radio button");

		assertTrue(redirectProductionOrderPage.radioButtonPrinters.size() > 0,
				"Printers associated to client managers were not displayed");
		logger.log(LogStatus.PASS, "Printers associated to client managers are displaying");
	}

	@Test
	public void productionOrderList_Screen_Sales_Order_ID_link() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.openProductionOrderDetails(driver,
				GnDUtils.getColumnData(productionOrderPage.tablePoList, "Production Order Id").get(0));

		ProductionOrderDetailsPage productionOrderDetailsPage = new ProductionOrderDetailsPage(driver);
		productionOrderDetailsPage.waitForPageToLoad();

		logger.log(LogStatus.PASS, "Successfully navigated to Production Order Details page");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_Pagination() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		assertTrue(productionOrderPage.labelOrderCount.isDisplayed(), "Label order count was not displayed");
		logger.log(LogStatus.PASS, "Label Order Count is displaying");

		assertTrue(productionOrderPage.dropdownShow.isDisplayed(), "Dropdown showCount was not displayed");
		logger.log(LogStatus.PASS, "Dropdown showCount is displaying");

		assertTrue(productionOrderPage.buttonPrevious.isDisplayed(), "Button Previous was not displayed");
		logger.log(LogStatus.PASS, "Button Previous is displaying");

		assertTrue(productionOrderPage.buttonNext.isDisplayed(), "Button Next was not displayed");
		logger.log(LogStatus.PASS, "Button Next is displaying");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_FilterDate_fields_From() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		productionOrderPage.textBoxFromDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date from field");

		assertTrue(productionOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		productionOrderPage.selectDateInCalendar(driver, "24");
		assertTrue(productionOrderPage.textBoxFromDate.getAttribute("value").contains("24"),
				"Selected data was not displayed");
		logger.log(LogStatus.PASS, "Selected data is displaying");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_FilterDate_fields_To() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		productionOrderPage.textBoxToDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date to field");

		assertTrue(productionOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		productionOrderPage.selectDateInCalendar(driver, "24");
		assertTrue(productionOrderPage.textBoxToDate.getAttribute("value").contains("24"),
				"Selected data was not displayed");
		logger.log(LogStatus.PASS, "Selected data is displaying");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_FilterDate_fields_ValidationMin() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		productionOrderPage.textBoxFromDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date from field");

		productionOrderPage.selectDateInCalendar(driver, "24");
		productionOrderPage.textBoxToDate.click();
		assertTrue(productionOrderPage.isDateDisabledInCalendar(driver, "23"),
				"User is able to select To Date less than From Date");
		logger.log(LogStatus.PASS, "User is not able to select To Date less than From Date");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_FilterDate_fields_ValidationMax() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		productionOrderPage.textBoxToDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date to field");

		productionOrderPage.selectDateInCalendar(driver, "23");
		productionOrderPage.textBoxFromDate.click();
		assertTrue(productionOrderPage.isDateDisabledInCalendar(driver, "24"),
				"User is able to select from Date greater than to Date");
		logger.log(LogStatus.PASS, "User is not able to select from Date greater than to Date");
	}

	@Test(groups = "ready")
	public void productionOrderList_Screen_FilterDate_fields_Both() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.buttonFilter.click();
		logger.log(LogStatus.INFO, "Clicked on filter button");

		productionOrderPage.textBoxFromDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date from field");

		assertTrue(productionOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		String deliveryDate = GnDUtils.getColumnData(productionOrderPage.tablePoList, "Delivery Date").get(0);

		productionOrderPage.setDate(driver, productionOrderPage.textBoxFromDate, deliveryDate);
		productionOrderPage.textBoxToDate.click();
		logger.log(LogStatus.INFO, "Clicked on delivery date to field");

		assertTrue(productionOrderPage.panelCalendar.isDisplayed(), "Calendar was not displayed");
		logger.log(LogStatus.PASS, "Calendar is displaying");

		productionOrderPage.setDate(driver, productionOrderPage.textBoxToDate, deliveryDate);
		productionOrderPage.buttonApply.click();
		logger.log(LogStatus.INFO, "Clicked on Apply button");

		assertTrue(GnDUtils.getColumnData(productionOrderPage.tablePoList, "Delivery Date").get(0).equals(deliveryDate),
				"Filter data was not showing");
		logger.log(LogStatus.PASS, "Filtered data is displaying in the selected date range");
	}

	// Set3
	@Test(groups = "Set3")
	public void productionOrderList_Screen_Filter_Status_Field() {
		ExtentTest logger = ExtentTestManager.getTest();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.waitForPageToLoad();
		loginPage.productionStaffLogin();
		logger.log(LogStatus.INFO, "Logged in with Production Staff");

		ProductionOrderPage productionOrderPage = new ProductionOrderPage(driver);
		productionOrderPage.waitForPageToLoad();

		productionOrderPage.listSelectedFilters.get(0).click();

		Set<String> statuses = new HashSet<>(
				GnDUtils.getColumnData(productionOrderPage.tablePoList, "Status Of Order"));
		for (String status : statuses) {
			productionOrderPage.buttonFilter.click();
			productionOrderPage.selectStatus(status.toLowerCase());
			productionOrderPage.buttonApply.click();
			assertTrue(GnDUtils.getColumnData(productionOrderPage.tablePoList, "Status Of Order").get(0).equals(status),
					"Filtered data is not matching with applied filter");
		}
		logger.log(LogStatus.PASS, "Filtered data is displaying as per selected filter");
	}
}