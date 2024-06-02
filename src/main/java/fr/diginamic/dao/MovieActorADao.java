package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Actor;
import fr.diginamic.entity.Movie;
import fr.diginamic.entity.association.MovieActorA;

public class MovieActorADao implements Dao<MovieActorA>{

	private EntityManager em;
	private TypedQuery<MovieActorA> querySelect;
	
	
	
	public MovieActorADao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select a from MovieActorA a join a.movieFk m join a.actorFk ac where m.movieImdbId = :p1 and ac.actorImdbId = :p2", MovieActorA.class);
	}

	@Override
	public List<MovieActorA> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieActorA insertIfNotExistCustom(EntityManager em, MovieActorA t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MovieActorA insertIfNotExistCustom(EntityManager em, Movie movie, Actor actor, String role) {
		// TODO Auto-generated method stub
		
		MovieActorA exMovieActorA = selectCustom(em, movie, actor);
		
		try {
			
			if(exMovieActorA == null) {
				MovieActorA nwMovieActorA = new MovieActorA(movie, actor, role);
				em.persist(nwMovieActorA);
				return nwMovieActorA;
						
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return exMovieActorA;
	}


	@Override
	public int updateCustom(EntityManager em, MovieActorA t1, MovieActorA t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, MovieActorA t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MovieActorA selectCustom(EntityManager em, MovieActorA t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MovieActorA selectCustom(EntityManager em, Movie movie, Actor actor) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", movie.getMovieImdbId());
		querySelect.setParameter("p2", actor.getActorImdbId());
		List<MovieActorA> exMovieActorA = querySelect.getResultList();
		if(exMovieActorA.size()==0) {
			return null;
		}
		return exMovieActorA.get(0);
	}

}
