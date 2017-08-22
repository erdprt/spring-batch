package fr.erdprt.samples.springbatch.utility.converter;

import org.erdprt.commonstructure.ProductType;
import org.erdprt.inventorystructure.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.erdprt.samples.persistance.model.products.RefCategory;
import fr.erdprt.samples.persistance.model.products.RefInventory;
import fr.erdprt.samples.persistance.model.products.RefProduct;
import fr.erdprt.samples.persistance.model.products.RefProductStatus;

/**
 * Conversion from {@link #MessageType} to {@link #RefProduct}
 * Suppose {@link #MessageType} contains only one product (split before if necessary)
 * @author erdprt
 *
 */
public class RefProductConverter implements EntityConverter<RefProduct, MessageType> {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(RefProductConverter.class);

	@Override
	public RefProduct convert(MessageType message) {
		if (messageBodyIsValid(message)) {
			ProductType product	=	message.getBody().getInventory().getProducts().getProduct().get(0);
			RefProduct refProduct	=	new RefProduct();
			refProduct.setCode(product.getCode());
			refProduct.setLabel(product.getLabel());
			refProduct.setRefCategory(new RefCategory());
			refProduct.getRefCategory().setId(product.getCategory());
			refProduct.setRefInventory(new RefInventory());
			refProduct.getRefInventory().setPrice(product.getPrice());
			refProduct.getRefInventory().setQuantity(product.getQuantity());
			refProduct.getRefInventory().setAvailable(product.isAvailable());
			refProduct.setRefProductStatus(new RefProductStatus());
			refProduct.getRefProductStatus().setCode(product.getStatus().value());
			return refProduct;
		}
		return null;
	}

	@Override
	public Boolean messageBodyIsValid(MessageType message) {
		if ((message!=null) &&
				(message.getBody()!=null) &&
				(message.getBody().getInventory().getProducts()!=null) &&
				(message.getBody().getInventory().getProducts().getProduct().size()>0) ) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
}
