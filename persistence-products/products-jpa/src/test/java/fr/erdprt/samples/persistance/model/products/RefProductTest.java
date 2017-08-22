package fr.erdprt.samples.persistance.model.products;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration("/springcontext-test.xml")
@Transactional
public class RefProductTest extends AbstractTests {
	
	private RefCategory refCategory;
	private RefProductStatus refProductStatus;
	
	@BeforeClass
	public void setUp() {
		this.refCategory		=	getEntityManager().getReference(RefCategory.class, getRefCategoryId());
		this.refProductStatus	=	getEntityManager().getReference(RefProductStatus.class, getRefProductStatusId());
	}

	
	@Test
	@Rollback(value=false)
	public void persist() {
		LOGGER.debug("test persist={}", getEntityManager());
		
		RefProduct entity	=	FactoryUtilities.createRefProduct();
		entity.setRefCategory(this.refCategory);
		entity.setRefProductStatus(this.refProductStatus);
		
		getEntityManager().persist(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
		
	}
}
