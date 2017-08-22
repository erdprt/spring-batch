package fr.erdprt.samples.springbatch.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class SynchronizingItemReader implements ItemReader<String>, ItemStream {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(SynchronizingItemReader.class);

	private ItemReader<String> delegate;
	
	@Override
	public void open(ExecutionContext executionContext)
			throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).open(executionContext);
			}
	}

	@Override
	public void update(ExecutionContext executionContext)
			throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).update(executionContext);
			}
	}

	@Override
	public void close() throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).close();
			}
	}

	@Override
	public synchronized String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return this.delegate.read();
	}

	public ItemReader<String> getDelegate() {
		return delegate;
	}

	public void setDelegate(ItemReader<String> delegate) {
		this.delegate = delegate;
	}
	
	


}
