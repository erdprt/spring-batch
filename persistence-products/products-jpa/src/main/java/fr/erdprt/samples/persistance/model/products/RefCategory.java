package fr.erdprt.samples.persistance.model.products;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="refCategory")
@Table(name="REF_CATEGORY")
@EntityListeners(TimestampEntityListener.class)
public class RefCategory implements EntityWithTechnicalColumns {

	@Id
	@Column(name = "REF_CAT_ID")
	private String id;
	
	@Column(name = "REF_CAT_LABEL")
	@NotNull
	private String label;

	@Column(name = "REF_CAT_DESC")
	@NotNull
	private String description;
	
	@Embedded
	private TechnicalColumns technicalColumns;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public TechnicalColumns getTechnicalColumns() {
		return this.technicalColumns;
	}

	@Override
	public void setTechnicalColumns(TechnicalColumns technicalColumns) {
		this.technicalColumns	=	technicalColumns;
	}
	
	
}
