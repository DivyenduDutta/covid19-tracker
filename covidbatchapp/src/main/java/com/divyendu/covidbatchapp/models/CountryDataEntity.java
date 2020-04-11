package com.divyendu.covidbatchapp.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity class for country_data table
 */
@Entity
@Table(name = "country_data")
@IdClass(CountryDateID.class)
public class CountryDataEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "country")
	private String countryName;
	
	@Id
	@Column(name = "data_retrieve_date")
	private Date dataReceiveDate;
	
	@NotNull
	@Column(name = "total_cases")
	private int totalCases;
	
	@Column(name = "new_cases")
	private int newCases;
	
	@Column(name = "total_deaths")
	private int totalDeaths;
	
	@Column(name = "new_deaths")
	private int newDeaths;
	
	@Column(name = "total_recovered")
	private int totalRecovered;
	
	@Column(name = "active_cases")
	private int activeCases;
	
	@Column(name = "critical")
	private int critical;

	public CountryDataEntity() {}

	public CountryDataEntity(@NotNull String countryName, @NotNull Date dataReceiveDate, @NotNull int totalCases, int newCases,
			int totalDeaths, int newDeaths, int totalRecovered, int activeCases, int critical) {
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
	
	@Override
	public String toString() {
		return "CountryDataEntity [countryName=" + countryName + ", dataReceiveDate=" + dataReceiveDate
				+ ", totalCases=" + totalCases + ", newCases=" + newCases + ", totalDeaths=" + totalDeaths
				+ ", newDeaths=" + newDeaths + ", totalRecovered=" + totalRecovered + ", activeCases=" + activeCases
				+ ", critical=" + critical + "]";
	}

	
	
	
}
