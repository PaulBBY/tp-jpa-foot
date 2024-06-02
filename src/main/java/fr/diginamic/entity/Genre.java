package fr.diginamic.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.diginamic.entity.association.MovieGenreA;

@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@Column(name = "GENRE")
	private String genre;

	@OneToMany(mappedBy = "genreFk")
	private Set<MovieGenreA> movieOfGenre;

	public Genre() {

	}

	public Genre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Genre [genre=" + genre + ", movieOfGenre=" + movieOfGenre + "]";
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<MovieGenreA> getMovieOfGenre() {
		return movieOfGenre;
	}

	public void setMovieOfGenre(Set<MovieGenreA> movieOfGenre) {
		this.movieOfGenre = movieOfGenre;
	}

}
