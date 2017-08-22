package fr.erdprt.samples.persistance.model.products.services.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.erdprt.samples.persistance.model.products.AbstractBaseTests;

public abstract  class AbstractTests extends AbstractBaseTests {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
