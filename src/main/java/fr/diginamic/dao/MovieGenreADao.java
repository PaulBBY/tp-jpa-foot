package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Genre;
import fr.diginamic.entity.Movie;
import fr.diginamic.entity.association.MovieGenreA;

public class MovieGenreADao implements Dao<MovieGenreA>{
	
	private EntityManager em;
	private TypedQuery<MovieGenreA> querySelect;
	
	

	public MovieGenreADao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select a from MovieGenreA a join a.movieFk m join a.genreFk g where m.movieImdbId =:p1 and g.genre =:p2", MovieGenreA.class);
	}

	@Override
	public List<MovieGenreA> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieGenreA insertIfNotExistCustom(EntityManager em, MovieGenreA t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MovieGenreA insertIfNotExistCustom(EntityManager em, Movie movie, Genre genre) {
		// TODO Auto-generated method stub

		MovieGenreA exMovieGenreA = selectCustom(em, movie, genre);
		
		try {

			if(exMovieGenreA == null) {
				MovieGenreA nwMovieGenreA = new MovieGenreA(movie, genre);
				em.persist(nwMovieGenreA);
				return nwMovieGenreA;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		return exMovieGenreA;
	}

	@Override
	public int updateCustom(EntityManager em, MovieGenreA t1, MovieGenreA t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, MovieGenreA t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MovieGenreA selectCustom(EntityManager em, MovieGenreA t) {
		// TODO Auto-generated method stub
		
		return null;
	}
	public MovieGenreA selectCustom(EntityManager em, Movie movie, Genre genre) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", movie.getMovieImdbId());
		querySelect.setParameter("p2", genre.getGenre());
		List<MovieGenreA> exMovieGenreA = querySelect.getResultList();
		if(exMovieGenreA.size()==0) {
			return null;
		}
		return exMovieGenreA.get(0);
	}
}
