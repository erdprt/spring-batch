package fr.erdprt.samples.springbatch.processors.category;

import org.erdprt.categorystructure.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.services.business.RefCategoryService;
import fr.erdprt.samples.springbatch.utility.converter.EntityConverter;

public class CategoryProcessor implements ItemProcessor<MessageType, RefCategory> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(CategoryProcessor.class);
	
	@Autowired
	private RefCategoryService refCategoryService;
	
	private EntityConverter<RefCategory, MessageType> entityConverter;
	
	@Override
	public RefCategory process(MessageType message) throws Exception {
		
		LOGGER.trace("Processing refCategory");
		RefCategory refCategory	=	this.entityConverter.convert(message);
		LOGGER.debug("refCategory={}", refCategory);
		refCategory				=	this.refCategoryService.save(refCategory);
		
		return refCategory;
	}

	public EntityConverter<RefCategory, MessageType> getEntityConverter() {
		return entityConverter;
	}

	public void setEntityConverter(
			EntityConverter<RefCategory, MessageType> entityConverter) {
		this.entityConverter = entityConverter;
	}
	
}
