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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="product")
@Table(name="TAB_PRODUCT")
@EntityListeners(TimestampEntityListener.class)

public class Product implements EntityWithTechnicalColumns {

	@Id
	@SequenceGenerator(name = "TAB_PRODUCT_SEQ", sequenceName = "TAB_PRODUCT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_PRODUCT_SEQ")
	@Column(name = "PROD_ID")
	private Long id;
	
	@Column(name = "PROD_IDENTIFIER")
	@NotNull
	private String uuid;

	@Column(name = "PROD_QUANTITY")
	@NotNull
	private Integer quantity;

	@Column(name = "PROD_PRICE")
	@NotNull
	private Double price;

	@OneToOne()
	@JoinColumn(name = "ORD_ID")	
	private Order order;

	@OneToOne( fetch=FetchType.LAZY)
	@JoinColumn(name = "REF_PROD_ID")
	private RefProduct refProduct;
	
	@Embedded
	private TechnicalColumns technicalColumns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public RefProduct getRefProduct() {
		return refProduct;
	}

	public void setRefProduct(RefProduct refProduct) {
		this.refProduct = refProduct;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
		this.order.addProduct(this);
	}

	@Override
	public TechnicalColumns getTechnicalColumns() {
		return technicalColumns;
	}

	@Override
	public void setTechnicalColumns(TechnicalColumns technicalColumns) {
		this.technicalColumns = technicalColumns;
	}
	
	
	
	

}
