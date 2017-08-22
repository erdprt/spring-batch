package fr.erdprt.samples.persistance.model.products;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="refProductStatus")
@Table(name="REF_PRODUCT_STATUS")
@EntityListeners(TimestampEntityListener.class)
public class RefProductStatus implements EntityWithTechnicalColumns {

	@Id
	@SequenceGenerator(name = "REF_PRODUCT_STATUS_SEQ", sequenceName = "REF_PRODUCT_STATUS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_PRODUCT_STATUS_SEQ")
	@Column(name = "REF_PROD_STA_ID")
	private Long id;
	
	@Column(name = "REF_PROD_STA_CODE")
	@NotNull
	private String code;
	
	@Column(name = "REF_PROD_STA_LABEL")
	@NotNull
	private String label;
	
	@Embedded
	private TechnicalColumns technicalColumns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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
