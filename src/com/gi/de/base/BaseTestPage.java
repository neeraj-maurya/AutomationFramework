package com.gi.de.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gi.de.constants.AppConstants;

public abstract class BaseTestPage extends BaseTestCase{

	public boolean isPageLoaded(WebDriver driver) {
		boolean isPageLoaded = new WebDriverWait(driver, AppConstants.PAGE_LOAD_TIMEOUT)
				.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
						.equals("complete"));
		return isPageLoaded;
	}

	public abstract void waitForPageToLoad();
}