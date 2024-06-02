package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Country;
import fr.diginamic.entity.Language;
import fr.diginamic.entity.Movie;
import fr.diginamic.filehandler.ReaderUtil;

public class MovieDao implements Dao<Movie> {

	private EntityManager em;
	private TypedQuery<Movie> querySelect;

	public MovieDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select m from Movie m where m.movieImdbId = :p1", Movie.class);
	}

	@Override
	public List<Movie> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie insertIfNotExistCustom(EntityManager em, Movie t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Movie insertIfNotExistCustom(EntityManager em, String movieImdbId, String nameOfMovie, String yearOfMovie,
			String rate, String urlOfMovie, String setPlace, Language language,  Country country) {
		// TODO Auto-generated method stub

		Movie exMovie = selectCustom(em, movieImdbId);
		if (exMovie == null) {
			try {
				Float formatedRate;
				if (rate != null) {
					formatedRate = ReaderUtil.parseFloat(rate);
				} else {
					formatedRate = null;
				}

				Integer formatedYear;
				if (yearOfMovie != null) {
					formatedYear = ReaderUtil.parseInt(yearOfMovie);
				} else {
					formatedYear = null;
				}

				Movie nwMovie = new Movie(movieImdbId, nameOfMovie, formatedYear, formatedRate, urlOfMovie, setPlace,
						language, country);
				em.persist(nwMovie);
				return nwMovie;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
				return null;
			}
		}
		return exMovie;
	}

	@Override
	public int updateCustom(EntityManager em, Movie t1, Movie t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Movie t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Movie selectCustom(EntityManager em, Movie t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Movie selectCustom(EntityManager em, String movieImdbId) {
		// TODO Auto-generated method stub

		querySelect.setParameter("p1", movieImdbId);
		List<Movie> exMovie = querySelect.getResultList();
		if (exMovie.size() == 0) {
			return null;
		}
		return exMovie.get(0);
	}

}
