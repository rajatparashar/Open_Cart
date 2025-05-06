package com.qa.opencart.constants;

public abstract class AppConstants {
	// Time Outs
	public static final int DEFAULT_TIMEOUT = 5;
	public static final int SHORT_TIMEOUT = 10;
	public static final int MEDIUM_TIMEOUT = 15;
	public static final int MAX_TIMEOUT = 20;

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";

	// Config Files Path:
	public static final String CONFIG_QA_PROP_FILE_PATH = "./src/test/resources/testconfig/QA_config.properties";
	public static final String CONFIG_DEV_PROP_FILE_PATH = "./src/test/resources/testconfig/DEV_config.properties";
	public static final String CONFIG_STAGE_PROP_FILE_PATH = "./src/test/resources/testconfig/STAGE_config.properties";
	public static final String CONFIG_UAT_PROP_FILE_PATH = "./src/test/resources/testconfig/UAT_config.properties";

}
