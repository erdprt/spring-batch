package fr.erdprt.samples.springbatch.utility.converter;

public interface EntityConverter<T, P> {

	public T convert(P message);
	
	public Boolean messageBodyIsValid(P message);
}
