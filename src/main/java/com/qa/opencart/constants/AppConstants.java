package com.qa.opencart.constants;

public abstract class AppConstants {
	// Time Outs
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int SHORT_TIME_OUT = 10;
	public static final int MEDIUM_TIME_OUT = 15;
	public static final int MAX_TIME_OUT = 20;

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String HOME_PAGE_TITLE = "My Account";
	public static final String HOME_PAGE_URL_FRACTION = "route=account/account";

	// Config Files Path:
	public static final String CONFIG_QA_PROP_FILE_PATH = "./src/test/resources/testconfig/QA_config.properties";
	public static final String CONFIG_DEV_PROP_FILE_PATH = "./src/test/resources/testconfig/DEV_config.properties";
	public static final String CONFIG_STAGE_PROP_FILE_PATH = "./src/test/resources/testconfig/STAGE_config.properties";
	public static final String CONFIG_UAT_PROP_FILE_PATH = "./src/test/resources/testconfig/UAT_config.properties";

	public static final String PRODUCT_SHEET_NAME = "product";
	public static final String USER_REGISTRATION_SHEET_NAME = "userregistration";
}
