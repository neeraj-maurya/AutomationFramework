package com.gi.de.full.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gi.de.base.BaseTestPage;
import com.gi.de.reporter.ExtentTestManager;
import com.gi.de.utililies.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author neerajm
 *
 */
public class ProductionOrderDetailsPage extends BaseTestPage {

	WebDriver driver;

	public ProductionOrderDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Loading icon
	@FindBy(xpath = "//app-loader")
	public WebElement iconLoading;

	// header
	@FindBy(xpath = "//h1[text()='Production Order Details']")
	public WebElement headerProductionOrderDetails;

	@Override
	public void waitForPageToLoad() {
		if (super.isPageLoaded(driver))
			try {
				WaitUtils.waitforElementToBeInvisible(driver, iconLoading);
				WaitUtils.waitforElementToBeVisible(driver, headerProductionOrderDetails);
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

}
