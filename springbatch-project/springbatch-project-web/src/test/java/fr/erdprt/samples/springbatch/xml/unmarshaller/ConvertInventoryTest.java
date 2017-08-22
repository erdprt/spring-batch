package fr.erdprt.samples.springbatch.xml.unmarshaller;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.erdprt.inventorystructure.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import fr.erdprt.samples.springbatch.utilities.IOHelpers;
import fr.erdprt.samples.springbatch.utility.ShowInventoryMessage;
import fr.erdprt.samples.springbatch.utility.unmarshaller.ConvertMessageInventory;

public class ConvertInventoryTest {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(ConvertInventoryTest.class);

	private ConvertMessageInventory convertInventory;
	private ShowInventoryMessage showInventoryMessage;
	
	@BeforeTest
	public void setUp() throws JAXBException {
		this.convertInventory	=	new ConvertMessageInventory();
		this.showInventoryMessage	=	new ShowInventoryMessage();
	}
	
	@Test
	public void test10() throws IOException, JAXBException {

		test("inventory_10unit.xml", 10);
	}

	public void test(String fileName, Integer count) throws IOException, JAXBException {
		
		File file	=	new File("./src/test/resources/datas/inventory", fileName);
		String xml 	=	new IOHelpers().readContent(file, 1024, "UTF8");
		
		LOGGER.debug("xml={}", xml);
		
		MessageType messageType	=	this.convertInventory.convert(xml);
		Assert.assertNotNull(messageType);
		Assert.assertNotNull(messageType.getMetaData());
		Assert.assertNotNull(messageType.getBody());
		Assert.assertNotNull(messageType.getBody().getInventory());
		Assert.assertNotNull(messageType.getBody().getInventory().getProducts());
		Assert.assertNotNull(messageType.getBody().getInventory().getProducts().getProduct());
		Assert.assertEquals(count.intValue(), messageType.getBody().getInventory().getProducts().getProduct().size());
		
	}
	
}
