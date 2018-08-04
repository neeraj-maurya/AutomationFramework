package com.gi.de.full.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.gi.de.base.BaseTestPage;
import com.gi.de.reporter.ExtentTestManager;
import com.gi.de.utililies.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author neerajm
 *
 */
public class ProductionOrderPage extends BaseTestPage {

	WebDriver driver;

	public ProductionOrderPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://172.26.21.82:4203/production-order");
		PageFactory.initElements(driver, this);
	}

	// Loading icon
	@FindBy(xpath = "//app-loader")
	public WebElement iconLoading;

	// header
	@FindBy(xpath = "//h1[text()='Production Order']")
	public WebElement headerProductionOrder;

	// serachbox controls
	@FindBy(xpath = "//input[@class='form-control']")
	public WebElement textBoxSearch;

	@FindBy(xpath = "//div[@class='search']/span")
	public WebElement buttonSearchFilter;

	@FindBy(xpath = "//button[text()='Personalize']")
	public WebElement buttonPersonalize;

	@FindBy(xpath = "//button[text()='Redirect']")
	public WebElement buttonRedirect;

	// filter controls
	@FindBy(xpath = "//button[starts-with(@class,'filter-button')]")
	public WebElement buttonFilter;

	@FindBy(xpath = "//app-filter-panel/form")
	public WebElement panelFilter;

	@FindBy(id = "orderId")
	public WebElement textBoxPoID;

	@FindBy(id = "volumeFrom")
	public WebElement textBoxVolumeFrom;

	@FindBy(xpath = "//bs-days-calendar-view")
	public WebElement panelCalendar;
	
	@FindBy(id = "volumeTo")
	public WebElement textBoxVolumeTo;

	@FindBy(id = "fromDate")
	public WebElement textBoxFromDate;

	@FindBy(id = "toDate")
	public WebElement textBoxToDate;

	@FindBy(xpath = "//select[@ng-reflect-name='status']")
	public WebElement dropdownStatus;

	@FindBy(xpath = "//button[text()='Clear']")
	public WebElement buttonClear;

	@FindBy(xpath = "//button[text()='Apply']")
	public WebElement buttonApply;

	@FindBy(xpath = "//div[@class='d-none d-md-block']/app-removable-card/div")
	// @FindBy(xpath = "//div[@class='selected']")
	public List<WebElement> listSelectedFilters;

	// table controls
	@FindBy(xpath = "//table")
	public WebElement tablePoList;

	@FindBy(xpath = "//table/thead//input")
	public WebElement checkboxSelectAll;

	@FindBy(xpath = "//table/tbody//input")
	public List<WebElement> listCheckboxes;

	// Footer controls
	@FindBy(xpath = "//span[@class='show-drop']//select")
	public WebElement dropdownShow;

	@FindBy(xpath = "//span[@class='count-text']")
	public WebElement labelOrderCount;

	@FindBy(xpath = "//div[contains(@class,'pagination-navigate')]/button")
	public List<WebElement> listNavigationButtons;

	@FindBy(xpath = "//div[contains(@class,'pagination-navigate')]/i")
	public List<WebElement> listNavigationArrows;

	@FindBy(xpath = "//button[text()='Previous']")
	public WebElement buttonPrevious;

	@FindBy(xpath = "//button[text()='Next']")
	public WebElement buttonNext;

	@Override
	public void waitForPageToLoad() {
		if (super.isPageLoaded(driver))
			try {
				WaitUtils.waitforElementToBeInvisible(driver, iconLoading);
				WaitUtils.waitforElementToBeVisible(driver, headerProductionOrder);
				ExtentTestManager.getTest().log(LogStatus.INFO,
						this.getClass().getSimpleName() + " loaded successfully");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, this.getClass().getSimpleName() + " Failed to load");
			}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					this.getClass().getSimpleName() + " timed out after 30 seconds");
		}
	}

	/**
	 * Selects status drowpdown based on value
	 * 
	 * @param status
	 */
	public void selectStatus(String status) {
		Select dropdown = new Select(dropdownStatus);
		dropdown.selectByValue(status);
	}

	/**
	 * Selects count dropdown based on value
	 * 
	 * @param count
	 */
	public void selectShowCount(String count) {
		Select dropdown = new Select(dropdownShow);
		dropdown.selectByValue(count);
	}

	/**
	 * Returns order display count
	 * 
	 * @return
	 */
	public int getShowingOrderCountInTable() {
		String labelText = labelOrderCount.getText();
		return Integer.valueOf(labelText.substring(labelText.indexOf(" ") + 1, labelText.indexOf(" ") + 4));
	}

	/**
	 * Returns count of all records in table
	 * 
	 * @return
	 */
	public int getTotalOrderCountInTable() {
		String labelText = labelOrderCount.getText();
		return Integer.valueOf(labelText.substring(labelText.lastIndexOf(" ") + 1));
	}

	/**
	 * navigates to production order details
	 * 
	 * @param PoId
	 */
	public void openProductionOrderDetails(WebDriver driver, String PoId) {
		String URL = driver.getCurrentUrl();
		driver.get(URL.substring(0, URL.lastIndexOf("/") + 1) + "po-details/" + PoId);
	}

	/**
	 * Selects any given date(dd) in the calendar control popup
	 * 
	 * @param driver
	 * @param fromDate
	 */
	public void selectDateInCalendar(WebDriver driver, String fromDate) {
		driver.findElement(By.xpath("//bs-days-calendar-view//span[normalize-space()='" + fromDate + "']")).click();
	}

	/**
	 * Returns true/false if a given date(dd) is disabled/enabled in the calendar
	 * control
	 * 
	 * @param driver
	 * @param toDate
	 * @return
	 */
	public boolean isDateDisabledInCalendar(WebDriver driver, String toDate) {
		return driver.findElement(By.xpath("//bs-days-calendar-view//span[normalize-space()='" + toDate + "']"))
				.getAttribute("class").equals("disabled");
	}

	/**
	 * Sets any given date(dd/MM/yyyy) in the date field
	 * 
	 * @param driver
	 * @param element
	 * @param date
	 */
	public void setDate(WebDriver driver, WebElement element, String date) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + date + "';", element);
	}
}
