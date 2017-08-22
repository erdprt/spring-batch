package fr.erdprt.samples.springbatch.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class DummyObjectWriter implements ItemWriter<Object> {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(DummyObjectWriter.class);
	
	@Override
	public void write(List<? extends Object> items) throws Exception {

		Integer count	=	0;
		for (Object item:items) {
			count++;
			LOGGER.info("write (item,index)=({},{})", new Object[] {item, count});
		}
		
	}

	
}
