/*
 * package com.divyendu.covidbatchapp.tasklet;
 * 
 * import java.io.BufferedReader; import java.io.InputStreamReader;
 * 
 * import org.springframework.batch.core.StepContribution; import
 * org.springframework.batch.core.UnexpectedJobExecutionException; import
 * org.springframework.batch.core.scope.context.ChunkContext; import
 * org.springframework.batch.core.step.tasklet.Tasklet; import
 * org.springframework.batch.repeat.RepeatStatus; import
 * org.springframework.beans.factory.InitializingBean;
 * 
 * import com.divyendu.covidbatchapp.file.FileDetails;
 * 
 * public class DataScrapingInvokerTasklet implements Tasklet, InitializingBean{
 * 
 * private FileDetails fileDetails = FileDetails.getInstance();
 * 
 * @Override public void afterPropertiesSet() throws Exception {}
 * 
 * @Override public RepeatStatus execute(StepContribution contribution,
 * ChunkContext chunkContext) throws Exception { try { ProcessBuilder pb = new
 * ProcessBuilder("python.exe","try.py"); Process p = pb.start(); BufferedReader
 * in = new BufferedReader(new InputStreamReader(p.getInputStream()));
 * System.out.println(in.readLine()); System.out.println("Ho"); }catch(Exception
 * e) { throw new
 * UnexpectedJobExecutionException("Error running data scraping script at " +
 * fileDetails.getRootDataScrapingFilePath() +
 * fileDetails.getDatascrapingFileName()); } return RepeatStatus.FINISHED; }
 * 
 * }
 */