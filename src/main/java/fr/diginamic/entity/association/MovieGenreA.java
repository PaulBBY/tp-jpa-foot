package fr.diginamic.entity.association;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.diginamic.entity.Genre;
import fr.diginamic.entity.Movie;

@Entity
@Table(name = "movieGenreA")
public class MovieGenreA {

	@Id
	@Column(name = "MOVIE_GENRE_A_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieGenreAId;

	@ManyToOne
	@JoinColumn(name = "MOVIE_FK")
	private Movie movieFk;

	@ManyToOne
	@JoinColumn(name = "GENRE_FK")
	private Genre genreFk;

	public MovieGenreA() {
	}

	
	public MovieGenreA(Movie movieFk, Genre genreFk) {
		this.movieFk = movieFk;
		this.genreFk = genreFk;
	}

	@Override
	public String toString() {
		return "MovieGenreA [movieGenreAId=" + movieGenreAId + ", genreFk=" + genreFk + ", movieFk=" + movieFk + "]";
	}

	public int getMovieGenreAId() {
		return movieGenreAId;
	}

	public void setMovieGenreAId(int movieGenreAId) {
		this.movieGenreAId = movieGenreAId;
	}

	public Genre getGenreFk() {
		return genreFk;
	}

	public void setGenreFk(Genre genreFk) {
		this.genreFk = genreFk;
	}

	public Movie getMovieFk() {
		return movieFk;
	}

	public void setMovieFk(Movie movieFk) {
		this.movieFk = movieFk;
	}

}
