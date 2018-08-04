package com.gi.de.utililies;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gi.de.constants.AppConstants;

/**
 * @author neerajm
 * Utility class to provide a wrapper around several commonly
 * used WebDriverWait methods.
 *
 */
public class WaitUtils {

	// Wait methods
	static WebDriverWait wait;
	static Wait<WebDriver> fluentWait;

	public static boolean waitforElementToBeInvisible(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, AppConstants.PAGE_LOAD_TIMEOUT);
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static WebElement waitforElementToBeVisible(WebDriver driver, WebElement element) {

		wait = new WebDriverWait(driver, AppConstants.PAGE_LOAD_TIMEOUT);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	@SuppressWarnings({ "deprecation" })
	public static void wait(WebDriver driver) {
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(AppConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	}
}
