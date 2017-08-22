package fr.erdprt.samples.springbatch.processors.inventory;

import org.erdprt.inventorystructure.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import fr.erdprt.samples.persistance.model.products.RefProduct;
import fr.erdprt.samples.persistance.model.products.services.business.RefProductService;
import fr.erdprt.samples.springbatch.utility.converter.EntityConverter;

public class InventoryProcessor implements ItemProcessor<MessageType, RefProduct> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(InventoryProcessor.class);

	@Autowired
	private RefProductService refProductService;
	
	private EntityConverter<RefProduct, MessageType> entityConverter;
	
	@Override
	public RefProduct process(MessageType message) throws Exception {
		LOGGER.trace("Processing RefProduct");
		RefProduct refProduct	=	this.entityConverter.convert(message);
		refProduct				=	this.refProductService.saveOrUpdate(refProduct);	
		return refProduct;
	}

	public EntityConverter<RefProduct, MessageType> getEntityConverter() {
		return entityConverter;
	}

	public void setEntityConverter(
			EntityConverter<RefProduct, MessageType> entityConverter) {
		this.entityConverter = entityConverter;
	}
	
	
	
}
