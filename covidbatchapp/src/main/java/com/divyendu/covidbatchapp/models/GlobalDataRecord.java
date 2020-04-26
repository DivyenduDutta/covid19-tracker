package com.divyendu.covidbatchapp.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO for Global data details
 */
public class GlobalDataRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("data_received_date")
	private String dataReceiveDate;
	
	@JsonProperty("total_corona_virus_cases")
	private int totalCoronaVirusCases;
	
	@JsonProperty("total_deaths")
	private int totalDeaths;
	
	@JsonProperty("total_recovered")
	private int totalRecovered;
	
	
	public GlobalDataRecord() {}

	public GlobalDataRecord(String dataReceiveDate, int totalCoronaVirusCases, int totalDeaths, int totalRecovered) {
		super();
		this.dataReceiveDate = dataReceiveDate;
		this.totalCoronaVirusCases = totalCoronaVirusCases;
		this.totalDeaths = totalDeaths;
		this.totalRecovered = totalRecovered;
	}

	public String getDataReceiveDate() {
		return dataReceiveDate;
	}

	public void setDataReceiveDate(String dataReceiveDate) {
		this.dataReceiveDate = dataReceiveDate;
	}

	public int getTotalCoronaVirusCases() {
		return totalCoronaVirusCases;
	}


	public void setTotalCoronaVirusCases(int totalCoronaVirusCases) {
		this.totalCoronaVirusCases = totalCoronaVirusCases;
	}


	public int getTotalDeaths() {
		return totalDeaths;
	}


	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}


	public int getTotalRecovered() {
		return totalRecovered;
	}


	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "GlobalDataRecord [dataReceiveDate=" + dataReceiveDate + ", totalCoronaVirusCases="
				+ totalCoronaVirusCases + ", totalDeaths=" + totalDeaths + ", totalRecovered=" + totalRecovered + "]";
	}
	
	
	

}
