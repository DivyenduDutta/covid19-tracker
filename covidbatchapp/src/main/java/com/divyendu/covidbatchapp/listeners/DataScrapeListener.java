package com.divyendu.covidbatchapp.listeners;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.divyendu.covidbatchapp.file.FileDetails;

public class DataScrapeListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution){
		System.out.println("After step of Data Scrape Listener");
		FileDetails fileDetails = new FileDetails();
    	String globalDataFileName = fileDetails.getGlobalDataFileName();
        String countryDataFileName = fileDetails.getCountryDataFileName();
        if (globalDataFileName.isEmpty() || countryDataFileName.isEmpty()) {
            return ExitStatus.FAILED;
        }
        try {
            Path globalFile = Paths.get(fileDetails.getInputFilePath()+ 
        		File.separator + globalDataFileName);
            Path countryFile = Paths.get(fileDetails.getInputFilePath()+ 
            		File.separator + countryDataFileName);
            if (Files.notExists(globalFile) || !Files.isReadable(globalFile)) {
            	System.out.println("file not found");
                throw new Exception("Global Data File does not exist or was not readable");
            }
            if (Files.notExists(countryFile) || !Files.isReadable(countryFile)) {
                throw new Exception("Country Data File does not exist or was not readable");
            }
        } catch (Exception e) {
        	System.out.println("exception here");
        	return ExitStatus.FAILED;
        }
        return ExitStatus.COMPLETED;
	}

}
