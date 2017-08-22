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

@ContextConfiguration("/springbatch-job-inventory-export-partitioning-test.xml")
public class InventoryExportPartitionningJobTest extends AbstractBaseTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(InventoryExportPartitionningJobTest.class);
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test(enabled=false)
	public void test() throws Exception {
		
		LOGGER.debug("test categoryExportPartitionningJob");
		JobParameters jobParameters	=	new JobParametersBuilder().
													addLong("commit.interval", 6L).
													addString("inventoryPartitioningExportJob.grid.size", "2").
													toJobParameters();
		
		JobExecution jobExecution	=	this.jobLauncherTestUtils.launchJob(jobParameters);
		Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		
		//Assert.assertEquals(12, selectCount("SELECT COUNT(*) FROM REF_PRODUCT").intValue());
		
	}
	
}
