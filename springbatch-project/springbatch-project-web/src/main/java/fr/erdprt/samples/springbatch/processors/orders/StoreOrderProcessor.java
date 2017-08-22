package fr.erdprt.samples.springbatch.processors.orders;

import org.erdprt.orderstructure.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import fr.erdprt.samples.persistance.model.products.Order;
import fr.erdprt.samples.persistance.model.products.services.business.OrderService;
import fr.erdprt.samples.springbatch.utility.converter.EntityConverter;


public class StoreOrderProcessor implements ItemProcessor<MessageType, Order> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(StoreOrderProcessor.class);

	@Autowired
	private OrderService orderService;
	
	private EntityConverter<Order, MessageType> entityConverter;
	
	
	@Override
	public Order process(MessageType messageType) throws Exception {
		LOGGER.trace("Processing Order");
		
		Order order	=	this.entityConverter.convert(messageType);
		order		=	this.orderService.saveOrUpdate(order);
		return order;
	}


	public EntityConverter<Order, MessageType> getEntityConverter() {
		return entityConverter;
	}


	public void setEntityConverter(
			EntityConverter<Order, MessageType> entityConverter) {
		this.entityConverter = entityConverter;
	}
	
	

	
	
}
