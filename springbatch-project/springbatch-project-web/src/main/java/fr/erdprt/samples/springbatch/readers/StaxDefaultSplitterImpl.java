package fr.erdprt.samples.springbatch.readers;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;


public class StaxDefaultSplitterImpl<T> extends AbstractItemCountingItemStreamItemReader<T> 
	implements ResourceAwareItemReaderItemStream<T>, InitializingBean {

	private static Logger logger	=	LoggerFactory.getLogger(StaxDefaultSplitterImpl.class);
	
	private Resource resource;	
	
	private String elementName;

	private XMLEventReader xmlReader;
	
	private XMLEventWriter writer;
	
	XMLInputFactory xmlInputFactory		=	null;	
	XMLOutputFactory xmlOutputFactory	=	null;
	
	private Integer totalUnits	=	0;
	
	private Writer currentWriter	=	null;
	
	protected List<XMLEvent> commonElements	=	new ArrayList<XMLEvent>();
	
	public StaxDefaultSplitterImpl() {
		setName(ClassUtils.getShortName(StaxDefaultSplitterImpl.class));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void setResource(Resource resource) {
		this.resource	=	resource;
	}
	
	@Override
	protected void doOpen() throws Exception {
		init();
		
	}

	@Override
	protected void doClose() throws Exception {
		this.resource	=	null;
	}

	@Override
	protected T doRead() throws Exception {
		
		while (this.xmlReader.hasNext()) {
			XMLEvent event = this.xmlReader.nextEvent();
			
		    if (event.isStartElement()) {
		    	if (getElementName().equalsIgnoreCase(event.asStartElement().getName().getLocalPart())) {
		    		readElement(event.asStartElement());
		    		return (T)this.currentWriter.toString();
		    	} else if (this.totalUnits==0) {
		    		this.commonElements.add(event);
		    	}
		    } else if (event.isStartDocument()) {
		    	this.commonElements.add(event);
		    } else if (this.totalUnits==0) {
	    		this.commonElements.add(event);
		    }

		}	
		return null;
	}

	private void init() throws IOException, XMLStreamException {
		
		this.xmlInputFactory		=	XMLInputFactory.newInstance();
		this.xmlOutputFactory		=	XMLOutputFactory.newInstance();
		this.xmlReader				=	this.xmlInputFactory.createXMLEventReader(this.resource.getInputStream());
	}
	
	
	public void readElement(StartElement startElement) throws IOException, XMLStreamException {
		logger.debug("readElement:" + startElement.getName().getLocalPart());
		
		this.writer	=	null;
		try {
			writer	=	createXmlWriter();
			writer.add(startElement);
			
			while (this.xmlReader.hasNext()) {
				XMLEvent currentEvent = this.xmlReader.nextEvent();
				writer.add(currentEvent);
				
				if (currentEvent.isEndElement()) {		
					if (getElementName().equalsIgnoreCase(currentEvent.asEndElement().getName().getLocalPart())) {
						logger.trace("end for:" + currentEvent.asEndElement().getName().getLocalPart());
						this.totalUnits++;
						return;
					}
				}
			}
		} finally {
			closeWriter();
		}		
	}
	
	public void closeWriter() throws IOException, XMLStreamException {
		logger.trace("closeWriter");
		
		if (this.writer!=null) this.writer.close();
		this.writer	=	null;
	}
	
	public XMLEventWriter createXmlWriter() throws IOException, XMLStreamException {
		logger.trace("createXmlWriter");
		
		if (this.currentWriter!=null) {
			this.currentWriter.close();
		}
		this.currentWriter	=	new StringWriter();
		this.writer	=	this.xmlOutputFactory.createXMLEventWriter(this.currentWriter);
		for (XMLEvent event:this.commonElements) {
			this.writer.add(event);
		}
		return this.writer;
	}
	
	public String createFilename() {
		return UUID.randomUUID().toString();
	}
	
	public String getElementName() {
		return elementName;
	}
	
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	public XMLEventReader getXmlReader() {
		return xmlReader;
	}

	public void setXmlReader(XMLEventReader xmlReader) {
		this.xmlReader = xmlReader;
	}

}
