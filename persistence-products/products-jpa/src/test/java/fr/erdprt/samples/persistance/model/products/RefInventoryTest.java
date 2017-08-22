package fr.erdprt.samples.persistance.model.products;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration("/springcontext-test.xml")
@Transactional
public class RefInventoryTest extends AbstractTests {
	
	private RefProduct refProduct;
	
	@BeforeClass
	public void setUp() {
		this.refProduct		=	getEntityManager().getReference(RefProduct.class, getRefProductId());
	}

	
	@Test
	@Rollback(value=false)
	public void persist() {
		LOGGER.debug("test persist={}", getEntityManager());
		
		RefInventory entity	=	FactoryUtilities.createRefInventory();
		entity.setRefProduct(this.refProduct);
		
		getEntityManager().persist(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
		
	}
}
