package fr.diginamic.entity.association;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.diginamic.entity.Actor;
import fr.diginamic.entity.Movie;

@Entity
@Table(name = "movieActorA")
public class MovieActorA {

	@Id
	@Column(name = "MOVIE_ACTOR_A_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieActorAId;

	@ManyToOne
	@JoinColumn(name = "MOVIE_FK")
	private Movie movieFk;

	@ManyToOne
	@JoinColumn(name = "ACTOR_FK")
	private Actor actorFk;

	@Column(name = "NAME_OF_ROLE")
	private String role;

	@Column(name = "IS_PRINCIPAL")
	private Boolean isPrincipal;

	public MovieActorA() {
	}

	public MovieActorA(Movie movieFk, Actor actorFk, String role) {
		this.movieFk = movieFk;

		this.actorFk = actorFk;
		this.role = role;
		this.isPrincipal = false;
	}

	@Override
	public String toString() {
		return "MovieActorA [movieActorAId=" + movieActorAId + ", actorFk=" + actorFk + ", movieFk=" + movieFk + "]";
	}

	public int getMovieActorAId() {
		return movieActorAId;
	}

	public void setMovieActorAId(int movieActorAId) {
		this.movieActorAId = movieActorAId;
	}

	public Actor getActorFk() {
		return actorFk;
	}

	public void setActorFk(Actor actorFk) {
		this.actorFk = actorFk;
	}

	public Movie getMovieFk() {
		return movieFk;
	}

	public void setMovieFk(Movie movieFk) {
		this.movieFk = movieFk;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsPrincipal() {
		return isPrincipal;
	}

	public void setIsPrincipal(Boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

}
