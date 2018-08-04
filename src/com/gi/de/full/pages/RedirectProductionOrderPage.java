package com.gi.de.full.pages;

import java.util.List;

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
public class RedirectProductionOrderPage extends BaseTestPage {

	WebDriver driver;

	public RedirectProductionOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Loading icon
	@FindBy(xpath = "//app-loader")
	public WebElement iconLoading;

	// header
	@FindBy(xpath = "//*[text()[normalize-space() = 'Redirect Production Orders']]")
	public WebElement headerRedirectProductionOrders;

	@FindBy(xpath = "//i[@class='fa fa-arrow-left']")
	public WebElement buttonBack;

	@FindBy(xpath = "//label[text()[contains(normalize-space(),'Selected Production Orders')]]")
	public WebElement labelSelectedProductionOrders;

	@FindBy(xpath = "//div[@class='selectedSO']//div")
	public List<WebElement> labelSelectedPOPanel;

	@FindBy(xpath = "//div[@class='selectedSO']//app-removable-card")
	public List<WebElement> buttonCloseOrderPanel;

	@FindBy(xpath = "//input[@value='satellite']")
	public WebElement radioButtonExternalSatellite;

	@FindBy(xpath = "//input[@value='client_machine']")
	public WebElement radioButtonDifferentClientManager;

	@FindBy(xpath = "//input[@value='cdp']")
	public WebElement radioButtonCDP;

	@FindBy(xpath = "//label[text()='From']/following-sibling::input")
	public WebElement textBoxSatellite;

	@FindBy(xpath = "//label[text()='From']/following-sibling::input")
	public WebElement listChooseSatellite;

	@FindBy(xpath = "//label[text()='From']/following-sibling::input")
	public Select dropdownChooseSatellite;

	@FindBy(xpath = "//app-cards[@ng-reflect-selection-type='radio']//input")
	public List<WebElement> radioButtonPrinters;

	@FindBy(xpath = "//div[@class='text-right']/button[text()='Cancel']")
	public WebElement buttonCancel;

	@FindBy(xpath = "//div[@class='text-right']/button[text()='Redirect']")
	public WebElement buttonRedirect;

	@Override
	public void waitForPageToLoad() {
		if (super.isPageLoaded(driver))
			try {
				WaitUtils.waitforElementToBeInvisible(driver, iconLoading);
				WaitUtils.waitforElementToBeVisible(driver, headerRedirectProductionOrders);
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

	public int getSelectedProductionOrderCount() {
		String labelText = labelSelectedProductionOrders.getText();
		return Integer.valueOf(labelText.substring(labelText.indexOf("(") + 1, labelText.indexOf(")")));
	}
}
