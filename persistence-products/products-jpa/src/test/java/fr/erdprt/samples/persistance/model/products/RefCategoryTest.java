package fr.erdprt.samples.persistance.model.products;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.database.H2ScriptUtilityException;

@ContextConfiguration("/springcontext-test.xml")
@Transactional
public class RefCategoryTest extends AbstractTests {

	@AfterTest
	public void tearDown() throws H2ScriptUtilityException {
		LOGGER.debug("tearDown:dump database");
	}
	
	@Test
	@Rollback(value=false)
	public void persist() {
		LOGGER.debug("test persist={}", getEntityManager());
		String categoryId	=	getRefNewCategoryId();
		RefCategory entity	=	FactoryUtilities.createRefCategory(categoryId);
		getEntityManager().persist(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
		
	}
}
