package fr.erdprt.samples.persistance.model.products;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
