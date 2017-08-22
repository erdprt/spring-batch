package fr.erdprt.samples.persistance.model.products.services.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.model.products.FactoryUtilities;
import fr.erdprt.samples.persistance.model.products.Person;

@ContextConfiguration("/springcontext-test.xml")
public class PersonDaoTest extends AbstractTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(PersonDaoTest.class);
	
	@Autowired
	private PersonDao personDao;
	
	@Test
	public void findByCode() {
		
		String personCode	=	"PERS1";
		Person person	=	this.personDao.findByCode(personCode);
		LOGGER.debug("result={}", person);
		
		Assert.assertNotNull(person);
		Assert.assertEquals(person.getCode(), personCode);
		Assert.assertNotNull(person.getAddress());
	}
	
	@Test
	@Rollback(value=false)
	public void save() {
		
		Person entity	=	FactoryUtilities.createPerson("10");
		entity	=	this.personDao.save(entity);
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
	}

	
}
