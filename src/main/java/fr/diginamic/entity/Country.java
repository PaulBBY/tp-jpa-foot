package fr.diginamic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@Column(name = "NAME_OF_COUNTRY")
	private String nameOfCountry;

	@Column(name = "URL_OF_COUNTRY")
	private String urlOfCountry;

	public Country(String nameOfCountry) {
		this.nameOfCountry = nameOfCountry;
	}

	public Country(String nameOfCountry, String urlOfCountry) {
		this.nameOfCountry = nameOfCountry;
		this.urlOfCountry = urlOfCountry;
	}

	public Country() {
	}

	public String getNameOfCountry() {
		return nameOfCountry;
	}

	public void setNameOfCountry(String nameOfCountry) {
		this.nameOfCountry = nameOfCountry;
	}

	public String getUrlOfCountry() {
		return urlOfCountry;
	}

	public void setUrlOfCountry(String urlOfCountry) {
		this.urlOfCountry = urlOfCountry;
	}

}
