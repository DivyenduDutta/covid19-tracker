package com.divyendu.covidbatchapp.constants;

/**
 * Common constants used across code base
 */
public class Constants {
	
	public static final String JOB_NAME = "covid-loader";
	
	//public static final String JOB_NAME_FIRST = "covid-loader-first";

	//public static final String JOB_NAME_SECOND = "covid-loader-second";
	
	public static final String JOB_PARAM_FILE_NAME = "covid-data.fileName";
	
	public static final String COUNTRY_JOB_PARAM_FILE_NAME = "country-covid-data.fileName";
	
	public static final String GLOBAL_STEP_NAME = "global-covid-step";
	
	public static final String COUNTRY_STEP_NAME = "country-covid-step";
	
	public static final String DELETE_FILE_STEP_NAME = "delete-file-covid-step";
	
	public static final String DATA_SCRAPE_STEP_NAME = "data-scrape-covid-step";
	
	public static final String VALIDATE_FILE_STEP_NAME = "validate-file-covid-step";
	
	public static final String GLOBAL_ITEM_READER_NAME = "global-covid-item-reader";
	
	public static final String COUNTRY_ITEM_READER_NAME = "country-covid-item-reader";
	
	public static final String GLOBAL_FILE_NAME_ROOT = "Total_Corona_Virus_Data_";
	
	public static final String COUNTRY_FILE_NAME_ROOT = "Country_Corona_Virus_Data_";
	
	public static final String DATA_SCRAPING_FILE_NAME = "data_scraping";
	
	public static final String JSON_EXT = ".json";
	
	public static final String PYTHON_EXT = ".py";
	
	private Constants() {}
}
