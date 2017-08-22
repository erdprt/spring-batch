package fr.erdprt.samples.persistance.model.products.listeners;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.erdprt.samples.persistance.model.products.EntityWithTechnicalColumns;
import fr.erdprt.samples.persistance.model.products.TechnicalColumns;

/**
 * 
 * @author erdprt
 */
public class TimestampEntityListener {
	
	private static final Logger LOGGER	=	LoggerFactory.getLogger(TimestampEntityListener.class);

	/**
	 * called when creating new line
	 * @param entity
	 */
	@PrePersist
	void onCreate(Object entity) {
		LOGGER.trace("onCreate: {}", entity);
		if (entity instanceof EntityWithTechnicalColumns) {
			LOGGER.trace("Creation for {}" , entity.getClass());
			EntityWithTechnicalColumns eact = (EntityWithTechnicalColumns) entity;
			if (eact.getTechnicalColumns() == null) {
				eact.setTechnicalColumns(new TechnicalColumns());
			}
			eact.getTechnicalColumns().setCreationTime(createTimeStamp());
			eact.getTechnicalColumns().setUpdateTime(createTimeStamp());
		}
	}

	/**
	 * called when updating line 
	 * @param entity
	 */
	@PreUpdate
	void onPersist(Object entity) {
		LOGGER.trace("onPersist: {}", entity);
		if (entity instanceof EntityWithTechnicalColumns) {
			EntityWithTechnicalColumns eact = (EntityWithTechnicalColumns) entity;
			LOGGER.trace("Update for {}" , entity.getClass());
			if (eact.getTechnicalColumns() == null) {
				eact.setTechnicalColumns(new TechnicalColumns());
			}
			eact.getTechnicalColumns().setUpdateTime(createTimeStamp());
		}
	}
	
	/**
	 * Utility method
	 * @return TimesTamp
	 */
	private Timestamp createTimeStamp() {
		Timestamp timestamp	=	new Timestamp(Calendar.getInstance().getTimeInMillis());
		return timestamp;
	}
}
