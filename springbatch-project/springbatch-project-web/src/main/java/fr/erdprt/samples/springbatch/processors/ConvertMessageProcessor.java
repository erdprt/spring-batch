package fr.erdprt.samples.springbatch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import fr.erdprt.samples.springbatch.utility.unmarshaller.ConvertMessage;

public class ConvertMessageProcessor implements ItemProcessor<String, Object> {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(ConvertMessageProcessor.class);
	
	private ConvertMessage<Object> convertMessage;

	@Override
	public Object process(String xml) throws Exception {
		LOGGER.trace("xml={}", xml);
		Object message	=	 convertMessage.convert(xml);
		LOGGER.trace("message={}", message);
		return message;
	}

	public ConvertMessage<Object> getConvertMessage() {
		return convertMessage;
	}

	public void setConvertMessage(ConvertMessage<Object> convertMessage) {
		this.convertMessage = convertMessage;
	}

}
