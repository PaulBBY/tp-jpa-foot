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

import fr.diginamic.entity.association.MovieActorA;
import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Table(name = "actor")
public class Actor {

	@Id
	@Column(name = "ACTOR_IMDB_ID")
	private String actorImdbId;

	@Column(name = "NAME")
	private String nameOfActor;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	@MaybeNull
	private Date dateOfBirthOfActor;

	@ManyToOne
	@JoinColumn(name = "BIRTH_PLACE_FK")
	@MaybeNull
	private BirthPlace birthPlaceFk;

	@Column(name = "HEIGHT")
	@MaybeNull
	private Float heigthOfActor;

	@Column(name = "URL")
	@MaybeNull
	private String urlOfActor;

	@OneToMany(mappedBy = "actorFk")
	private Set<MovieActorA> moviesOfActor;

	public Actor() {
	}

	public Actor(String actorImdbId, String nameOfActor, Date dateOfBirthOfActor,
			BirthPlace birthPlaceFk, Float heigthOfActor, String urlOfActor) {
		this.actorImdbId = actorImdbId;
		this.nameOfActor = nameOfActor;
		this.dateOfBirthOfActor = dateOfBirthOfActor;
		this.birthPlaceFk = birthPlaceFk;
		this.heigthOfActor = heigthOfActor;
		this.urlOfActor = urlOfActor;
	}

	public Actor(String actorImdbId, String nameOfActor, BirthPlace birthPlaceFk,
			String urlOfActor) {
		this.actorImdbId = actorImdbId;
		this.nameOfActor = nameOfActor;
		this.birthPlaceFk = birthPlaceFk;
		this.urlOfActor = urlOfActor;
	}

	public String getActorImdbId() {
		return actorImdbId;
	}

	public void setActorImdbId(String actorImdbId) {
		this.actorImdbId = actorImdbId;
	}



	public String getNameOfActor() {
		return nameOfActor;
	}

	public void setNameOfActor(String nameOfActor) {
		this.nameOfActor = nameOfActor;
	}

	public Date getDateOfBithOfActor() {
		return dateOfBirthOfActor;
	}

	public void setDateOfBithOfActor(Date dateOfBithOfActor) {
		this.dateOfBirthOfActor = dateOfBithOfActor;
	}

	public Float getHeigthOfActor() {
		return heigthOfActor;
	}

	public void setHeigthOfActor(Float heigthOfActor) {
		this.heigthOfActor = heigthOfActor;
	}

	public BirthPlace getBirthPlaceFk() {
		return birthPlaceFk;
	}

	public void setBirthPlaceFk(BirthPlace birthPlaceFk) {
		this.birthPlaceFk = birthPlaceFk;
	}

	public String getUrlOfActor() {
		return urlOfActor;
	}

	public void setUrlOfActor(String urlOfActor) {
		this.urlOfActor = urlOfActor;
	}

	public Set<MovieActorA> getMoviesOfActor() {
		return moviesOfActor;
	}

	public void setMoviesOfActor(Set<MovieActorA> moviesOfActor) {
		this.moviesOfActor = moviesOfActor;
	}

	@Override
	public String toString() {
		return "Actor [actorImdbId=" + actorImdbId + ", nameOfActor="
				+ nameOfActor + ", dateOfBithOfActor=" + dateOfBirthOfActor + ", heigthOfActor=" + heigthOfActor
				+ ", birthPlaceFk=" + birthPlaceFk + ", urlOfActor=" + urlOfActor + ", moviesOfActor=" + moviesOfActor
				+ "]";
	}

}
