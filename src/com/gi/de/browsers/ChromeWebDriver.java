package com.gi.de.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriver implements DriverFactory {

	WebDriver driver;

	public WebDriver getDriver() {

		String chromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("--test-type");
		options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(options);

		return driver;
	}
}