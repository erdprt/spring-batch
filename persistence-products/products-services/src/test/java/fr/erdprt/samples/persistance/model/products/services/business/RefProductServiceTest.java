package fr.erdprt.samples.persistance.model.products.services.business;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.RefInventory;
import fr.erdprt.samples.persistance.model.products.RefProduct;
import fr.erdprt.samples.persistance.model.products.RefProductStatus;
import fr.erdprt.samples.persistance.model.products.services.dao.AbstractTests;

@ContextConfiguration("/springcontext-test.xml")
public class RefProductServiceTest extends AbstractTests {
	
	private static final Logger logger	=	LoggerFactory.getLogger(RefProductServiceTest.class);

	@Autowired
	private RefProductService refProductService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Save new entity
	 */
	@Test
	@Rollback(value=false)
	public void saveOrUpdateNew() {
		
		RefProduct refProduct	=	new RefProduct();
		refProduct.setCode("PROD100");
		refProduct.setLabel("LABEL FOR PROD100");
		
		refProduct.setRefCategory(new RefCategory());
		refProduct.getRefCategory().setId("CAT1");
		
		refProduct.setRefProductStatus(new RefProductStatus());
		refProduct.getRefProductStatus().setCode("ACTIVE");
		
		refProduct.setRefInventory(new RefInventory());
		refProduct.getRefInventory().setAvailable(true);
		refProduct.getRefInventory().setPrice(123.9);
		refProduct.getRefInventory().setQuantity(1000);
		
		refProductService.saveOrUpdate(refProduct);

		/*
		Map<String, Object> map	=	this.jdbcTemplate.queryForMap("select * from REF_PRODUCT where REF_PROD_CODE=?", new Object[] {"PROD100"});
		
		for (Iterator<String> keys=map.keySet().iterator();;keys.hasNext()) {
			String key	=	keys.next();
			LOGGER.info("key,value={},{}", new Object[] {key,keys.next()});
		}
		*/
	}

	/**
	 * Update existing entity values: testing cardinalities
	 */
	@Test
	@Rollback(value=false)
	public void saveOrUpdateExisting() {
		
		final String productLabel	=	"LABEL FOR PROD1 UPDATED";
		final String productStatus	=	"DEPRECATED";
		final Double invPrice		=	120.98;
		final Integer invQuantity	=	1000;
		
		RefProduct refProduct		=	new RefProduct();
		refProduct.setCode("PROD1");
		refProduct.setLabel(productLabel);
		
		// Try to change status
		refProduct.setRefProductStatus(new RefProductStatus());
		refProduct.getRefProductStatus().setCode(productStatus);
		
		refProduct.setRefInventory(new RefInventory());
		refProduct.getRefInventory().setAvailable(false);
		refProduct.getRefInventory().setPrice(invPrice);
		refProduct.getRefInventory().setQuantity(invQuantity);
		
		refProductService.saveOrUpdate(refProduct);

		Map<String, Object> map	=	this.jdbcTemplate.queryForMap("select A.*, B.*,C.* from REF_PRODUCT A, REF_PRODUCT_STATUS B, REF_INVENTORY C where A.REF_PROD_ID=C.REF_PROD_ID AND A.REF_PROD_STA_ID=B.REF_PROD_STA_ID AND REF_PROD_CODE=?", new Object[] {"PROD1"});
		
		Assert.assertNotNull(map);
		logger.info("show REF_PROD_LABEL={}",map.get("REF_PROD_LABEL"));
		logger.info("show REF_PROD_STA_CODE={}",map.get("REF_PROD_STA_CODE"));
		/*
		Assert.assertEquals(map.get("REF_PROD_LABEL"), productLabel);
		Assert.assertEquals(map.get("REF_PROD_STA_CODE"), productStatus );
		Assert.assertEquals(Double.valueOf(map.get("REF_INV_PRICE").toString()), invPrice );
		Assert.assertEquals(Integer.valueOf(map.get("REF_INV_QUANTITY").toString()), invQuantity );
		Assert.assertEquals(Boolean.valueOf(map.get("REF_INV_AVAILABLE").toString()).booleanValue(), false );
		*/
	}
	
}
