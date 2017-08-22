package fr.erdprt.samples.persistance.model.products.services.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.services.dao.RefCategoryDao;

@Service
public class RefCategoryService {

	@Autowired
	private RefCategoryDao refCategoryDao;
	
	/**
	 * Save or update the input list of entities
	 * @param categories
	 * @return
	 */
	public List<RefCategory> save(List<RefCategory> categories) {
		this.refCategoryDao.save(categories);
		return categories;
	}

	@Transactional
	public RefCategory save(RefCategory refCategory) {
		return this.refCategoryDao.save(refCategory);
	}
	
	/**
	 * Retunr the number of entities
	 * @return Long
	 */
	public Long count() {
		return this.refCategoryDao.count();
	}
	
}
