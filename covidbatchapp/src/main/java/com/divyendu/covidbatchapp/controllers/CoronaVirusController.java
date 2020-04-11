package com.divyendu.covidbatchapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.divyendu.covidbatchapp.constants.Constants;

/**
 * Rest controller to invoke batch app via rest service
 */
@RestController
public class CoronaVirusController {
	
	private final JobLauncher jobLauncher;
	private final Job job;
	
	public CoronaVirusController(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}
	
	@GetMapping("/automate")
	public ResponseEntity<String> runJob(){
		Map<String, JobParameter> parameterMap = new HashMap<>();
		parameterMap.put("job run time", new JobParameter(System.currentTimeMillis()));
		try {
			jobLauncher.run(job, new JobParameters(parameterMap));
		}catch(Exception e) {
			return new ResponseEntity<String>("FAILURE" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}
