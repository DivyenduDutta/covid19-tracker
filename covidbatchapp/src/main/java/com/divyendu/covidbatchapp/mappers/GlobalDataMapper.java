package com.divyendu.covidbatchapp.mappers;

import org.springframework.stereotype.Component;

import com.divyendu.covidbatchapp.helper.DateHelper;
import com.divyendu.covidbatchapp.models.GlobalDataEntity;
import com.divyendu.covidbatchapp.models.GlobalDataRecord;

@Component
public class GlobalDataMapper {
	
	public void mapEntityToRecord(GlobalDataEntity entity, GlobalDataRecord record) {
		record.setDataReceiveDate(DateHelper.convertDateToString(entity.getDataReceiveDate()));
		record.setTotalCoronaVirusCases(entity.getTotalCoronaVirusCases());
		record.setTotalDeaths(entity.getTotalDeaths());
		record.setTotalRecovered(entity.getTotalRecovered());
	}

}
