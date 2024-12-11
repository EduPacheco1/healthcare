package br.com.healthcare.application.service;

import java.util.List;
import java.util.UUID;

import br.com.healthcare.application.api.beneficiary.BeneficiaryListResponse;
import br.com.healthcare.application.api.beneficiary.BeneficiaryRequest;
import br.com.healthcare.application.api.beneficiary.BeneficiaryResponse;
import br.com.healthcare.application.api.beneficiary.BeneficiaryUpdateRequest;
import br.com.healthcare.application.api.document.DocumentListResponse;

public interface BeneficiaryService {

	BeneficiaryResponse save(BeneficiaryRequest beneficiaryRequest);

	List<BeneficiaryListResponse> list();

	List<DocumentListResponse> list(UUID idBeneficiary);

	void update(UUID idBeneficiary, BeneficiaryUpdateRequest beneficiaryUpdateRequest);

	void delete(UUID idBeneficiary);
}
