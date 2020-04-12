package com.divyendu.covidbatchapp.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.divyendu.covidbatchapp.constants.Constants;

/**
 * Various file datails properties and methods
 */
public class FileDetails {
	
	private String inputFilePath;
	private String globalDataFileName;
	private String countryDataFileName;
	private String rootDataScrapingFilePath;
	private String datascrapingFileName;

	public String getInputFilePath() {
		return inputFilePath;
	}

	private void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public String getGlobalDataFileName() {
		return globalDataFileName;
	}

	private void setGlobalDataFileName(String globalDataFileName) {
		this.globalDataFileName = globalDataFileName;
	}

	public String getCountryDataFileName() {
		return countryDataFileName;
	}

	private void setCountryDataFileName(String countryDataFileName) {
		this.countryDataFileName = countryDataFileName;
	}	

	public String getRootDataScrapingFilePath() {
		return rootDataScrapingFilePath;
	}

	public void setRootDataScrapingFilePath(String rootDataScrapingFilePath) {
		this.rootDataScrapingFilePath = rootDataScrapingFilePath;
	}

	public String getDatascrapingFileName() {
		return datascrapingFileName;
	}

	public void setDatascrapingFileName(String datascrapingFileName) {
		this.datascrapingFileName = datascrapingFileName;
	}

	public FileDetails() {
		String currentPath = new File("").getAbsolutePath();
		String[] fileParts = currentPath.split("\\\\");
		List<String> filePartsList = new LinkedList<String>(Arrays.asList(fileParts));
		filePartsList.remove(filePartsList.size()-1);
		setInputFilePath(String.join("\\", filePartsList) + "\\covidbatchapp\\Data");
		setRootDataScrapingFilePath(String.join("\\", filePartsList) + "\\data gathering");
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		setGlobalDataFileName(Constants.GLOBAL_FILE_NAME_ROOT + date + Constants.JSON_EXT);
		setCountryDataFileName(Constants.COUNTRY_FILE_NAME_ROOT + date + Constants.JSON_EXT);
		setDatascrapingFileName(Constants.DATA_SCRAPING_FILE_NAME + Constants.PYTHON_EXT);
	}

}
