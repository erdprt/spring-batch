package fr.erdprt.samples.springbatch.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.erdprt.commonstructure.ProductType;
import org.erdprt.inventorystructure.MessageType;

public class ShowInventoryMessage implements ShowMessage<MessageType> {

	@Override
	public String show(MessageType message) {
		
		StringBuilder builder	=	new StringBuilder();
		builder.append("{MetaData=(from=").append(message.getMetaData().getFrom()).
				append("),(to=").append(message.getMetaData().getTo()).
				append("),(contentType=").append(message.getMetaData().getContentType()).
				append(")}");
		
		if (message.getBody().getInventory().getProducts()!=null) {
			for (ProductType product:message.getBody().getInventory().getProducts().getProduct()) {
				builder.append(showProduct(product));
			}
		}
		
		return builder.toString();
	}

	
	public String showProduct(ProductType productType) {
		
		StringBuilder builder	=	new StringBuilder();
		builder.append("{product=(category=").append(productType.getCategory()).
						append(")(code=").append(productType.getCode()).
						append(")(href=").append(productType.getHref()).
						append(")(id=").append(productType.getId()).
						append(")(date=").append(formatDate(productType.getDate())).
						append(")(price=").append(productType.getPrice()).
						append(")(quantity=").append(productType.getQuantity()).
						append(")}");
		return builder.toString();
	}
	
	public String formatDate(Calendar calendar) {
		if (calendar!=null) {
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(calendar.getTime());
		} 
		return "null";
	}
	
}
