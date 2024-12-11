package br.com.healthcare.application.api.beneficiary;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import br.com.healthcare.application.api.document.DocumentListResponse;
import br.com.healthcare.application.service.BeneficiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Beneficiary Controller", description = "Endpoints for Beneficiary Management")
public class BeneficiaryController implements BeneficiaryAPI {

	private final BeneficiaryService beneficiaryService;
	
	@Override
	@Operation(summary = "Create a new Beneficiary", description = "Creates a new beneficiary whit your documents - Document Types: ID, CPF, CNH, CNPJ, OUTRO")
	public BeneficiaryResponse save(BeneficiaryRequest beneficiaryRequest) {
		BeneficiaryResponse save = beneficiaryService.save(beneficiaryRequest);
		return save;
	}

	@Override
	@Operation(summary = "Get all Beneficiaries", description = "Get all registered beneficiaries")
	public List<BeneficiaryListResponse> getAllBeneficiary() {
		List<BeneficiaryListResponse> list = beneficiaryService.list();
		return list;
	}

	@Override
	@Operation(summary = "Get all Documents", description = "Get all documents of a registered beneficiary")
	public List<DocumentListResponse> getAllDocumentsBeneficiary(UUID idBeneficiary) {
		List<DocumentListResponse> list = beneficiaryService.list(idBeneficiary);
		return list;
	}

	@Override
	@Operation(summary = "Update a Beneficiary", description = "Update beneficiary data and documents, including(optinal) a new document - Document Types: ID, CPF, CNH, CNPJ, OUTRO")
	public void update(UUID idBeneficiary, BeneficiaryUpdateRequest beneficiaryUpdateRequest) {
		beneficiaryService.update(idBeneficiary, beneficiaryUpdateRequest);
		
	}

	@Override
	@Operation(summary = "Delete a Beneficiary", description = "Delete beneficiary data and all documents")
	public void delete(UUID idBeneficiary) {
	    beneficiaryService.delete(idBeneficiary);
	}
}
