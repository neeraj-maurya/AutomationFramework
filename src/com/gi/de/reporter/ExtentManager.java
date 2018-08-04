package com.gi.de.reporter;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author neerajm
 *
 */
public class ExtentManager {

	static ExtentReports extent;
	final static String filePath = "./report/AutomationReport.html";

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports(filePath, true);
		}
		return extent;
	}
}
