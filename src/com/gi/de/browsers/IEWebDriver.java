package com.gi.de.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEWebDriver implements DriverFactory{

	WebDriver driver;
	
	public WebDriver getDriver() {
		String iePath = System.getProperty("user.dir") + "/drivers/IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", iePath);
		driver= new InternetExplorerDriver();
		
		return driver;
	}
}