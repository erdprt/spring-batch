package fr.erdprt.samples.springbatch.jobs;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@ContextConfiguration("/springbatch-job-dummy-test.xml")
public class DummyJobTest extends AbstractTestNGSpringContextTests {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(DummyJobTest.class);
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobRegistry jobRegistry;
	
	@BeforeTest
	public void setUp() {
	}
	
	@Test
	public void test() throws Exception {
		
		LOGGER.info("test dummyJob");
		
		JobParameters jobParameters	=	null;

		jobParameters	=	new JobParametersBuilder().
									addLong("commit.interval", 6L).
									addDate("date", Calendar.getInstance().getTime()).
									toJobParameters();
		this.jobLauncherTestUtils.launchJob(jobParameters);

		
	}

	
}
