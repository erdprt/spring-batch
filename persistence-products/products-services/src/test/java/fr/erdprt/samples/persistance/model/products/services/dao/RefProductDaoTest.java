package fr.erdprt.samples.persistance.model.products.services.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.model.products.FactoryUtilities;
import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.RefProduct;
import fr.erdprt.samples.persistance.model.products.RefProductStatus;
import fr.erdprt.samples.persistance.model.products.services.dao.RefProductDao;

@ContextConfiguration("/springcontext-test.xml")
public class RefProductDaoTest extends AbstractTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(RefProductDaoTest.class);
	
	private RefCategory refCategory;
	private RefProductStatus refProductStatus;

	@Autowired
	private RefProductDao refProductDao;
	
	@BeforeClass
	public void setUp() {
		this.refCategory		=	getEntityManager().getReference(RefCategory.class, getRefCategoryId());
		this.refProductStatus	=	getEntityManager().getReference(RefProductStatus.class, getRefProductStatusId());
	}
	
	@Test
	public void findByStatus() {
		
		List<RefProduct> refProducts	=	this.refProductDao.findByStatus("ACTIVE");
		Assert.assertNotNull(refProducts);
		Assert.assertEquals(1, refProducts.size());
	}
	
	@Test
	public void save() {
		
		RefProduct entity	=	FactoryUtilities.createRefProduct();
		entity.setRefCategory(this.refCategory);
		entity.setRefProductStatus(this.refProductStatus);

		entity	=	this.refProductDao.save(entity);
		Assert.assertNotNull(entity.getId());
		LOGGER.debug("entity id={}", entity.getId());
	}
	
	
}
