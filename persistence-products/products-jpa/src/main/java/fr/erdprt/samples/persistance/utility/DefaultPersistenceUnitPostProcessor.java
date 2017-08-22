package fr.erdprt.samples.persistance.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

/**
 * 
 * @author erprt
 *
 */
public class DefaultPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(DefaultPersistenceUnitPostProcessor.class);
	
	private PersistenceUnitBean persistenceUnitBean;
	
	public DefaultPersistenceUnitPostProcessor(PersistenceUnitBean persistenceUnitBean) {
		super();
		this.persistenceUnitBean = persistenceUnitBean;
	}


	@Override
	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo mInfo) {
		
		if (persistenceUnitBean!=null) {
			LOGGER.trace("applying with persistenceUnitBean: {}", persistenceUnitBean);
			
			if (persistenceUnitBean.getPersistenceUnitName()!=null) {
				mInfo.setPersistenceUnitName(persistenceUnitBean.getPersistenceUnitName());
			}
			if (persistenceUnitBean.getPersistenceProviderClassName()!=null) {
				mInfo.setPersistenceProviderClassName(persistenceUnitBean.getPersistenceProviderClassName());
			}
			if (persistenceUnitBean.getTransactionType()!=null) {	
				mInfo.setTransactionType(persistenceUnitBean.getTransactionType());
			}
			if (persistenceUnitBean.getProperties()!=null && persistenceUnitBean.getProperties().size()>0) {
				mInfo.setProperties(persistenceUnitBean.getProperties());
			}
			if (persistenceUnitBean.getExcludeUnlistedClasses()!=null) {
				mInfo.setExcludeUnlistedClasses(persistenceUnitBean.getExcludeUnlistedClasses());
			}
			if (persistenceUnitBean.getManagedClassNames()!=null && persistenceUnitBean.getManagedClassNames().size()>0) {
				for (String managedClassName:persistenceUnitBean.getManagedClassNames()) {
					mInfo.addManagedClassName(managedClassName);
				}
			}
		}
	}
	
}
