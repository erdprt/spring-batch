package fr.erdprt.samples.persistance.model.products;

import java.util.Calendar;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.erdprt.samples.persistance.model.products.listeners.TimestampEntityListener;

@Entity(name="person")
@Table(name="TAB_PERSON")
@EntityListeners(TimestampEntityListener.class)
public class Person implements EntityWithTechnicalColumns {

	@Id
	@SequenceGenerator(name = "TAB_PERSON_SEQ", sequenceName = "TAB_PERSON_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_PERSON_SEQ")
	@Column(name = "PER_ID")
	private Long id;

	@Column(name = "PER_LASTNAME")
	@NotNull
	private String lastName;

	@Column(name = "PER_FIRSTNAME")
	@NotNull
	private String firstName;

	@Column(name = "PER_BIRTH_DATE")
	@NotNull
	private Calendar birthDate;

	@Column(name = "PER_NATIONALITY")
	@NotNull
	private String nationality;

	@Column(name = "PER_IDENTIFIER")
	@NotNull
	private String uuid;

	@Column(name = "PER_CODE")
	@NotNull
	private String code;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "ADR_ID")	
	private Address address;
	
	@OneToMany(mappedBy="person")
	private Set<Order> orders	=	new HashSet<Order>();
	
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		this.address.setPerson(this);
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
