package br.com.healthcare.application.api.document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.healthcare.domain.DocType;
import br.com.healthcare.domain.Document;
import lombok.Value;

@Value
public class DocumentListResponse {

	private UUID idDocument;
	private DocType docType;
	private String description;
	private LocalDateTime dateRegistration;
	private LocalDateTime dateChange;
	
	public static List<DocumentListResponse> convert(List<Document> documents){
		return documents.stream().map(DocumentListResponse::new).collect(Collectors.toList());
	}
	
	public DocumentListResponse(Document document) {
		this.idDocument = document.getIdDocument();
		this.docType = document.getDocType();
		this.description = document.getDescription();
		this.dateRegistration = document.getDateRegistration();
		this.dateChange = document.getDateChange();
		
	}
	
}
