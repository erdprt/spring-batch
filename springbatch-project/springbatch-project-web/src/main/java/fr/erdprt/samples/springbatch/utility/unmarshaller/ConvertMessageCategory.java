package fr.erdprt.samples.springbatch.utility.unmarshaller;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.erdprt.categorystructure.MessageType;
import org.erdprt.categorystructure.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertMessageCategory implements ConvertMessage<MessageType> {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(ConvertMessageCategory.class);

	private ObjectFactory factory;
	private JAXBContext jaxbContext;
	
	public ConvertMessageCategory() throws JAXBException {
		
		factory 			=	new ObjectFactory();
		this.jaxbContext 	= 	JAXBContext.newInstance("org.erdprt.categorystructure:org.erdprt.commonstructure",ObjectFactory.class.getClassLoader());

	}
	
	
	/**
	 * Unmarshall input xml to {@link MessageType}
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	@Override
	public MessageType convert(String xml) throws JAXBException {
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringBuffer xmlStr = new StringBuffer( xml );
		StreamSource source = new StreamSource( new StringReader( xmlStr.toString() ) );
		JAXBElement<MessageType> messageType =  unmarshaller.unmarshal(source, MessageType.class);
		MessageType message	=	messageType.getValue();
		return message;
	}
	
	
	
	
}
