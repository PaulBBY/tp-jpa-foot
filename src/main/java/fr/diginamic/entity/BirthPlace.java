package fr.diginamic.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Table(name = "birthPlace")

public class BirthPlace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BIRTH_PLACE_ID")
	private int birthPlaceId;

	@Column(name = "CITY_OF_BIRTH")
	private String cityOfBirth;

	@Column(name = "ADDITIONAL_INFORMATION")
	@MaybeNull
	private String additionalInformation;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_OF_BIRTH")
	private Country country;

	@OneToMany(mappedBy = "birthPlaceFk")
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Director> directors;

	@OneToMany(mappedBy = "birthPlaceFk")
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Actor> actors;

	public BirthPlace() {
	}

	public BirthPlace(String cityOfBirth, String additionalInformation, Country country) {
		this.cityOfBirth = cityOfBirth;
		this.additionalInformation = additionalInformation;
		this.country = country;
	}
	
	

	public BirthPlace(String cityOfBirth, String additionalInformation) {
		this.cityOfBirth = cityOfBirth;
		this.additionalInformation = additionalInformation;
	}

	public int getBirthPlaceId() {
		return birthPlaceId;
	}

	public void setBirthPlaceId(int birthPlaceId) {
		this.birthPlaceId = birthPlaceId;
	}

	public String getCityOfBirth() {
		return cityOfBirth;
	}

	public void setCityOfBirth(String cityOfBirth) {
		this.cityOfBirth = cityOfBirth;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<Director> directors) {
		this.directors = directors;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "BirthPlace [birthPlaceId=" + birthPlaceId + ", cityOfBirth=" + cityOfBirth + ", additionalInformation="
				+ additionalInformation + ", country=" + country + ", directors=" + directors + ", actors=" + actors
				+ "]";
	}

	

}
