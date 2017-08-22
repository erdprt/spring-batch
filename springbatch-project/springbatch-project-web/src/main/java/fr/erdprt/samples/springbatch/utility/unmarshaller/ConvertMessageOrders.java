package fr.erdprt.samples.springbatch.utility.unmarshaller;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.erdprt.orderstructure.MessageType;
import org.erdprt.orderstructure.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertMessageOrders implements ConvertMessage<MessageType> {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(ConvertMessageOrders.class);

	private JAXBContext jaxbContext;
	
	public ConvertMessageOrders() throws JAXBException {
		
		this.jaxbContext 	= 	JAXBContext.newInstance("org.erdprt.orderstructure:org.erdprt.commonstructure",ObjectFactory.class.getClassLoader());
	}
	
	
	/**
	 * Unmarshall input xml to {@link MessageType}
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	@Override
	public MessageType convert(String xml) throws JAXBException {
		LOGGER.trace("convert message={}", xml);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringBuffer xmlStr = new StringBuffer( xml );
		StreamSource source = new StreamSource( new StringReader( xmlStr.toString() ) );
		JAXBElement<MessageType> messageType =  unmarshaller.unmarshal(source, MessageType.class);
		MessageType message	=	messageType.getValue();
		
		LOGGER.trace("messageType metaData={}", message.getMetaData());
		LOGGER.trace("messageType body={}", message.getBody());
		LOGGER.trace("messageType orders={}", message.getBody().getOrders());
		LOGGER.trace("messageType orders={}", message.getBody().getOrders().getPerson());
		LOGGER.trace("messageType orders={}", message.getBody().getOrders().getPerson().get(0));
		LOGGER.trace("messageType orders={}", message.getBody().getOrders().getPerson().get(0).getAddress());
		
		return message;
	}
	
	
	
	
}
