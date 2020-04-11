package com.divyendu.covidbatchapp.mappers;

import org.springframework.batch.item.file.LineMapper;

import com.divyendu.covidbatchapp.models.GlobalDataRecord;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Used by the job to read data fro JSON and create java object
 */
public class GlobalDataJsonLineMapper implements LineMapper<GlobalDataRecord> {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public GlobalDataRecord mapLine(String line, int lineNumber) throws Exception {
		return mapper.readValue(line, GlobalDataRecord.class);
	}

}
