package fr.erdprt.samples.persistance.model.products.services.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.model.products.FactoryUtilities;
import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.services.business.RefCategoryService;
import fr.erdprt.samples.persistance.model.products.services.dao.AbstractTests;

@ContextConfiguration("/springcontext-test.xml")
public class RefCategoryServiceTest extends AbstractTests {

	@Autowired
	private RefCategoryService refCategoryService;
	
	/**
	 * Save entity list
	 */
	@Test
	public void save() {
		
		List<RefCategory> refCategories	=	new ArrayList<RefCategory>();
		String existingRefCategoryId	=	getRefCategoryId();
		RefCategory existingRefCategory	=	FactoryUtilities.createRefCategory(existingRefCategoryId);
		refCategories.add(existingRefCategory);
	
		String newRefCategoryId	=	getRefNewCategoryId();
		RefCategory newRefCategory	=	FactoryUtilities.createRefCategory(newRefCategoryId);
		refCategories.add(newRefCategory);
		LOGGER.debug("save category");
		this.refCategoryService.save(refCategories);
		
		Long count	=	this.refCategoryService.count();
		LOGGER.debug("count={}", count);
		Assert.assertEquals(Long.valueOf(5L), count);
		//List<RefCategory> existingRefCategories	=	this.refCategoryService.count();
		
	}
	
}
