package fr.diginamic.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.diginamic.entity.association.MovieDirectorA;
import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Table(name = "director")
public class Director {

	@Id
	@Column(name = "DIRECTOR_IMDB_ID")
	private String directorImdbId;

	@Column(name = "NAME")
	private String nameOfDirector;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	@MaybeNull
	private Date dateOfBirthOfDirector;

	@ManyToOne
	@JoinColumn(name = "BIRTH_PLACE_FK")
	@MaybeNull
	private BirthPlace birthPlaceFk;

	@Column(name = "URL")
	@MaybeNull
	private String urlOfDirector;

	@OneToMany(mappedBy = "directorFk")
	private Set<MovieDirectorA> moviesOfDirector;

	public Director() {
	}

	public Director(String directorImdbId, String nameOfDirector, Date dateOfBirthOfDirector, BirthPlace birthPlaceFk,
			String urlOfDirector) {
		this.directorImdbId = directorImdbId;
		this.nameOfDirector = nameOfDirector;
		this.dateOfBirthOfDirector = dateOfBirthOfDirector;
		this.birthPlaceFk = birthPlaceFk;
		this.urlOfDirector = urlOfDirector;
	}

	@Override
	public String toString() {
		return "Director [directorImdbId=" + directorImdbId + ", nameOfDirector=" + nameOfDirector
				+ ", dateOfBirthOfDirector=" + dateOfBirthOfDirector + ", birthPlaceFk=" + birthPlaceFk
				+ ", urlOfDirector=" + urlOfDirector + ", moviesOfDirector=" + moviesOfDirector + "]";
	}

	public String getDirectorImdbId() {
		return directorImdbId;
	}

	public void setDirectorImdbId(String directorImdbId) {
		this.directorImdbId = directorImdbId;
	}

	public String getNameOfDirector() {
		return nameOfDirector;
	}

	public void setNameOfDirector(String nameOfDirector) {
		this.nameOfDirector = nameOfDirector;
	}

	public Date getDateOfBirthOfDirector() {
		return dateOfBirthOfDirector;
	}

	public void setDateOfBirthOfDirector(Date dateOfBirthOfDirector) {
		this.dateOfBirthOfDirector = dateOfBirthOfDirector;
	}

	public BirthPlace getBirthPlaceFk() {
		return birthPlaceFk;
	}

	public void setBirthPlaceFk(BirthPlace birthPlaceFk) {
		this.birthPlaceFk = birthPlaceFk;
	}

	public String getUrlOfDirector() {
		return urlOfDirector;
	}

	public void setUrlOfDirector(String urlOfDirector) {
		this.urlOfDirector = urlOfDirector;
	}

	public Set<MovieDirectorA> getMoviesOfDirector() {
		return moviesOfDirector;
	}

	public void setMoviesOfDirector(Set<MovieDirectorA> moviesOfDirector) {
		this.moviesOfDirector = moviesOfDirector;
	}

}
