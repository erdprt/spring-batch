package fr.erdprt.samples.persistance.database;

import java.io.OutputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2ScriptUtility {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(H2ScriptUtility.class);

	private String user;
	private String password;
	private String url;
	private String driver;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void execute(String sql, OutputStream outputStream) throws H2ScriptUtilityException {
		try {
			LOGGER.debug("execute:{}", sql);
			Script.execute(getUrl(), getUser(), getPassword(), outputStream);
		} catch (SQLException sqle) {
			throw new H2ScriptUtilityException(sqle);
		}


	}
}
