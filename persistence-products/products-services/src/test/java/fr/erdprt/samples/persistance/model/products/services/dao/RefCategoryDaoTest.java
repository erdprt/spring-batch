package fr.erdprt.samples.persistance.model.products.services.dao;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.model.products.FactoryUtilities;
import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.services.dao.RefCategoryDao;

@ContextConfiguration("/springcontext-test.xml")
public class RefCategoryDaoTest extends AbstractTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(RefCategoryDaoTest.class);
	
	@Autowired
	private RefCategoryDao refCategoryDao;
	
	@Test
	public void findAll() {
		
		Iterable<RefCategory> refIterable	=	this.refCategoryDao.findAll();
		LOGGER.debug("result={}", refIterable);
		Iterator<RefCategory> iterator	=	refIterable.iterator();
		while (iterator.hasNext()) {
			RefCategory refCategory	=	iterator.next();
			LOGGER.debug("result={}", refCategory.getId());
		}
	}
	
	@Test
	public void saveNewCategory() {
		String categoryId	=	getRefNewCategoryId();
		RefCategory entity	=	FactoryUtilities.createRefCategory(categoryId);
		entity	=	this.refCategoryDao.save(entity);
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
	}

	@Test
	public void saveExistingCategory() {
		
		String categoryId				=	getRefCategoryId();
		RefCategory existingRefCategory	=	this.refCategoryDao.findOne(categoryId);
		// Extract label and description for comparing further: instead,  existingRefCategory will be synchronized with new values after calling save above  
		String existingLabel			=	existingRefCategory.getLabel();
		String existingDescription		=	existingRefCategory.getDescription();
		
		LOGGER.debug("existing entity id={}", existingRefCategory.getId());
		Assert.assertNotNull(existingRefCategory);
		
		// Create a new RefCategory: new label, description.
		String newCategoryId	=	getRefNewCategoryId();
		RefCategory entity	=	FactoryUtilities.createRefCategory(newCategoryId);
		// get Existing id
		entity.setId(categoryId);
		entity	=	this.refCategoryDao.save(entity);
		
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
		Assert.assertEquals(existingRefCategory.getId(), entity.getId());
		Assert.assertNotEquals(existingDescription, entity.getDescription());
		Assert.assertNotEquals(existingLabel, entity.getLabel());
		
	}
	
}
