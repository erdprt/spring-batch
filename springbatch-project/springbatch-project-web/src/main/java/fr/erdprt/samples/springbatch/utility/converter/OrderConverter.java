package fr.erdprt.samples.springbatch.utility.converter;

import java.util.HashSet;

import org.erdprt.commonstructure.MetaDataType;
import org.erdprt.commonstructure.ProductType;
import org.erdprt.orderstructure.MessageType;
import org.erdprt.orderstructure.PersonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.erdprt.samples.persistance.model.products.Address;
import fr.erdprt.samples.persistance.model.products.MetaData;
import fr.erdprt.samples.persistance.model.products.Order;
import fr.erdprt.samples.persistance.model.products.Person;
import fr.erdprt.samples.persistance.model.products.Product;
import fr.erdprt.samples.persistance.model.products.RefProduct;

/**
 * Conversion from {@link #MessageType} to {@link #Order}
 * Suppose {@link #MessageType} contains only one product (split before if necessary)
 * @author erdprt
 *
 */
public class OrderConverter implements EntityConverter<Order, MessageType> {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(OrderConverter.class);

	@Override
	public Order convert(MessageType message) {
		if (messageBodyIsValid(message)) {
			Order order	=	createOrder();
			convertMetaData(order, message);
			convertPerson(order, message);
			convertProducts(order, message);
			return order;
		}
		return null;
	}

	@Override
	public Boolean messageBodyIsValid(MessageType message) {
		if ((message!=null) &&
				(message.getBody()!=null) &&
				(message.getBody().getOrders()!=null) &&
				(message.getBody().getOrders().getPerson().size()>0) ) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private Order createOrder() {
		Order order	=	new Order();
		order.setPerson(new Person());
		order.getPerson().setAddress(new Address());
		order.setMetaData(new MetaData());
		order.setProducts(new HashSet<Product>());
		return order;
	}
	
	private void convertMetaData(Order order,MessageType message) {
		
		MetaDataType metaDataType	=	message.getMetaData();
		order.getMetaData().setContent(metaDataType.getContentType());
		order.getMetaData().setFrom(metaDataType.getFrom());
		order.getMetaData().setTo(metaDataType.getTo());
	}
	
	private void convertPerson(Order order,MessageType message) {
		
		PersonType personType	=	message.getBody().getOrders().getPerson().get(0);
		order.getPerson().setCode(personType.getCode());
		order.getPerson().setBirthDate(personType.getCivilStatus().getBirthDate());
		order.getPerson().setFirstName(personType.getCivilStatus().getFirstName());
		order.getPerson().setLastName(personType.getCivilStatus().getLastName());
		order.getPerson().setNationality(personType.getCivilStatus().getNationality());
		order.getPerson().setUuid(personType.getId());
		order.getPerson().getAddress().setCity(personType.getAddress().getCity());
		order.getPerson().getAddress().setStreetName(personType.getAddress().getStreet());
		order.getPerson().getAddress().setZipCode(personType.getAddress().getZipCode());
	}

	private void convertProducts(Order order,MessageType message) {

		for (ProductType productType:message.getBody().getOrders().getPerson().get(0).getProducts().getProduct()) {
			convertProduct(order, productType);
		}
	}

	private void convertProduct(Order order,ProductType productType) {

		Product product	=	new Product();
		product.setPrice(productType.getPrice());
		product.setQuantity(productType.getQuantity());
		product.setUuid(productType.getId());
		product.setRefProduct(new RefProduct());
		product.getRefProduct().setCode(productType.getCode());
		product.setOrder(order);
		order.getProducts().add(product);
		
	}
	
}
