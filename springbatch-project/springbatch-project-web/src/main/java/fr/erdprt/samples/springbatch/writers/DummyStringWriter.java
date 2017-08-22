package fr.erdprt.samples.springbatch.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class DummyStringWriter implements ItemWriter<String> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(DummyStringWriter.class);
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		LOGGER.trace("write {} items", items.size());
		for (String item:items) {
			LOGGER.info("write item={}", item);
		}
		
	}

	
}
