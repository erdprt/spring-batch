package fr.erdprt.samples.springbatch.utility.unmarshaller;

import javax.xml.bind.JAXBException;

public interface ConvertMessage<T> {

	public T convert(String xml) throws JAXBException;
}
