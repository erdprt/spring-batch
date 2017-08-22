package fr.erdprt.samples.springbatch.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration("/springbatch-job-category-import-test.xml")
public class CategoryImportJobTest extends AbstractBaseTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(CategoryImportJobTest.class);
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	

	@Test
	public void test() throws Exception {
		
		LOGGER.info("test categoryJob");
		JobParameters jobParameters	=	new JobParametersBuilder().
												addString("inputFile", "./src/test/resources/datas/category/category_10unit.xml").
												addString("elementName", "category").
												addLong("commit.interval", 1L).
												toJobParameters();
		JobExecution jobExecution	=	this.jobLauncherTestUtils.launchJob(jobParameters);
		Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		
		Assert.assertEquals(10, selectCount("SELECT COUNT(*) FROM REF_CATEGORY").intValue());
	}
	
}
