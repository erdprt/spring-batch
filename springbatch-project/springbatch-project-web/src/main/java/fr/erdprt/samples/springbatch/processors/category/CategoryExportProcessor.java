package fr.erdprt.samples.springbatch.processors.category;

import org.erdprt.categorystructure.BodyType;
import org.erdprt.categorystructure.CategoriesType;
import org.erdprt.categorystructure.CategoryType;
import org.erdprt.categorystructure.MessageType;
import org.erdprt.commonstructure.MetaDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import fr.erdprt.samples.persistance.model.products.RefCategory;

public class CategoryExportProcessor implements ItemProcessor<RefCategory, MessageType> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(CategoryExportProcessor.class);
	
	@Override
	public MessageType process(RefCategory refCategory) throws Exception {
		LOGGER.info("process id {}", refCategory.getId());
		
		MessageType messageType	=	new MessageType();
		messageType.setBody(new BodyType());
		messageType.setMetaData(new MetaDataType());
		messageType.getBody().setCategories(new CategoriesType());
		messageType.getBody().getCategories();
		
		messageType.getMetaData().setContentType("content");
		messageType.getMetaData().setFrom("from");
		messageType.getMetaData().setNature("nature");
		messageType.getMetaData().setTo("to");
		
		CategoryType categoryType	=	new CategoryType();
		categoryType.setDescription(refCategory.getDescription());
		categoryType.setId(refCategory.getId());
		categoryType.setLabel(refCategory.getLabel());
		
		messageType.getBody().getCategories().getCategory().add(categoryType);
		
		return messageType;
	}

	
	
}
