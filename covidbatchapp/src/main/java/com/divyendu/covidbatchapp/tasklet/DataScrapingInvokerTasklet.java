package com.divyendu.covidbatchapp.tasklet;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;

import com.divyendu.covidbatchapp.file.FileDetails;

public class DataScrapingInvokerTasklet implements Tasklet, InitializingBean{

	private FileDetails fileDetails;
	
	@Override
	public void afterPropertiesSet() throws Exception {}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			fileDetails = new FileDetails();
			
			 ProcessBuilder pb = new ProcessBuilder("python.exe",
					 fileDetails.getRootDataScrapingFilePath() + File.separator +
					 fileDetails.getDatascrapingFileName());
			 
			Process p = pb.start();
			p.waitFor();
			/*BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			System.out.println(in.readLine());
			System.out.println("Ho");*/
		}catch(Exception e) {
			 throw new UnexpectedJobExecutionException("Error running data scraping script at " + 
					 fileDetails.getRootDataScrapingFilePath() + fileDetails.getDatascrapingFileName());
		}
		return RepeatStatus.FINISHED;
	}

}
