package com.gi.de.utililies;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author neerajm
 *
 */
public class GnDUtils {

	String filePath = System.getProperty("user.dir") + "/img/";

	public String takeScreenShot(String methodName, WebDriver driver) {
		String screenshotLocation = filePath + methodName + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotLocation);
		try {
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotLocation;
	}

	// Method for Web tables
	public static List<WebElement> getColumnWebElement(WebElement table, String columnName) {
		int colNum = 1;
		for (String header : getTableHeaders(table)) {
			if (header.equals(columnName)) {
				break;
			}
			colNum++;
		}
		List<WebElement> columnWebElement = table.findElements(By.xpath(".//tr/td[" + colNum + "]"));
		return columnWebElement;
	}

	public static List<String> getColumnData(WebElement table, String columnName) {
		List<WebElement> columnWebElement = getColumnWebElement(table, columnName);
		List<String> columnData = new ArrayList<>();
		for (WebElement columnElement : columnWebElement) {
			columnData.add(columnElement.getText());
		}
		return columnData;
	}

	public static int getRowCount(WebElement table) {
		return table.findElements(By.tagName("tr")).size();
	}

	public static List<WebElement> getTableHeaderWebElements(WebElement table) {
		return table.findElements(By.tagName("th"));
	}

	public static List<String> getTableHeaders(WebElement table) {
		List<String> tableHeaders = new ArrayList<>();
		for (WebElement header : getTableHeaderWebElements(table)) {
			tableHeaders.add(header.getText());
		}
		return tableHeaders;
	}

	// Utility methods for elements
	public static boolean isElementPresent(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Utility method for javascript click
	public static void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("arguments[0].click;", element);
	}
	
	//Date util
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return formatter.format(date);
	}
}