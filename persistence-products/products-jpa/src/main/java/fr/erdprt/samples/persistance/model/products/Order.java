package fr.erdprt.samples.persistance.model.products;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="order")
@Table(name="TAB_ORDERS")
@EntityListeners(TimestampEntityListener.class)
public class Order implements EntityWithTechnicalColumns {

	@Id
	@SequenceGenerator(name = "TAB_ORDERS_SEQ", sequenceName = "TAB_ORDERS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_ORDERS_SEQ")
	@Column(name = "ORD_ID")
	private Long id;

	@Column(name = "ORD_PRICE")
	@NotNull
	private Double price;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "PER_ID")	
	private Person person;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "ORD_ID")
	private Set<Product> products	=	new HashSet<Product>();

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MTD_ID")	
	private MetaData metaData;

	@Embedded
	private TechnicalColumns technicalColumns;

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		getProducts().add(product);
		/*
		if (product.getOrder()==null) {
			product.setOrder(this);
		}
		*/
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public TechnicalColumns getTechnicalColumns() {
		return technicalColumns;
	}

	public void setTechnicalColumns(TechnicalColumns technicalColumns) {
		this.technicalColumns = technicalColumns;
	}
	
	
}
