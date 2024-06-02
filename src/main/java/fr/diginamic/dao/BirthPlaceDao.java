package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.BirthPlace;
import fr.diginamic.entity.Country;

public class BirthPlaceDao implements Dao<BirthPlace> {

	private EntityManager em;
	private TypedQuery<BirthPlace> querySelect;

	public BirthPlaceDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery(
				"select b from BirthPlace b join b.country c where b.cityOfBirth = :p1 and b.additionalInformation = :p2 and c.nameOfCountry = :p3",
				BirthPlace.class);
	}

	@Override
	public List<BirthPlace> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BirthPlace insertIfNotExistCustom(EntityManager em, BirthPlace t) {
		// TODO Auto-generated method stub
		return null;
	}

	public BirthPlace insertIfNotExistCustom(EntityManager em, String cityOfBirth, String additionalInformation,
			Country country) {
		// TODO Auto-generated method stub
		BirthPlace exBirthPlace = selectCustom(em, cityOfBirth, additionalInformation, country);

		if (exBirthPlace == null) {

			try {
				BirthPlace nwBirthPlace = new BirthPlace(cityOfBirth, additionalInformation, country);
				em.persist(nwBirthPlace);
				return nwBirthPlace;

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}

		return exBirthPlace;
	}

	@Override
	public int updateCustom(EntityManager em, BirthPlace t1, BirthPlace t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, BirthPlace t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BirthPlace selectCustom(EntityManager em, BirthPlace t) {
		// TODO Auto-generated method stub

		return null;
	}

	public BirthPlace selectCustom(EntityManager em, String cityOfBirth, String additionalInformation, Country country) {
		// TODO Auto-generated method stub

		querySelect.setParameter("p1", cityOfBirth);
		querySelect.setParameter("p2", additionalInformation);
		querySelect.setParameter("p3", country.getNameOfCountry());
		

		List<BirthPlace> birthPlace = querySelect.getResultList();

		if (birthPlace.size() == 0) {
			return null;
		}
		return birthPlace.get(0);
	}

}
