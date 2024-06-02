package fr.diginamic.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entity.Actor;
import fr.diginamic.entity.BirthPlace;
import fr.diginamic.filehandler.ReaderUtil;

public class ActorDao implements Dao<Actor> {

	private EntityManager em;
	private TypedQuery<Actor> querySelect;

	public ActorDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select a from Actor a where a.actorImdbId = :p1", Actor.class);
	}

	@Override
	public List<Actor> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor insertIfNotExistCustom(EntityManager em, Actor t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Actor insertIfNotExistCustom(EntityManager em, String actorImdbId, String nameOfActor,String dateOfBirthOfActor, BirthPlace birthPlace, String heightOfActor,
			String urlOfActor) {
		// TODO Auto-generated method stub
		Actor exActor = selectCustom(em, actorImdbId);

		if (exActor == null) {
			try {

				Float formatedHeightOfActor;
				if (heightOfActor != null) {
					formatedHeightOfActor = ReaderUtil.parseFloat(heightOfActor);
				} else {
					formatedHeightOfActor = null;
				}

				Date formatedDateOfBirthOfActor;
				if (dateOfBirthOfActor != null) {
					formatedDateOfBirthOfActor = ReaderUtil.parseDate(dateOfBirthOfActor);
				} else {
					formatedDateOfBirthOfActor = null;
				}

				Actor nwActor = new Actor(actorImdbId, nameOfActor, formatedDateOfBirthOfActor,
						birthPlace, formatedHeightOfActor, urlOfActor);

				em.persist(nwActor);
				return nwActor;
			} catch (Exception e) {
				// TODO: handle exception

				System.out.println(e.toString());
				return null;
			}
		}
		return exActor;
	}

	@Override
	public int updateCustom(EntityManager em, Actor t1, Actor t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Actor t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Actor selectCustom(EntityManager em, Actor t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Actor selectCustom(EntityManager em, String actorImdbId) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", actorImdbId);

		List<Actor> actor = querySelect.getResultList();
		if (actor.size() == 0) {
			return null;
		}
		return actor.get(0);
	}

}
