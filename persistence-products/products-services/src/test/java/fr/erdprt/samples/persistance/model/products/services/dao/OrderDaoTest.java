package fr.erdprt.samples.persistance.model.products.services.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.model.products.FactoryUtilities;
import fr.erdprt.samples.persistance.model.products.Order;
import fr.erdprt.samples.persistance.model.products.Person;
import fr.erdprt.samples.persistance.model.products.RefProduct;

@ContextConfiguration("/springcontext-test.xml")
public class OrderDaoTest extends AbstractTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(OrderDaoTest.class);
	
	@Autowired
	private OrderDao orderDao;
	
	private RefProduct refProduct;
	private Person person;
	
	
	@Test
	@Rollback(value=false)
	public void save() {
		
		this.refProduct		=	getEntityManager().getReference(RefProduct.class, getRefProductId());
		this.person			=	getEntityManager().getReference(Person.class, getPersonId());
		
		Order entity	=	FactoryUtilities.createOrder(2L, refProduct);
		entity.setPrice(Double.valueOf(1200));
		entity.setPerson(person);
		entity	=	this.orderDao.save(entity);
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
	}

	
}
