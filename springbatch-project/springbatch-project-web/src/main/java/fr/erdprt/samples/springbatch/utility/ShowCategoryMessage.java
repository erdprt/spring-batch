package fr.erdprt.samples.springbatch.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.erdprt.categorystructure.CategoryType;
import org.erdprt.categorystructure.MessageType;

public class ShowCategoryMessage implements ShowMessage<MessageType> {

	@Override
	public String show(MessageType message) {
		
		StringBuilder builder	=	new StringBuilder();
		builder.append("{MetaData=(from=").append(message.getMetaData().getFrom()).
				append("),(to=").append(message.getMetaData().getTo()).
				append("),(contentType=").append(message.getMetaData().getContentType()).
				append(")}");
		
		if (message.getBody().getCategories().getCategory()!=null) {
			for (CategoryType category:message.getBody().getCategories().getCategory()) {
				builder.append(showCategory(category));
			}
		}
		
		return builder.toString();
	}

	
	public String showCategory(CategoryType categoryType) {
		
		StringBuilder builder	=	new StringBuilder();
		builder.append("{category=(id=").append(categoryType.getId()).
						append(")(label=").append(categoryType.getLabel()).
						append(")(description=").append(categoryType.getDescription()).
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
