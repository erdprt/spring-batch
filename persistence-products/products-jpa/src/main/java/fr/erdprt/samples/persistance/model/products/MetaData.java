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

@Entity(name="metaData")
@Table(name="TAB_METADONNEES")
@EntityListeners(TimestampEntityListener.class)
public class MetaData implements EntityWithTechnicalColumns {

	@Id
	@SequenceGenerator(name = "TAB_METADONNEES_SEQ", sequenceName = "TAB_METADONNEES_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_METADONNEES_SEQ")
	@Column(name = "MTD_ID")
	private Long id;

	@Column(name = "MTD_FROM")
	@NotNull
	private String from;

	@Column(name = "MTD_TO")
	@NotNull
	private String to;

	@Column(name = "MTD_CONTENT")
	@NotNull
	private String content;

	@Embedded
	private TechnicalColumns technicalColumns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TechnicalColumns getTechnicalColumns() {
		return technicalColumns;
	}

	public void setTechnicalColumns(TechnicalColumns technicalColumns) {
		this.technicalColumns = technicalColumns;
	}
	
}
