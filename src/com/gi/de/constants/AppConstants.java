package com.gi.de.constants;

import com.gi.de.utililies.AppConfig;

public final class AppConstants {

	public final static String userDirectory = System.getProperty("user.dir");
	public final static String APPLICATION_URL = AppConfig.propertyReader().getProperty("application.url");
	public final static String SO_LIST_API_URL = AppConfig.propertyReader().getProperty("so.list.url");
	public final static String APPLICATION_USER_NAME_OPM = AppConfig.propertyReader().getProperty("application.username.opm");
	public final static String APPLICATION_USER_NAME_PS = AppConfig.propertyReader().getProperty("application.username.ps");
	public final static String APPLICATION_PASSWORD_GLOBAL = AppConfig.propertyReader().getProperty("application.password.global");
	
	//Timeouts
	public final static int IMPLICIT_WAIT_TIME = Integer.valueOf(AppConfig.propertyReader().getProperty("implicit.wait"));
	public final static int PAGE_LOAD_TIMEOUT = Integer.valueOf(AppConfig.propertyReader().getProperty("pageLoad.timeout"));
	public final static int SCRIPT_TIMEOUT = Integer.valueOf(AppConfig.propertyReader().getProperty("script.timeout"));
}
