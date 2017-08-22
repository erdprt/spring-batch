package fr.erdprt.samples.springbatch.utility.converter;

import org.erdprt.categorystructure.CategoryType;
import org.erdprt.categorystructure.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.erdprt.samples.persistance.model.products.RefCategory;

public class RefCategoryConverter implements EntityConverter<RefCategory, MessageType> {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(RefCategoryConverter.class);

	@Override
	public RefCategory convert(MessageType message) {
		if (messageBodyIsValid(message)) {
			CategoryType messageCategory	=	message.getBody().getCategories().getCategory().get(0);
			RefCategory refCategory	=	new RefCategory();
			refCategory.setId(messageCategory.getId());
			refCategory.setDescription(messageCategory.getDescription());
			refCategory.setLabel(messageCategory.getLabel());
			return refCategory;
		}
		return null;
	}

	@Override
	public Boolean messageBodyIsValid(MessageType message) {
		if ((message!=null) &&
				(message.getBody()!=null) &&
				(message.getBody().getCategories()!=null) &&
				(message.getBody().getCategories().getCategory().size()>0) ) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
}
