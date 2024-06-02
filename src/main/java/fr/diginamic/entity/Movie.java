package fr.diginamic.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.diginamic.entity.association.MovieGenreA;
import net.bytebuddy.utility.nullability.MaybeNull;
import fr.diginamic.entity.association.MovieActorA;
import fr.diginamic.entity.association.MovieDirectorA;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@Column(name = "MOVIE_IMDB_ID")
	private String movieImdbId;

	@Column(name = "NAME_OF_MOVIE")
	private String nameOfMovie;

	@Column(name = "YEAR_OF_MOVIE")
	@MaybeNull
	private Integer yearOfMovie;

	@Column(name = "RATING")
	@MaybeNull
	private Float rate;

	@Column(name = "URL_OF_MOVIE")
	@MaybeNull
	private String urlOfMovie;

	@Column(name = "SET_PLACE")
	@MaybeNull
	private String setPlace;

	@OneToMany(mappedBy = "movieFk")
	private Set<MovieGenreA> genreOfMovie;

	@ManyToOne
	@JoinColumn(name = "LANGUAGE_FK")
	@MaybeNull
	private Language languageFk;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_FK")
	@MaybeNull
	private Country countryFk;

	@OneToMany(mappedBy = "movieFk")
	private Set<MovieActorA> actorsOfMovie;
	
	@OneToMany(mappedBy = "movieFk")
	private Set<MovieDirectorA> directorOfMovie;

	public Movie() {
	}

	public Movie(String movieImdbId, String nameOfMovie, Integer yearOfMovie, Float rate, String urlOfMovie,
			String setPlace, Language languageFk, Country countryFk) {
		this.movieImdbId = movieImdbId;
		this.nameOfMovie = nameOfMovie;
		this.yearOfMovie = yearOfMovie;
		this.rate = rate;
		this.urlOfMovie = urlOfMovie;
		this.setPlace = setPlace;
		this.languageFk = languageFk;
		this.countryFk = countryFk;
	}

	public String getMovieImdbId() {
		return movieImdbId;
	}

	public void setMovieImdbId(String movieImdbId) {
		this.movieImdbId = movieImdbId;
	}

	public String getNameOfMovie() {
		return nameOfMovie;
	}

	public void setNameOfMovie(String nameOfMovie) {
		this.nameOfMovie = nameOfMovie;
	}

	public Integer getYearOfMovie() {
		return yearOfMovie;
	}

	public void setYearOfMovie(Integer yearOfMovie) {
		this.yearOfMovie = yearOfMovie;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getUrlOfMovie() {
		return urlOfMovie;
	}

	public void setUrlOfMovie(String urlOfMovie) {
		this.urlOfMovie = urlOfMovie;
	}

	public String getSetPlace() {
		return setPlace;
	}

	public void setSetPlace(String setPlace) {
		this.setPlace = setPlace;
	}

	public Set<MovieGenreA> getGenreOfMovie() {
		return genreOfMovie;
	}

	public void setGenreOfMovie(Set<MovieGenreA> genreOfMovie) {
		this.genreOfMovie = genreOfMovie;
	}

	public Language getLanguageFk() {
		return languageFk;
	}

	public void setLanguageFk(Language languageFk) {
		this.languageFk = languageFk;
	}


	public Country getCountryFk() {
		return countryFk;
	}

	public void setCountryFk(Country countryFk) {
		this.countryFk = countryFk;
	}

}
