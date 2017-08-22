package fr.erdprt.samples.persistance.model.products;

import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.math3.random.RandomDataGenerator;

public class FactoryUtilities {

	
	public static RefProduct createRefProduct() {
		
		RandomDataGenerator generator	=	new RandomDataGenerator();
		Integer value	=	generator.nextInt(1, 100000);
		
		RefProduct refProduct	=	new RefProduct();
		refProduct.setLabel("LABEL FOR PRODUCT " + value);
		refProduct.setCode("code" + value);
		
		return refProduct;
	}
	
	
	public static RefCategory createRefCategory(String categoryId) {
		
		RandomDataGenerator generator	=	new RandomDataGenerator();
		
		RefCategory refCategory	=	new RefCategory();
		refCategory.setId(categoryId);
		refCategory.setLabel("LABEL FOR CATEGORY " + categoryId);
		refCategory.setDescription("DESCRIPTION FOR CATEGORY " + categoryId);
		
		return refCategory;
	}
	
	public static RefInventory createRefInventory() {
		
		RandomDataGenerator generator	=	new RandomDataGenerator();
		Integer value	=	generator.nextInt(1, 1000000);
		
		RefInventory refInventory	=	new RefInventory();
		refInventory.setAvailable(Boolean.TRUE);
		refInventory.setPrice(value *1.2);
		refInventory.setQuantity(value*2);
		return refInventory;
	}
	
	public static Person createPerson(String personId) {
		
		Person person	=	new Person();
		person.setFirstName("firstName" + personId);
		person.setLastName("lastName" + personId);
		person.setBirthDate(Calendar.getInstance());
		person.setNationality("N" + personId);
		person.setUuid(UUID.randomUUID().toString());
		person.setAddress(createAddress(personId));
		person.setCode("code" + personId);
		
		return person;
	}

	public static Address createAddress(String addressId) {
		
		Address address	=	new Address();
		address.setCity("city" + addressId);
		address.setStreetName("streetName" + addressId);
		address.setZipCode("zipCode" + addressId);
		
		return address;
	}

	public static Order createOrder(Long orderId, RefProduct refProduct) {
		
		Order order	=	new Order();
		//order.setPrice(1000.0);
		order.setMetaData(createMetaData(orderId));
		for (Integer index=0;index<orderId;index++) {
			Product product	=	createProduct(index.longValue(), refProduct);
			product.setOrder(order);
			order.addProduct(product);
		}
		return order;
	}

	
	public static Product createProduct(Long productId, RefProduct refProduct) {
		
		Product product	=	new Product();
		product.setPrice(100.00);
		product.setQuantity(10);
		product.setUuid(UUID.randomUUID().toString());
		product.setRefProduct(refProduct);
		
		return product;
	}
	
	public static MetaData createMetaData(Long metaDataId) {
		
		MetaData metaData	=	new MetaData();
		metaData.setContent("content" + metaDataId);
		metaData.setFrom("from" + metaDataId);
		metaData.setTo("to" + metaDataId);
		return metaData;
	}
	
}
