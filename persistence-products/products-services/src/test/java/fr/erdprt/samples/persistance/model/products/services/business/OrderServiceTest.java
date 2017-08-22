package fr.erdprt.samples.persistance.model.products.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.model.products.FactoryUtilities;
import fr.erdprt.samples.persistance.model.products.Order;
import fr.erdprt.samples.persistance.model.products.Person;
import fr.erdprt.samples.persistance.model.products.RefProduct;
import fr.erdprt.samples.persistance.model.products.services.dao.AbstractTests;

@ContextConfiguration("/springcontext-test.xml")
public class OrderServiceTest extends AbstractTests {
	
	@Autowired
	private OrderService orderService;
	
	private RefProduct refProduct;
	private Person person;
	
	/**
	 * Save new entity
	 */
	@Test
	@Transactional
	@Rollback(value=false)
	public void saveWithExistingPerson() {
		
		this.refProduct		=	getEntityManager().getReference(RefProduct.class, getRefProductId());
		this.person			=	getEntityManager().getReference(Person.class, getPersonId());

		Order entity			=	FactoryUtilities.createOrder(2L, refProduct);
		entity.setPerson(person);
		
		this.orderService.saveOrUpdate(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
	}

	@Test
	@Transactional
	@Rollback(value=false)
	public void saveWithNewPerson() {
		
		this.refProduct		=	getEntityManager().getReference(RefProduct.class, getRefProductId());

		Person newPerson	=	FactoryUtilities.createPerson("2");
		
		Order entity			=	FactoryUtilities.createOrder(2L, refProduct);
		entity.setPerson(newPerson);
		
		this.orderService.saveOrUpdate(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());

	}
	
}
