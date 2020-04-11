package com.divyendu.covidbatchapp.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO for Country data details
 */
public class CountryDataRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("country_name")
	private String countryName;
	
	@JsonProperty("data_received_date")
	private String dataReceiveDate;
	
	@JsonProperty("total_cases")
	private int totalCases;
	
	@JsonProperty("new_cases")
	private int newCases;
	
	@JsonProperty("total_deaths")
	private int totalDeaths;
	
	@JsonProperty("new_deaths")
	private int newDeaths;
	
	@JsonProperty("total_recovered")
	private int totalRecovered;
	
	@JsonProperty("active_cases")
	private int activeCases;
	
	@JsonProperty("critical")
	private int critical;
	
	public CountryDataRecord() {}

	public CountryDataRecord(String countryName, String dataReceiveDate, int totalCases, int newCases, int totalDeaths,
			int newDeaths, int totalRecovered, int activeCases, int critical) {
		super();
		this.countryName = countryName;
		this.dataReceiveDate = dataReceiveDate;
		this.totalCases = totalCases;
		this.newCases = newCases;
		this.totalDeaths = totalDeaths;
		this.newDeaths = newDeaths;
		this.totalRecovered = totalRecovered;
		this.activeCases = activeCases;
		this.critical = critical;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getDataReceiveDate() {
		return dataReceiveDate;
	}

	public void setDataReceiveDate(String dataReceiveDate) {
		this.dataReceiveDate = dataReceiveDate;
	}

	public int getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}

	public int getNewCases() {
		return newCases;
	}

	public void setNewCases(int newCases) {
		this.newCases = newCases;
	}

	public int getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public int getNewDeaths() {
		return newDeaths;
	}

	public void setNewDeaths(int newDeaths) {
		this.newDeaths = newDeaths;
	}

	public int getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	public int getActiveCases() {
		return activeCases;
	}

	public void setActiveCases(int activeCases) {
		this.activeCases = activeCases;
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CountryDataRecord [countryName=" + countryName + ", dataReceiveDate=" + dataReceiveDate
				+ ", totalCases=" + totalCases + ", newCases=" + newCases + ", totalDeaths=" + totalDeaths
				+ ", newDeaths=" + newDeaths + ", totalRecovered=" + totalRecovered + ", activeCases=" + activeCases
				+ ", critical=" + critical + "]";
	}
	
	
	

}
