package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Country;

public class CountryDao implements Dao<Country> {

	private EntityManager em;
	private TypedQuery<Country> querySelect;

	public CountryDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select c from Country c where c.nameOfCountry = :p1", Country.class);
	}

	@Override
	public List<Country> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country insertIfNotExistCustom(EntityManager em, Country t) {
		// TODO Auto-generated method stub

		return null;
	}

	public Country insertIfNotExistCustom(EntityManager em, String nameOfCountry, String urlOfCountry) {
		// TODO Auto-generated method stub
		Country exCountry = selectCustom(em, nameOfCountry);

		if (exCountry == null) {

			try {
				Country nwCountry = new Country(nameOfCountry, urlOfCountry);
				em.persist(nwCountry);
				return nwCountry;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}

		}
		return exCountry;
	}
	
	public Country insertIfNotExistCustom(EntityManager em, String nameOfCountry) {
		// TODO Auto-generated method stub
		Country exCountry = selectCustom(em, nameOfCountry);

		if (exCountry == null) {

			try {
				Country nwCountry = new Country(nameOfCountry);
				em.persist(nwCountry);
				return nwCountry;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}

		}
		return exCountry;
	}

	@Override
	public int updateCustom(EntityManager em, Country t1, Country t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Country t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Country selectCustom(EntityManager em, Country t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Country selectCustom(EntityManager em, String nameOfCountry) {

		querySelect.setParameter("p1", nameOfCountry);

		List<Country> country = querySelect.getResultList();

		if (country.size() == 0) {
			return null;
		}
		return country.get(0);
	}

}
