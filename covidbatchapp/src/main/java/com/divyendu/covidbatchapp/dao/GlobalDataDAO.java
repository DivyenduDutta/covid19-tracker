package com.divyendu.covidbatchapp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divyendu.covidbatchapp.models.GlobalDataEntity;
import com.divyendu.covidbatchapp.repositories.GlobalDataRepository;

@Component
public class GlobalDataDAO {

	@Autowired
	private GlobalDataRepository globalDataRepo;

	public GlobalDataEntity getGlobalDataRecordByRetreiveDate(Date retrieveDate) {
		GlobalDataEntity globalDataEntity = globalDataRepo.findById(retrieveDate).orElse(null);
		return globalDataEntity;
	}

	public List<GlobalDataEntity> getGlobalDataRecordsByDateRange(Date retrieveStartDate, Date retrieveEndDate) {
		List<GlobalDataEntity> globalDataEntityRecords = globalDataRepo.findByDataReceiveDateBetween(retrieveStartDate,
				retrieveEndDate);
		return globalDataEntityRecords;
	}

}
