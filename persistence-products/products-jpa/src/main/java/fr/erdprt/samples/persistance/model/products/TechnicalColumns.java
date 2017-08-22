package fr.erdprt.samples.persistance.model.products;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author erdprt
 * 
 */
@Embeddable
public class TechnicalColumns {
	
	@Column(name = "TRA_CREATED", insertable = true, updatable = false)
	private Timestamp creationTime;
	
	@Column(name = "TRA_UPDATED", insertable = true, updatable = true)
	private Timestamp updateTime;

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
