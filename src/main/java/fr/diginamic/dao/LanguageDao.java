package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Language;

public class LanguageDao implements Dao<Language> {

	private EntityManager em;
	private TypedQuery<Language> querySelect;
	
	
	
	public LanguageDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select l from Language l where l.language = :p1", Language.class);
	}

	@Override
	public List<Language> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language insertIfNotExistCustom(EntityManager em, Language t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Language insertIfNotExistCustom(EntityManager em, String language) {
		// TODO Auto-generated method stub
		Language exLanguage = selectCustom(em, language);
		if (exLanguage == null) {
			Language nwLanguage = new Language(language);
			em.persist(nwLanguage);
			return nwLanguage;
		}
		return exLanguage;
	}

	@Override
	public int updateCustom(EntityManager em, Language t1, Language t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Language t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Language selectCustom(EntityManager em, Language t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Language selectCustom(EntityManager em, String language) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", language);
		
		List<Language> exLanguage = querySelect.getResultList();
		if (exLanguage.size() == 0) {
			return null;
		}
		return exLanguage.get(0);
	}
}
