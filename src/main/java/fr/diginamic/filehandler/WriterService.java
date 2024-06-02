package fr.diginamic.filehandler;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import fr.diginamic.dao.*;
import fr.diginamic.entity.Actor;
import fr.diginamic.entity.BirthPlace;
import fr.diginamic.entity.Country;
import fr.diginamic.entity.Director;
import fr.diginamic.entity.Genre;
import fr.diginamic.entity.Language;
import fr.diginamic.entity.Movie;
import fr.diginamic.entity.association.MovieActorA;
import fr.diginamic.entity.association.MovieDirectorA;
import fr.diginamic.entity.association.MovieGenreA;

public class WriterService {

	private EntityManagerFactory emf;
	private ArrayList<Path> pathToUse;
	private CountryDao countryDao;
	private BirthPlaceDao birthPlaceDao;
	private ActorDao actorDao;
	private DirectorDao directorDao;
	private GenreDao genreDao;
	private LanguageDao languageDao;
	private MovieDao movieDao;
	private MovieGenreADao movieGenreADao;
	private MovieDirectorADao movieDirectorADao;
	private MovieActorADao movieActorADao;
	private EntityManager emWrite;

	public WriterService(EntityManagerFactory emf, ArrayList<Path> pathToUse) {
		this.emf = emf;
		this.emWrite = this.emf.createEntityManager();
		this.countryDao = new CountryDao(emWrite);
		this.birthPlaceDao = new BirthPlaceDao(emWrite);
		this.actorDao = new ActorDao(emWrite);
		this.directorDao = new DirectorDao(emWrite);
		this.genreDao = new GenreDao(emWrite);
		this.languageDao = new LanguageDao(emWrite);
		this.movieDao = new MovieDao(emWrite);
		this.movieGenreADao = new MovieGenreADao(emWrite);
		this.movieDirectorADao = new MovieDirectorADao(emWrite);
		this.movieActorADao = new MovieActorADao(emWrite);

		this.pathToUse = pathToUse;
	}

	// invalid stuff

	public void writeCountryCsv() {

		List<String[]> returned = ReaderUtil.loadFile(pathToUse.get(4), 2);

		try {
			emWrite.getTransaction().begin();

			for (String[] s : returned) {

				Country country = countryDao.insertIfNotExistCustom(emWrite, s[0], s[1]);

			}
			emWrite.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public void writeActorCsv() {

		List<String[]> returned = ReaderUtil.loadFile(pathToUse.get(1), 6);

		try {

			int count = 0;
			boolean begin = true;

			for (String[] s : returned) {

				if (begin) {
					emWrite.getTransaction().begin();
					begin = false;
				}

				String actorImdbId = s[0];

				String nameOfActor = s[1];

				String dateOfBirthOfActor = s[2];

				String[] geography = s[3].split(",");
				int size = geography.length;
				String cityOfBirth = geography[0].trim();
				String nameOfCountry = geography[size - 1].trim();

				String additionalInformation;
				StringBuilder stringBuilderAdditionalInformation = new StringBuilder();

				if (size > 2) {
					for (int i = 1; i < size; i++) {
						stringBuilderAdditionalInformation.append(geography[i].trim());
					}
				}

				additionalInformation = stringBuilderAdditionalInformation.toString();

				String[] tempHeightOfActor = s[4].split(" ");
				String heightOfActor = tempHeightOfActor[0].trim();

				String urlOfActor = s[5];

				Country country = countryDao.insertIfNotExistCustom(emWrite, nameOfCountry);
				BirthPlace birthPlace = birthPlaceDao.insertIfNotExistCustom(emWrite, cityOfBirth,
						additionalInformation, country);
				Actor actor = actorDao.insertIfNotExistCustom(emWrite, actorImdbId, nameOfActor, dateOfBirthOfActor,
						birthPlace, heightOfActor, urlOfActor);

				count++;
				if (count % 50 == 0) {
					count = 0;
					emWrite.getTransaction().commit();
					begin = true;
					continue;
				}
			}

			emWrite.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void writeDirectorCsv() {

		List<String[]> returned = ReaderUtil.loadFile(pathToUse.get(5), 5);

		try {
			int count = 0;
			boolean begin = true;

			for (String[] s : returned) {
				if (begin) {
					emWrite.getTransaction().begin();
					begin = false;
				}

				String directorImdbId = s[0];
				String nameOfDirector = s[1];
				String dateOfBirthOfDirector = s[2];

				String[] geography = s[3].split(",");
				int size = geography.length;

				String cityOfBirth = geography[0].trim();
				String nameOfCountry = geography[size - 1].trim();

				String additionalInformation;
				StringBuilder stringBuilderAdditionalInformation = new StringBuilder();

				if (size > 2) {
					for (int i = 1; i < size; i++) {
						stringBuilderAdditionalInformation.append(geography[i].trim());
					}
				}
				additionalInformation = stringBuilderAdditionalInformation.toString();

				String urlOfDirector = s[4];

				Country country = countryDao.insertIfNotExistCustom(emWrite, nameOfCountry);
				BirthPlace birthPlace = birthPlaceDao.insertIfNotExistCustom(emWrite, cityOfBirth,
						additionalInformation, country);

				Director director = directorDao.insertIfNotExistCustom(emWrite, directorImdbId, nameOfDirector,
						dateOfBirthOfDirector, birthPlace, urlOfDirector);

				count++;
				if (count % 50 == 0) {
					count = 0;
					emWrite.getTransaction().commit();
					begin = true;
					continue;
				}

			}
			emWrite.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e);
		}

	}

	public void writeMovieCsv() {

		List<String[]> returned = ReaderUtil.loadFile(pathToUse.get(3), 10);

		try {

			int count = 0;
			boolean begin = true;

			for (String[] s : returned) {
				if (begin) {
					emWrite.getTransaction().begin();
					begin = false;

				}

				String movieImdbId = s[0];
				String nameOfMovie = s[1];
				String yearOfMovie = s[2];
				String rate = s[3].trim();
				String urlOfMovie = s[4];
				String setPlace = s[5];

				String[] genres = s[6].split(",");

				List<Genre> genreOfMovie = new ArrayList<>();

				for (String v : genres) {
					Genre genre = genreDao.insertIfNotExistCustom(emWrite, v);
					genreOfMovie.add(genre);
				}

				System.out.println(genreOfMovie.size());

				String nameOfLanguage = s[7];
				Language language = languageDao.insertIfNotExistCustom(emWrite, nameOfLanguage);

				String nameOfCountry = s[9];

				Country country = countryDao.insertIfNotExistCustom(emWrite, nameOfCountry);

				Movie movie = movieDao.insertIfNotExistCustom(emWrite, movieImdbId, nameOfMovie, yearOfMovie, rate,
						urlOfMovie, setPlace, language, country);

				Set<MovieGenreA> movieGenreASet = new HashSet<>();

				for (Genre g : genreOfMovie) {
					MovieGenreA movieGenreA = movieGenreADao.insertIfNotExistCustom(emWrite, movie, g);
					movieGenreASet.add(movieGenreA);
				}

				movie.setGenreOfMovie(movieGenreASet);
				emWrite.merge(movie);

				count++;

				if (count % 50 == 0) {
					count = 0;
					emWrite.getTransaction().commit();
					begin = true;
					continue;
				}

			}
			emWrite.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void writeMovieDirectorCsv() {

		List<String[]> returned = ReaderUtil.loadFile(pathToUse.get(5), 2);

		try {

			int count = 0;
			boolean begin = true;

			for (String[] s : returned) {
				if (begin) {
					emWrite.getTransaction().begin();
					begin = false;

				}

				String movieImdbId = s[0];
				String directorImdbId = s[1];

				Movie movie = movieDao.selectCustom(emWrite, movieImdbId);
				Director director = directorDao.selectCustom(emWrite, directorImdbId);

				MovieDirectorA movieDirectorA = movieDirectorADao.insertIfNotExistCustom(emWrite, movie, director);

				if (count % 50 == 0) {
					count = 0;
					emWrite.getTransaction().commit();
					begin = true;
					continue;
				}

			}

			emWrite.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void writeMovieActorCsv() {

		List<String[]> returned = ReaderUtil.loadFile(pathToUse.get(6), 3);

		try {

			int count = 0;
			boolean begin = true;

			for (String[] s : returned) {
				if (begin) {
					emWrite.getTransaction().begin();
					begin = false;

				}

				String movieImdbId = s[0];
				String actorImdbId = s[1];
				String role = s[2];

				Movie movie = movieDao.selectCustom(emWrite, movieImdbId);
				Actor actor = actorDao.selectCustom(emWrite, actorImdbId);

				MovieActorA movieActorA = movieActorADao.insertIfNotExistCustom(emWrite, movie, actor, role);
				
				if (count % 50 == 0) {
					count = 0;
					emWrite.getTransaction().commit();
					begin = true;
					continue;
				}

			}

			emWrite.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public void writeMainRoleCsv () {
		
		List<String[]> returned = ReaderUtil.loadFile(pathToUse.get(2), 2);
		
		try {

			int count = 0;
			boolean begin = true;

			for (String[] s : returned) {
				if (begin) {
					emWrite.getTransaction().begin();
					begin = false;

				}
				
				String movieImdbId = s[0];
				String actorImdbId = s[1];
				
				Movie movie = movieDao.selectCustom(emWrite, movieImdbId);
				Actor actor = actorDao.selectCustom(emWrite, actorImdbId);
				
				MovieActorA movieActorA = movieActorADao.selectCustom(emWrite, movie, actor);
				if (movieActorA != null) {
					movieActorA.setIsPrincipal(true);
					emWrite.merge(movieActorA);
				}
				
				if (count % 50 == 0) {
					count = 0;
					emWrite.getTransaction().commit();
					begin = true;
					continue;
				}
				
			}
			emWrite.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
	}

	public void writeSequence() {

		writeCountryCsv();
		writeActorCsv();
		writeDirectorCsv();
		writeMovieCsv();
		writeMovieDirectorCsv();
		writeMovieActorCsv();
		writeMainRoleCsv();
		

	}

}
