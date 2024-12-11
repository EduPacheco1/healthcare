package br.com.healthcare.application.api.document;

import java.util.UUID;

import br.com.healthcare.domain.DocType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DocumentUpdateRequest {

	private UUID idDocument;
    private DocType docType;
    private String description;
}
