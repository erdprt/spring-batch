package fr.erdprt.samples.springbatch.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import fr.erdprt.samples.springbatch.utility.ShowMessage;

public class DummyMessageWriter implements ItemWriter<Object> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(DummyMessageWriter.class);
	
	private ShowMessage<Object> showMessage;
	
	@Override
	public void write(List<? extends Object> items) throws Exception {

		for (Object item:items) {
			LOGGER.info("write item={}", showMessage.show(item));
		}
		
	}

	public ShowMessage<Object> getShowMessage() {
		return showMessage;
	}

	public void setShowMessage(ShowMessage<Object> showMessage) {
		this.showMessage = showMessage;
	}
	
	

	
}
