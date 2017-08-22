                                              package fr.erdprt.samples.persistance.model.products.services.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.RefInventory;
import fr.erdprt.samples.persistance.model.products.RefProduct;
import fr.erdprt.samples.persistance.model.products.RefProductStatus;
import fr.erdprt.samples.persistance.model.products.services.dao.RefCategoryDao;
import fr.erdprt.samples.persistance.model.products.services.dao.RefInventoryDao;
import fr.erdprt.samples.persistance.model.products.services.dao.RefProductDao;
import fr.erdprt.samples.persistance.model.products.services.dao.RefProductStatusDao;

@Service
public class RefProductService {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(RefProductService.class);

	@Autowired
	private RefProductDao refProductDao;
	
	@Autowired
	private RefCategoryDao refCategoryDao;
	
	@Autowired
	private RefProductStatusDao refProductStatusDao;

	@Autowired
	private RefInventoryDao refInventoryDao;
	
	@Transactional
	public RefProduct saveOrUpdate(RefProduct refProduct) {
		
		LOGGER.trace("saveOrUpdate product code={}", refProduct.getCode());
		RefProduct existingRefProduct	=	this.refProductDao.findByCode(refProduct.getCode());
		if (existingRefProduct==null) {
			LOGGER.trace("existing RefProduct for code={} is null", refProduct.getCode());
		} else {
			LOGGER.trace("existing RefProduct for code={} is not null", refProduct.getCode());
			if (existingRefProduct.getRefInventory()==null) {
				LOGGER.trace("existing RefInventory for code={} is null", refProduct.getCode());
			}
		}
		
		if (existingRefProduct!=null) {
			// Update category if changed
			if ((refProduct.getLabel()!=null) && 
						(!refProduct.getLabel().equalsIgnoreCase(existingRefProduct.getLabel()))) {
				existingRefProduct.setLabel(refProduct.getLabel());
			}
			
			// Update category if changed
			if ((refProduct.getRefCategory()!=null) && 
						(refProduct.getRefCategory().getId()!=null) &&
						(!refProduct.getRefCategory().getId().equalsIgnoreCase(existingRefProduct.getRefCategory().getId()))) {
				RefCategory refCategory	=	this.refCategoryDao.findOne(refProduct.getRefCategory().getId());
				existingRefProduct.setRefCategory(refCategory);
			}
			// Update status if changed
			if ((refProduct.getRefProductStatus()!=null) && 
					(refProduct.getRefProductStatus().getCode()!=null) &&
					(!refProduct.getRefProductStatus().getCode().equalsIgnoreCase(existingRefProduct.getRefProductStatus().getCode()))) {
			RefProductStatus refProductStatus	=	this.refProductStatusDao.findByCode(refProduct.getRefProductStatus().getCode());
			existingRefProduct.setRefProductStatus(refProductStatus);
			}
			if (existingRefProduct.getRefInventory()!=null) {
				// Update inventory
				if ((refProduct.getRefInventory()!=null) && refProduct.getRefInventory().getAvailable()!=null) {
					existingRefProduct.getRefInventory().setAvailable(refProduct.getRefInventory().getAvailable());
				}
				if ((refProduct.getRefInventory()!=null) && refProduct.getRefInventory().getPrice()!=null) {
					existingRefProduct.getRefInventory().setPrice(refProduct.getRefInventory().getPrice());
				}
				if ((refProduct.getRefInventory()!=null) && refProduct.getRefInventory().getQuantity()!=null) {
					existingRefProduct.getRefInventory().setQuantity(refProduct.getRefInventory().getQuantity());
				}
				this.refProductDao.save(existingRefProduct);
			} else {
				// Special Case: same product appears twice, in the same transactional context, so first is not committed 
				// and getRefInventory is null
				RefInventory refInventory	=	refProduct.getRefInventory();	
				refInventory.setRefProduct(existingRefProduct);
				this.refInventoryDao.save(refInventory);
				existingRefProduct.setRefInventory(refInventory);				
			}
			this.refProductDao.save(existingRefProduct);
			return existingRefProduct;
		} else {
			RefCategory refCategory	=	this.refCategoryDao.findOne(refProduct.getRefCategory().getId());
			refProduct.setRefCategory(refCategory);
			RefProductStatus refProductStatus	=	this.refProductStatusDao.findByCode(refProduct.getRefProductStatus().getCode());
			refProduct.setRefProductStatus(refProductStatus);
			
			RefInventory refInventory	=	refProduct.getRefInventory();
			refProduct.setRefInventory(null);
			this.refProductDao.save(refProduct);
			refInventory.setRefProduct(refProduct);
			this.refInventoryDao.save(refInventory);
			return refProduct;
		}
	}
	
	public RefProduct findByCode(String code) {
		return this.refProductDao.findByCode(code);
	}
	
}
