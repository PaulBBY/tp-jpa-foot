package fr.diginamic.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

	@Id
	@Column(name = "LANGUAGE")
	private String language;

	@OneToMany(mappedBy = "languageFk")
	private Set<Movie> movieOfLanguage;

	public Language() {
	}

	public Language(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Language [language=" + language + ", movieOfLanguage=" + movieOfLanguage + "]";
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Set<Movie> getMovieOfLanguage() {
		return movieOfLanguage;
	}

	public void setMovieOfLanguage(Set<Movie> movieOfLanguage) {
		this.movieOfLanguage = movieOfLanguage;
	}

}
