package fr.diginamic.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.BirthPlace;
import fr.diginamic.entity.Director;
import fr.diginamic.filehandler.ReaderUtil;

public class DirectorDao implements Dao<Director>{
	
	private EntityManager em;
	private TypedQuery<Director> querySelect;
	
	

	public DirectorDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select a from Director a where a.directorImdbId = :p1", Director.class);
	}

	@Override
	public List<Director> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Director insertIfNotExistCustom(EntityManager em, Director t) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Director insertIfNotExistCustom(EntityManager em,String directorImdbId, String nameOfDirector, String dateOfBirthOfDirector, BirthPlace birthPlace, String urlOfDirector) {
		// TODO Auto-generated method stub
		Director exDirector = selectCustom(em, directorImdbId);
		
		if (exDirector == null) {
			try {
				Date formatedDateOfBirthOfDirector;
				if (dateOfBirthOfDirector != null) {
					formatedDateOfBirthOfDirector = ReaderUtil.parseDate(dateOfBirthOfDirector);
				} else {
					formatedDateOfBirthOfDirector = null;
				}
				
				Director nwDirector = new Director(directorImdbId, nameOfDirector, formatedDateOfBirthOfDirector, birthPlace, urlOfDirector);
				em.persist(nwDirector);
				return nwDirector;
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}
		}
		return exDirector;
	}

	@Override
	public int updateCustom(EntityManager em, Director t1, Director t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Director t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Director selectCustom(EntityManager em, Director t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Director selectCustom(EntityManager em, String directorImdbId) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", directorImdbId);
		
		List<Director> director = querySelect.getResultList();
		if (director.size() == 0) {
			return null;
		}
		return director.get(0);
	}

}
