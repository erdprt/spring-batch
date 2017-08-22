package fr.erdprt.samples.persistance.model.products;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;


public abstract class AbstractBaseTests extends AbstractTransactionalTestNGSpringContextTests {
	
	protected final static Logger LOGGER	=	LoggerFactory.getLogger(AbstractBaseTests.class);
	
	private static final String[] categories	=	new String[] {"CAT1","CAT2","CAT3","CAT4"};
	
	private static final Long[] productStatus	=	new Long[] {1L,2L};
	
	private static final Long[] product	=	new Long[] {1L};
	
	private static final Long[] person	=	new Long[] {1L};
	
	protected String getRefCategoryId() {
		RandomDataGenerator generator	=	new RandomDataGenerator();
		Integer value	=	generator.nextInt(0, 3);
		return AbstractBaseTests.categories[value];
	}

	protected String getRefNewCategoryId() {
		RandomDataGenerator generator	=	new RandomDataGenerator();
		Integer value	=	generator.nextInt(4, 10000);
		return "CAT" +value;
	}
	
	protected Long getRefProductStatusId() {
		RandomDataGenerator generator	=	new RandomDataGenerator();
		Integer value	=	generator.nextInt(0, 1);
		return AbstractBaseTests.productStatus[value];
	}
	
	protected Long getRefProductId() {
		return AbstractBaseTests.product[0];
	}
	
	protected Long getPersonId() {
		return AbstractBaseTests.person[0];
	}

}
