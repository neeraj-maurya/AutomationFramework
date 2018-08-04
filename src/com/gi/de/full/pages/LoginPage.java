package com.gi.de.full.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gi.de.base.BaseTestPage;
import com.gi.de.constants.AppConstants;
import com.gi.de.reporter.ExtentTestManager;
import com.gi.de.utililies.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author neerajm
 *
 */
public class LoginPage extends BaseTestPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Loading icon
	@FindBy(xpath = "//app-loader")
	public WebElement iconLoading;

	// header
	@FindBy(xpath = "//h3[text()='Sign In']")
	public WebElement headerSignIn;

	// login form
	@FindBy(xpath = "//input[@name='username']")
	public WebElement textBoxUserName;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement textBoxPassword;

	@FindBy(xpath = "//button[text()='Sign In']")
	public WebElement buttonSignIn;

	@Override
	public void waitForPageToLoad() {
		if (super.isPageLoaded(driver))
			try {
				WaitUtils.waitforElementToBeInvisible(driver, iconLoading);
				WaitUtils.waitforElementToBeVisible(driver, headerSignIn);
				ExtentTestManager.getTest().log(LogStatus.INFO,
						this.getClass().getSimpleName() + " loaded successfully");
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, this.getClass().getSimpleName() + " Failed to load");
			}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					this.getClass().getSimpleName() + " timed out after 30 seconds");
		}
	}

	public void operationsManagerLogin() {
		textBoxUserName.sendKeys(AppConstants.APPLICATION_USER_NAME_OPM);
		textBoxPassword.sendKeys(AppConstants.APPLICATION_PASSWORD_GLOBAL);
		buttonSignIn.click();
	}
	
	public void productionStaffLogin() {
		textBoxUserName.sendKeys(AppConstants.APPLICATION_USER_NAME_PS);
		textBoxPassword.sendKeys(AppConstants.APPLICATION_PASSWORD_GLOBAL);
		buttonSignIn.click();
	}
}
