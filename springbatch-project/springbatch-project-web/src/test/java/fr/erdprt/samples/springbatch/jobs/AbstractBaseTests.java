package fr.erdprt.samples.springbatch.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

public abstract class AbstractBaseTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	
	public Integer selectCount(String sql) {
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}
	
	
}
