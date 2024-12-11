package br.com.healthcare.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Document {

	@Id
	private UUID idDocument;
	
	@Column(nullable = false)
	private DocType docType;
	
	@Column(nullable = false)
	private String description;
	
	@Column
	private LocalDateTime dateRegistration;
	
	@Column
	private LocalDateTime dateChange;
	
	@ManyToOne
	@JoinColumn(name = "beneficiary_id", nullable = false)
	private Beneficiary beneficiary;
	
	{
		this.idDocument = UUID.randomUUID();
		this.dateRegistration = LocalDateTime.now();
	}
	
	 public static Document createDocument(DocType docType, String description, Beneficiary beneficiary) {
	        Document document = new Document();
	        document.setDocType(docType);
	        document.setDescription(description);
	        document.setBeneficiary(beneficiary);
	        return document;
	    }
}
