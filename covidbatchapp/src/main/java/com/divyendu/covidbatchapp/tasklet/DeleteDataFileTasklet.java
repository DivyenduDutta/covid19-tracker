package com.divyendu.covidbatchapp.tasklet;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;

import com.divyendu.covidbatchapp.file.FileDetails;

/**
 * Custom tasklet (as opposed to step) to delete data JSONs after writing to DB
 */
public class DeleteDataFileTasklet implements Tasklet, InitializingBean{
	
	@Override
	public void afterPropertiesSet() throws Exception {}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		FileDetails fileDetails = new FileDetails();
		String globalDataFile = fileDetails.getInputFilePath() + File.separator + fileDetails.getGlobalDataFileName();
		String countryDataFile = fileDetails.getInputFilePath() + File.separator + fileDetails.getCountryDataFileName();
		try {
			File globalFile = new File(globalDataFile);
			File countryFile = new File(countryDataFile);
			globalFile.delete();
			countryFile.delete();
		}catch(Exception e) {
			throw new UnexpectedJobExecutionException("Error deleting file " + globalDataFile + " and " + countryDataFile);
		}
		
		return RepeatStatus.FINISHED; 
	}

}
