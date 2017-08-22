package fr.erdprt.samples.springbatch.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration("/springbatch-job-orders-import-test.xml")
public class OrdersImportJobTest extends AbstractBaseTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(OrdersImportJobTest.class);
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void test() throws Exception {
		
		LOGGER.debug("test inventoryJob");
		JobParameters jobParameters	=	new JobParametersBuilder().
												addString("inputFile", "./src/test/resources/datas/orders/orders_5unit.xml").
												addString("elementName", "person").
												addLong("commit.interval", 2L).
												toJobParameters();
		JobExecution jobExecution	=	this.jobLauncherTestUtils.launchJob(jobParameters);
		Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		
		Assert.assertEquals(5, selectCount("SELECT COUNT(*) FROM TAB_ORDERS").intValue());
		
	}
	
}
