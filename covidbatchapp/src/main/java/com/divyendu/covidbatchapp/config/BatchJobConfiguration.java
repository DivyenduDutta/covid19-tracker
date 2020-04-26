package com.divyendu.covidbatchapp.config;

import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import com.divyendu.covidbatchapp.constants.Constants;
import com.divyendu.covidbatchapp.file.FileDetails;
import com.divyendu.covidbatchapp.listeners.DataScrapeListener;
import com.divyendu.covidbatchapp.mappers.GlobalDataJsonLineMapper;
import com.divyendu.covidbatchapp.models.CountryDataEntity;
import com.divyendu.covidbatchapp.models.CountryDataRecord;
import com.divyendu.covidbatchapp.models.GlobalDataEntity;
import com.divyendu.covidbatchapp.models.GlobalDataRecord;
import com.divyendu.covidbatchapp.tasklet.DataScrapingInvokerTasklet;
import com.divyendu.covidbatchapp.tasklet.DeleteDataFileTasklet;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configuration class for creating jobs and steps
 *
 */
@Configuration
public class BatchJobConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
    @Qualifier(value="entityManagerFactory")
    private EntityManagerFactory batchEntityManagerFactory;
	
	private FileDetails fileDetails;
	
	@Bean
	JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
		postProcessor.setJobRegistry(jobRegistry);
		return postProcessor;
	}
	
	@Bean
	public Job job(@Qualifier("globalDataStep") Step globalDataStep, 
						@Qualifier("countryDataStep") Step countryDataStep,
						@Qualifier("deleteDataFileStep") Step deleteDataFileStep,
						@Qualifier("dataScrapeStep") Step dataScrapeStep) throws Exception{
		return this.jobBuilderFactory
				.get(Constants.JOB_NAME)
				.start(dataScrapeStep)
				.next(globalDataStep)
				.next(countryDataStep)
				.next(deleteDataFileStep)
				.build();
	}
	
    @Bean
    @Qualifier("globalDataStep")
    public Step globalDataStep(ItemReader<GlobalDataRecord> itemReader,
                     Function<GlobalDataRecord, GlobalDataEntity> processor,
                     JpaItemWriter<GlobalDataEntity> writer) throws Exception {
        return this.stepBuilderFactory
            .get(Constants.GLOBAL_STEP_NAME)
            .<GlobalDataRecord, GlobalDataEntity>chunk(2)
            .reader(itemReader)
            .processor(processor)
            .writer(writer)
            .build();
    }
    

    
    @Bean
    @Qualifier("countryDataStep")
    public Step countryDataStep(JsonItemReader<CountryDataRecord> itemReader,
    				Function<CountryDataRecord, CountryDataEntity> processor,
                    JpaItemWriter<CountryDataEntity> writer) throws Exception {
        return this.stepBuilderFactory
            .get(Constants.COUNTRY_STEP_NAME)
            .<CountryDataRecord, CountryDataEntity>chunk(2)
            .reader(itemReader)
            .processor(processor)
            .writer(writer)
            .build();
    }
    
    @Bean
    @Qualifier("deleteDataFileStep")
    public Step deleteDataFileStep() {
    	return this.stepBuilderFactory.get(Constants.DELETE_FILE_STEP_NAME)
    			.tasklet(new DeleteDataFileTasklet())
    			.build();
    }
    
    @Bean
    @Qualifier("dataScrapeStep")
    public Step dataScrapeStep() {
    	return this.stepBuilderFactory.get(Constants.DATA_SCRAPE_STEP_NAME)
    			.tasklet(new DataScrapingInvokerTasklet())
    			.listener(new DataScrapeListener())
    			.build();
    }
    
    @Bean
    @StepScope
    FlatFileItemReader<GlobalDataRecord> globalDataReader(){
    	fileDetails =  new FileDetails();
        return new FlatFileItemReaderBuilder<GlobalDataRecord>()
                .name(Constants.GLOBAL_ITEM_READER_NAME)
                .resource(
                    new PathResource(
                        Paths.get(fileDetails.getInputFilePath() +
                            File.separator + fileDetails.getGlobalDataFileName())))
                .lineMapper(new GlobalDataJsonLineMapper())
                .build();
    }
    
    @Bean
    @StepScope
    public JsonItemReader<CountryDataRecord> countryDataReader() {
    	fileDetails =  new FileDetails();
       ObjectMapper objectMapper = new ObjectMapper();
       JacksonJsonObjectReader<CountryDataRecord> jsonObjectReader = 
                new JacksonJsonObjectReader<>(CountryDataRecord.class);
       jsonObjectReader.setMapper(objectMapper);
       return new JsonItemReaderBuilder<CountryDataRecord>()
                     .jsonObjectReader(jsonObjectReader)
                     .resource(
                    	new PathResource(
                             Paths.get(fileDetails.getInputFilePath() +
                                     File.separator + fileDetails.getCountryDataFileName())))
                     .name(Constants.COUNTRY_ITEM_READER_NAME)
                     .build();
    }
    
	@Bean
    @StepScope
    public Function<GlobalDataRecord, GlobalDataEntity> globalDataProcessor(){
        return (globaldataRecord) ->  {
            try {
				return new GlobalDataEntity(
					new SimpleDateFormat("yyyy-MM-dd").parse(globaldataRecord.getDataReceiveDate()),
					globaldataRecord.getTotalCoronaVirusCases(),
					globaldataRecord.getTotalDeaths(),
					globaldataRecord.getTotalRecovered());
			} catch (ParseException e) {
				e.printStackTrace();
				return new GlobalDataEntity();
			}
        };
    }
	
	@Bean
    @StepScope
    public Function<CountryDataRecord, CountryDataEntity> countryDataProcessor(){
        return (countrydataRecord) ->  {
            try {
				return new CountryDataEntity(
					countrydataRecord.getCountryName(),
					new SimpleDateFormat("yyyy-MM-dd").parse(countrydataRecord.getDataReceiveDate()),
					countrydataRecord.getTotalCases(),
					countrydataRecord.getNewCases(),
					countrydataRecord.getTotalDeaths(),
					countrydataRecord.getNewDeaths(),
					countrydataRecord.getTotalRecovered(),
					countrydataRecord.getActiveCases(),
					countrydataRecord.getCritical());
			} catch (ParseException e) {
				e.printStackTrace();
				return new CountryDataEntity();
			}
        };
    }
	
    @Bean
    @StepScope
    public JpaItemWriter<GlobalDataEntity> globalDataWriter() {
        JpaItemWriter<GlobalDataEntity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(batchEntityManagerFactory);
        return writer;
    }
    
    @Bean
    @StepScope
    public JpaItemWriter<CountryDataEntity> countryDataWriter() {
        JpaItemWriter<CountryDataEntity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(batchEntityManagerFactory);
        return writer;
    }
    
    /*@Bean
    public JobParametersValidator validator() {
        return new JobParametersValidator() {
            @Override
            public void validate(JobParameters parameters) throws JobParametersInvalidException {
            	FileDetails fileDetails = new FileDetails();
            	String globalDataFileName = fileDetails.getGlobalDataFileName();
                String countryDataFileName = fileDetails.getCountryDataFileName();
                if (globalDataFileName.isEmpty() || countryDataFileName.isEmpty()) {
                    throw new JobParametersInvalidException(
                		"The covid-data.fileName parameter is required.");
                }
                try {
                    Path globalFile = Paths.get(fileDetails.getInputFilePath()+ 
                		File.separator + globalDataFileName);
                    Path countryFile = Paths.get(fileDetails.getInputFilePath()+ 
                    		File.separator + countryDataFileName);
                    if (Files.notExists(globalFile) || !Files.isReadable(globalFile)) {
                        throw new Exception("Global Data File does not exist or was not readable");
                    }
                    if (Files.notExists(countryFile) || !Files.isReadable(countryFile)) {
                        throw new Exception("Country Data File does not exist or was not readable");
                    }
                } catch (Exception e) {
                    throw new JobParametersInvalidException(
                        "  The input path + covid-data.fileName parameter needs to " + 
                    		"be a valid file location." + " ----- " + globalDataFileName + " ----- " + countryDataFileName);
                }
            }
        };
    }*/
}
