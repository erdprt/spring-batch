package fr.erdprt.samples.springbatch.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class DummyItemReader implements ItemReader<String> {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(DummyItemReader.class);

	private Integer index	=	10;
	
	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		if (index>0) {
			LOGGER.debug("read item={}", index.toString());
			index--;
			return index.toString();
		} else {
			return null;
		}
	}

	
	
}
