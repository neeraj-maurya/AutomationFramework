package com.gi.de.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxWebDriver implements DriverFactory{
	
	WebDriver driver;

	public WebDriver getDriver() {
		String firefoxPath = System.getProperty("user.dir") + "/drivers/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver(capabilities);
		return driver;
	}
}