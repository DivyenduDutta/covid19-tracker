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

	public FileDetails() {
		String currentPath = new File("").getAbsolutePath();
		String[] fileParts = currentPath.split("\\\\");
		List<String> filePartsList = new LinkedList<String>(Arrays.asList(fileParts));
		filePartsList.remove(filePartsList.size()-1);
		setInputFilePath(String.join("\\", filePartsList) + "\\data gathering\\Data");
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		setGlobalDataFileName(Constants.GLOBAL_FILE_NAME_ROOT + date + Constants.FILE_EXT);
		setCountryDataFileName(Constants.COUNTRY_FILE_NAME_ROOT + date + Constants.FILE_EXT);
	}

}
