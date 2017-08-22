package fr.erdprt.samples.persistance.model.products;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration("/springcontext-test.xml")
@Transactional
public class PersonTest  extends AbstractTests {

	@Test
	@Rollback(value=false)
	public void persist() {
		LOGGER.debug("test persist={}", getEntityManager());

		Person entity	=	FactoryUtilities.createPerson("100");
		//getEntityManager().persist(entity.getAddress());
		getEntityManager().persist(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
		
	}


}
