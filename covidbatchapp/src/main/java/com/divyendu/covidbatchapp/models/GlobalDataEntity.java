package com.divyendu.covidbatchapp.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity class for global_data table
 */
@Entity
@Table(name = "global_data")
public class GlobalDataEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "data_retrieve_date")
	private Date dataReceiveDate;
	
	@Column(name = "total_cv_cases")
	private int totalCoronaVirusCases;
	
	@Column(name = "total_deaths")
	private int totalDeaths;
	
	@Column(name = "total_recovered")
	private int totalRecovered;

	public GlobalDataEntity() {}

	public GlobalDataEntity(@NotNull Date dataReceiveDate, @NotNull int totalCoronaVirusCases, @NotNull int totalDeaths, @NotNull int totalRecovered) {
		super();
		this.dataReceiveDate = dataReceiveDate;
		this.totalCoronaVirusCases = totalCoronaVirusCases;
		this.totalDeaths = totalDeaths;
		this.totalRecovered = totalRecovered;
	}

	@Override
	public String toString() {
		return "GlobalDataEntity [dataReceiveDate=" + dataReceiveDate + ", totalCoronaVirusCases="
				+ totalCoronaVirusCases + ", totalDeaths=" + totalDeaths + ", totalRecovered=" + totalRecovered + "]";
	}

	public Date getDataReceiveDate() {
		return dataReceiveDate;
	}

	public void setDataReceiveDate(Date dataReceiveDate) {
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

}
