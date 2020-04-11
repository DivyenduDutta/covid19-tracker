package com.divyendu.covidbatchapp.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Task scheduler from spring core to run spring batch app
 * at specified times
 * Change @Scheduled(cron = "0 0 23 * * ?") for different trigger condition
 */
@Component
public class ScheduledTask {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@Scheduled(cron = "0 0 23 * * ?")
	public void run() {
		Map<String, JobParameter> parameterMap = new HashMap<>();
		parameterMap.put("job run time", new JobParameter(System.currentTimeMillis()));
		try {
			jobLauncher.run(job, new JobParameters(parameterMap));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
