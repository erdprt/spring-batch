package fr.erdprt.samples.springbatch.processors.inventory;

import java.util.Calendar;

import org.erdprt.commonstructure.MetaDataType;
import org.erdprt.commonstructure.ProductType;
import org.erdprt.commonstructure.ProductsType;
import org.erdprt.commonstructure.StatusEnum;
import org.erdprt.inventorystructure.BodyType;
import org.erdprt.inventorystructure.InventoryType;
import org.erdprt.inventorystructure.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import fr.erdprt.samples.persistance.model.products.RefInventory;

public class InventoryExportProcessor implements ItemProcessor<RefInventory, MessageType> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(InventoryExportProcessor.class);
	
	@Override
	public MessageType process(RefInventory refInventory) throws Exception {
		LOGGER.info("process id {}", refInventory.getId());
		
		MessageType messageType	=	new MessageType();
		messageType.setBody(new BodyType());
		messageType.setMetaData(new MetaDataType());
		
		messageType.getMetaData().setContentType("content");
		messageType.getMetaData().setFrom("from");
		messageType.getMetaData().setNature("nature");
		messageType.getMetaData().setTo("to");
		
		InventoryType inventoryType	=	new InventoryType();
		messageType.getBody().setInventory(new InventoryType());
		messageType.getBody().getInventory().setProducts(new ProductsType());
		
		ProductType productType	=	new ProductType();
		productType.setAvailable(refInventory.getAvailable());
		productType.setCategory(refInventory.getRefProduct().getRefCategory().getId());
		productType.setCode(refInventory.getRefProduct().getCode());
		
		Calendar calendar	=	Calendar.getInstance();
		calendar.setTimeInMillis(refInventory.getRefProduct().getTechnicalColumns().getCreationTime().getTime());
		productType.setDate(calendar);
		
		productType.setId(refInventory.getRefProduct().getId().toString());
		productType.setLabel(refInventory.getRefProduct().getLabel());
		productType.setPrice(refInventory.getPrice());
		productType.setQuantity(refInventory.getQuantity());
		StatusEnum statusEnum	=	StatusEnum.valueOf(refInventory.getRefProduct().getRefProductStatus().getCode());
		productType.setStatus(statusEnum);
		
		messageType.getBody().getInventory().getProducts().getProduct().add(productType);
		
		return messageType;
	}

	
	
}
