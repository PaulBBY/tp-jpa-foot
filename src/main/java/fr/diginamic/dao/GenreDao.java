package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Genre;

public class GenreDao implements Dao<Genre>{
	
	private EntityManager em;
	private TypedQuery<Genre> querySelect;
	
	

	public GenreDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select g from Genre g where g.genre = :p1", Genre.class);
	}

	@Override
	public List<Genre> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genre insertIfNotExistCustom(EntityManager em, Genre t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Genre insertIfNotExistCustom(EntityManager em, String genre) {
		// TODO Auto-generated method stub
		
		Genre exGenre = selectCustom(em, genre);
		if (exGenre == null) {
			Genre nwGenre = new Genre(genre);
			em.persist(nwGenre);
			return nwGenre;
		}
		return exGenre;
	}

	@Override
	public int updateCustom(EntityManager em, Genre t1, Genre t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Genre t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Genre selectCustom(EntityManager em, Genre t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Genre selectCustom(EntityManager em, String genre) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", genre);
		
		List<Genre> genres = querySelect.getResultList();
		if (genres.size() == 0) {
			return null;
		}
		return genres.get(0);
	}

}
