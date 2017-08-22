package fr.erdprt.samples.persistance.utility;

import java.util.List;
import java.util.Properties;

import javax.persistence.spi.PersistenceUnitTransactionType;

/**
 * utility class to ovveride or replace persistence.xml settings
 * @author erdprt
 *
 */
public class PersistenceUnitBean {

	private String persistenceUnitName;
	private PersistenceUnitTransactionType transactionType;
	private String persistenceProviderClassName;
	private Properties properties;
	private Boolean excludeUnlistedClasses;
	private List<String> managedClassNames;
	
	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}
	public void setPersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}
	public PersistenceUnitTransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		if (transactionType!=null) {
			this.transactionType = PersistenceUnitTransactionType.valueOf(transactionType);
		}
	}
	public String getPersistenceProviderClassName() {
		return persistenceProviderClassName;
	}
	public void setPersistenceProviderClassName(String persistenceProviderClassName) {
		this.persistenceProviderClassName = persistenceProviderClassName;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Boolean getExcludeUnlistedClasses() {
		return excludeUnlistedClasses;
	}
	public void setExcludeUnlistedClasses(Boolean excludeUnlistedClasses) {
		this.excludeUnlistedClasses = excludeUnlistedClasses;
	}
	public List<String> getManagedClassNames() {
		return managedClassNames;
	}
	public void setManagedClassNames(List<String> managedClassNames) {
		this.managedClassNames = managedClassNames;
	}
	@Override
	public String toString() {
		return "PersistenceUnitBean [persistenceUnitName="
				+ persistenceUnitName + ", transactionType=" + transactionType
				+ ", persistenceProviderClassName="
				+ persistenceProviderClassName + ", properties=" + properties
				+ ", excludeUnlistedClasses=" + excludeUnlistedClasses
				+ ", managedClassNames=" + managedClassNames + "]";
	}
	
	
	
	
}
