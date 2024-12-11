package br.com.healthcare.application.api.document;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.healthcare.domain.DocType;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DocumentResponse {
	
	private UUID idDocument;
	private DocType docType;
	private String description;
	private LocalDateTime dateRegistration;
}
