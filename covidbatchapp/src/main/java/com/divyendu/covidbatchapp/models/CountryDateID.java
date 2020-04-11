package com.divyendu.covidbatchapp.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Used to model composite primary key of country_data table
 */
public class CountryDateID implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String countryName;
	private Date dataReceiveDate;
	
	public CountryDateID() {}

	public CountryDateID(String countryName, Date dataReceiveDate) {
		this.countryName = countryName;
		this.dataReceiveDate = dataReceiveDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((dataReceiveDate == null) ? 0 : dataReceiveDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryDateID other = (CountryDateID) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (dataReceiveDate == null) {
			if (other.dataReceiveDate != null)
				return false;
		} else if (!dataReceiveDate.equals(other.dataReceiveDate))
			return false;
		return true;
	}

	
}
