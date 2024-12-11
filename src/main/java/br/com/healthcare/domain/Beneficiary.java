package br.com.healthcare.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import br.com.healthcare.application.api.beneficiary.BeneficiaryRequest;
import br.com.healthcare.application.api.beneficiary.BeneficiaryUpdateRequest;
import br.com.healthcare.application.api.document.DocumentRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Beneficiary {

	@Id
	private UUID idBeneficiary;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String tel;

	@Column(nullable = false)
	private LocalDate dateBirth;

	@Column
	private LocalDateTime dateRegistration;

	@Column
	private LocalDateTime dateChange;

	@OneToMany(mappedBy = "beneficiary", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Document> document;

	{
		this.idBeneficiary = UUID.randomUUID();
		this.dateRegistration = LocalDateTime.now();
	}

	public Beneficiary(BeneficiaryRequest beneficiaryRequest) {
		this();
		this.name = beneficiaryRequest.getName();
		this.tel = beneficiaryRequest.getTel();
		this.dateBirth = beneficiaryRequest.getDateBirth();
		this.dateRegistration = LocalDateTime.now();

		if (beneficiaryRequest.getDocuments() != null) {
			this.document = beneficiaryRequest.getDocuments().stream().map(
					docRequest -> Document.createDocument(docRequest.getDocType(), docRequest.getDescription(), this))
					.collect(Collectors.toList());
		}
	}

	public void update(BeneficiaryUpdateRequest beneficiaryUpdateRequest) {
	    updateFieldIfNotNull(beneficiaryUpdateRequest.getName(), value -> this.name = value);
	    updateFieldIfNotNull(beneficiaryUpdateRequest.getTel(), value -> this.tel = value);
	    updateFieldIfNotNull(beneficiaryUpdateRequest.getDateBirth(), value -> this.dateBirth = value);

	    this.dateChange = LocalDateTime.now();

	    updateFieldIfNotNull(beneficiaryUpdateRequest.getDocuments(), this::updateDocuments);
	}

	private <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
	    if (value != null) {
	        setter.accept(value);
	    }
	}

	private void updateDocuments(List<DocumentRequest> documentUpdates) {
	    for (DocumentRequest updateDoc : documentUpdates) {
	        if (updateDoc.getIdDocument() != null) {
	            this.document.stream()
	                .filter(doc -> doc.getIdDocument().equals(updateDoc.getIdDocument()))
	                .findFirst()
	                .ifPresent(doc -> {
	                    if (updateDoc.getDocType() != null) {
	                        doc.setDocType(updateDoc.getDocType());
	                    }
	                    if (updateDoc.getDescription() != null) {
	                        doc.setDescription(updateDoc.getDescription());
	                    }
	                    doc.setDateChange(LocalDateTime.now());
	                });
	        } else {
	            Document newDoc = Document.createDocument(
	                updateDoc.getDocType(), 
	                updateDoc.getDescription(), 
	                this
	            );
	            this.document.add(newDoc);
	        }
	    }
	}
}
	
