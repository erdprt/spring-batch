package fr.erdprt.samples.persistance.model.products;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration("/springcontext-test.xml")
@Transactional
public class OrderTest extends AbstractTests {

	private RefProduct refProduct;
	private Person person;
	
	@BeforeClass
	public void setUp() {
	}
	
	@Test
	@Rollback(value=false)
	public void persist() {
		LOGGER.debug("test persist={}", getEntityManager());

		this.refProduct		=	getEntityManager().getReference(RefProduct.class, getRefProductId());
		this.person			=	getEntityManager().getReference(Person.class, getPersonId());
		
		Order entity	=	FactoryUtilities.createOrder(2L, this.refProduct);
		entity.setPrice(Double.valueOf(123));
		entity.setPerson(this.person);
		getEntityManager().persist(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
		
	}

}
