package fr.diginamic.entity.association;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.diginamic.entity.Director;
import fr.diginamic.entity.Movie;

@Entity
@Table(name="movieDirectorA")
public class MovieDirectorA {

	@Id
	@Column(name="MOVIE_DIRECTOR_A_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieDirectorAId;
	

	@ManyToOne
	@JoinColumn(name="MOVIE_FK")
	private Movie movieFk;
	
	@ManyToOne
	@JoinColumn(name="DIRECTOR_FK")
	private Director directorFk;
	

	public MovieDirectorA() {
	}

	

	public MovieDirectorA(Movie movieFk, Director directorFk) {
		this.movieFk = movieFk;
		this.directorFk = directorFk;
	}



	public int getMovieDirectorAId() {
		return movieDirectorAId;
	}

	public void setMovieDirectorAId(int movieDirectorAId) {
		this.movieDirectorAId = movieDirectorAId;
	}

	public Director getDirectorFk() {
		return directorFk;
	}

	public void setDirectorFk(Director directorFk) {
		this.directorFk = directorFk;
	}

	public Movie getMovieFk() {
		return movieFk;
	}

	public void setMovieFk(Movie movieFk) {
		this.movieFk = movieFk;
	}

	@Override
	public String toString() {
		return "MovieDirectorA [movieDirectorAId=" + movieDirectorAId + ", directorFk=" + directorFk + ", movieFk="
				+ movieFk + "]";
	}
	
	
	
	
}
