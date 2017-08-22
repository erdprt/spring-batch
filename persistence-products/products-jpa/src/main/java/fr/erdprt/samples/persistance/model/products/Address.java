package fr.erdprt.samples.persistance.model.products;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="address")
@Table(name="TAB_ADDRESS")
@EntityListeners(TimestampEntityListener.class)
public class Address implements EntityWithTechnicalColumns {
	
	@Id
	@SequenceGenerator(name = "TAB_ADDRESS_SEQ", sequenceName = "TAB_ADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_ADDRESS_SEQ")
	@Column(name = "ADR_ID")
	private Long id;
	
	@Column(name = "ADR_STREET")
	@NotNull
	private String streetName;	

	@Column(name = "ADR_CITY")
	@NotNull
	private String city;	

	@Column(name = "ADR_ZIPCODE")
	@NotNull
	private String zipCode;	
	
	@OneToOne(mappedBy="address")
	private Person person;

	@Embedded
	private TechnicalColumns technicalColumns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public TechnicalColumns getTechnicalColumns() {
		return technicalColumns;
	}

	public void setTechnicalColumns(TechnicalColumns technicalColumns) {
		this.technicalColumns = technicalColumns;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
