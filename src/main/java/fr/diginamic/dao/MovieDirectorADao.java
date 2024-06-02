package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Director;
import fr.diginamic.entity.Movie;
import fr.diginamic.entity.association.MovieDirectorA;

public class MovieDirectorADao implements Dao<MovieDirectorA> {

	private EntityManager em;
	private TypedQuery<MovieDirectorA> querySelect;

	public MovieDirectorADao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery(
				"select a from MovieDirectorA a join a.movieFk m join a.directorFk d where m.movieImdbId = :p1 and d.directorImdbId = :p2",
				MovieDirectorA.class);
	}

	@Override
	public List<MovieDirectorA> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieDirectorA insertIfNotExistCustom(EntityManager em, MovieDirectorA t) {
		// TODO Auto-generated method stub
		return null;
	}

	public MovieDirectorA insertIfNotExistCustom(EntityManager em, Movie movie, Director director) {
		// TODO Auto-generated method stub
		MovieDirectorA exMovieDirectorA = selectCustom(em, movie, director);

		try {
			if (exMovieDirectorA == null) {
				MovieDirectorA nwMovieDirectorA = new MovieDirectorA(movie, director);
				em.persist(nwMovieDirectorA);
				return nwMovieDirectorA;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

		return exMovieDirectorA;
	}

	@Override
	public int updateCustom(EntityManager em, MovieDirectorA t1, MovieDirectorA t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, MovieDirectorA t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MovieDirectorA selectCustom(EntityManager em, MovieDirectorA t) {
		// TODO Auto-generated method stub

		return null;
	}

	public MovieDirectorA selectCustom(EntityManager em, Movie movie, Director director) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", movie.getMovieImdbId());
		querySelect.setParameter("p2", director.getDirectorImdbId());
		List<MovieDirectorA> exMovieDirectorA = querySelect.getResultList();
		if (exMovieDirectorA.size() == 0) {
			return null;
		}
		return exMovieDirectorA.get(0);
	}

}
