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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="refInventory")
@Table(name="REF_INVENTORY")
@EntityListeners(TimestampEntityListener.class)
public class RefInventory implements EntityWithTechnicalColumns {

	@Id
	@SequenceGenerator(name = "REF_INVENTORY_SEQ", sequenceName = "REF_INVENTORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_INVENTORY_SEQ")
	@Column(name = "REF_INV_ID")
	private Long id;
	
	@Column(name = "REF_INV_PRICE", precision=2)
	@NotNull
	private Double price;

	@Column(name = "REF_INV_AVAILABLE")
	@NotNull
	private Boolean available;

	@Column(name = "REF_INV_QUANTITY")
	@NotNull
	private Integer quantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REF_PROD_ID")
	private RefProduct refProduct;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public RefProduct getRefProduct() {
		return refProduct;
	}

	public void setRefProduct(RefProduct refProduct) {
		this.refProduct = refProduct;
	}

	@Embedded
	private TechnicalColumns technicalColumns;


	@Override
	public TechnicalColumns getTechnicalColumns() {
		return this.technicalColumns;
	}

	@Override
	public void setTechnicalColumns(TechnicalColumns technicalColumns) {
		this.technicalColumns	=	technicalColumns;
	}
	
	
}
