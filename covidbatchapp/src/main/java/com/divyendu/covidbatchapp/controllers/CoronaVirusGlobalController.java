package com.divyendu.covidbatchapp.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.divyendu.covidbatchapp.dao.GlobalDataDAO;
import com.divyendu.covidbatchapp.helper.DateHelper;
import com.divyendu.covidbatchapp.mappers.GlobalDataMapper;
import com.divyendu.covidbatchapp.models.GlobalDataEntity;
import com.divyendu.covidbatchapp.models.GlobalDataRecord;

/**
 * Controller for Global data services
 */
@RestController
@RequestMapping("global")
public class CoronaVirusGlobalController {

	@Autowired
	private GlobalDataDAO globalDAO;

	@Autowired
	private GlobalDataMapper mapper;

	/*
	 *  Rest service to get single global data record via date
	 */
	@GetMapping("/single")
	public GlobalDataRecord getSingleGlobalDataRecordByRetrieveDate(
			@RequestParam(value = "retrieve_date", required = true) String retriveDate) {
		GlobalDataRecord globalDataRecord = new GlobalDataRecord();
		try {
			GlobalDataEntity globalDataEntity = globalDAO
					.getGlobalDataRecordByRetreiveDate(DateHelper.convertStringToDate(retriveDate));
			mapper.mapEntityToRecord(globalDataEntity, globalDataRecord);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return globalDataRecord;
	}

	/*
	 *  Rest service to get list of global data records between date range
	 */
	@GetMapping("/multiple")
	public List<GlobalDataRecord> getGlobalDataRecordByRange(
			@RequestParam(value = "retrieve_start_date", required = true) String retriveStartDate,
			@RequestParam(value = "retrieve_end_date", required = true) String retriveEndDate) {
		List<GlobalDataRecord> globalDataRecords = new ArrayList<>();
		try {
			List<GlobalDataEntity> globalDataEntities = globalDAO.getGlobalDataRecordsByDateRange(
					DateHelper.convertStringToDate(retriveStartDate), DateHelper.convertStringToDate(retriveEndDate));
			globalDataEntities.forEach(entity -> {
				GlobalDataRecord tempGlobalRecord = new GlobalDataRecord();
				mapper.mapEntityToRecord(entity, tempGlobalRecord);
				globalDataRecords.add(tempGlobalRecord);
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return globalDataRecords;
	}

}
