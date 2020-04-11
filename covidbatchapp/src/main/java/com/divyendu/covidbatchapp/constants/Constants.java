package com.divyendu.covidbatchapp.constants;

/**
 * Common constants used across code base
 */
public class Constants {

	public static final String JOB_NAME = "covid-loader";
	
	public static final String JOB_PARAM_FILE_NAME = "covid-data.fileName";
	
	public static final String COUNTRY_JOB_PARAM_FILE_NAME = "country-covid-data.fileName";
	
	public static final String GLOBAL_STEP_NAME = "global-covid-step";
	
	public static final String COUNTRY_STEP_NAME = "country-covid-step";
	
	public static final String DELETE_FILE_STEP_NAME = "delete-file-covid-step";
	
	public static final String GLOBAL_ITEM_READER_NAME = "global-covid-item-reader";
	
	public static final String COUNTRY_ITEM_READER_NAME = "country-covid-item-reader";
	
	public static final String GLOBAL_FILE_NAME_ROOT = "Total_Corona_Virus_Data_";
	
	public static final String COUNTRY_FILE_NAME_ROOT = "Country_Corona_Virus_Data_";
	
	public static final String FILE_EXT = ".json";
	
	private Constants() {}
}
