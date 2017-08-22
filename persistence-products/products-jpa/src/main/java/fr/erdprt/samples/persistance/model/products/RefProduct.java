package fr.erdprt.samples.persistance.model.products;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="refProduct")
@Table(name="REF_PRODUCT")
@EntityListeners(TimestampEntityListener.class)
public class RefProduct implements EntityWithTechnicalColumns {

	@Id
	@SequenceGenerator(name = "REF_PRODUCT_SEQ", sequenceName = "REF_PRODUCT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_PRODUCT_SEQ")
	@Column(name = "REF_PROD_ID")
	private Long id;
	
	@Column(name = "REF_PROD_CODE")
	@NotNull
	private String code;

	@Column(name = "REF_PROD_LABEL")
	@NotNull
	private String label;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REF_PROD_STA_ID")
	private RefProductStatus refProductStatus;
	
	@OneToOne( fetch=FetchType.LAZY)
	@JoinColumn(name = "REF_CAT_ID")
	private RefCategory refCategory;
	
	@OneToOne(mappedBy="refProduct")
	private RefInventory refInventory;
	
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
	
	public RefProductStatus getRefProductStatus() {
		return refProductStatus;
	}

	public void setRefProductStatus(RefProductStatus refProductStatus) {
		this.refProductStatus = refProductStatus;
	}

	public RefCategory getRefCategory() {
		return refCategory;
	}

	public void setRefCategory(RefCategory refCategory) {
		this.refCategory = refCategory;
	}

	public RefInventory getRefInventory() {
		return refInventory;
	}

	public void setRefInventory(RefInventory refInventory) {
		this.refInventory = refInventory;
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
