package fr.erdprt.samples.springbatch.web;

import java.util.Calendar;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobLauncherController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobLauncherController.class);

	private static final String JOB_PARAM = "job";

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobRegistry jobRegistry;
	
	public JobLauncherController() {
		super();
	}


	@RequestMapping(value = "/launch/{job}", method = RequestMethod.GET)
	public String launchJob(@PathVariable("job") String job, HttpServletRequest request) throws Exception {
		
		LOGGER.info("launchJob:{}", job);
		launch(job, request);
		return "index";
	}

	@RequestMapping(value = "/execute", method={RequestMethod.GET,RequestMethod.POST })
	public String executeJob(HttpServletRequest request) throws Exception {
		
		String job	=	request.getParameter(JOB_PARAM);
		LOGGER.info("launchJob:{}", job);
		launch(job, request);
		return "index";
	}
	
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultPage() {
		return "index";
	}
	
	public void launch(String job, HttpServletRequest request) throws Exception {
		
		LOGGER.info("request for job:{}", job);
		
		JobParametersBuilder builder = extractParameters(request);
		
		jobLauncher.run(jobRegistry.getJob(job),
				builder.toJobParameters());
		
	}

	private JobParametersBuilder extractParameters(HttpServletRequest request) {
		
		JobParametersBuilder builder = new JobParametersBuilder();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			
			String paramName 	= 	paramNames.nextElement();
			String paramValue	=	request.getParameter(paramName);
			
			LOGGER.trace("(name,value)=({},{})", new Object[] {paramName, paramValue});
			if (!JOB_PARAM.equals(paramName)) {
				builder.addString(paramName, request.getParameter(paramName));
			}
		}
		// Add a parameter timetamp to make the batch restartable
		builder.addLong("timeStamp", Calendar.getInstance().getTimeInMillis());
		return builder;
	}
	
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}


	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}


	public JobRegistry getJobRegistry() {
		return jobRegistry;
	}


	public void setJobRegistry(JobRegistry jobRegistry) {
		this.jobRegistry = jobRegistry;
	}
	
	

}
