package fr.erdprt.samples.springbatch.tests;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import fr.erdprt.samples.springbatch.jobs.AbstractBaseTests;

@ContextConfiguration("/springbatch-job-monitoring-test.xml")
public class SpringBatchMonitoringTest extends AbstractBaseTests {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(SpringBatchMonitoringTest.class);

	@Autowired
	private JobExplorerFactoryBean jobExplorer;
	
	
	@Test(enabled=false)
	public void test() throws Exception {
		LOGGER.info("test");
		
		JobExplorer jExplorer	=	this.jobExplorer.getObject();
		List<String> jobNames	=	jExplorer.getJobNames();
		
		for (String jobName:jobNames) {
			LOGGER.debug("jobName={}", jobName);
		}
		
		Assert.assertNotNull("");
		
		
	}
}
