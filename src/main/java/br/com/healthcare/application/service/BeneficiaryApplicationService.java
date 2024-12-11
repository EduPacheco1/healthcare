package br.com.healthcare.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.healthcare.application.api.beneficiary.BeneficiaryListResponse;
import br.com.healthcare.application.api.beneficiary.BeneficiaryRequest;
import br.com.healthcare.application.api.beneficiary.BeneficiaryResponse;
import br.com.healthcare.application.api.beneficiary.BeneficiaryUpdateRequest;
import br.com.healthcare.application.api.document.DocumentListResponse;
import br.com.healthcare.application.repository.BeneficiaryRepository;
import br.com.healthcare.domain.Beneficiary;
import br.com.healthcare.domain.Document;
import br.com.healthcare.handler.ApiExceptions;
import br.com.healthcare.infra.DocumentSpringDataJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeneficiaryApplicationService implements BeneficiaryService {
	
	private final BeneficiaryRepository beneficiaryRepository;

	private final DocumentSpringDataJpaRepository documentSpringDataJpaRepository;

    @Override
    public BeneficiaryResponse save(BeneficiaryRequest beneficiaryRequest) {
        Beneficiary beneficiary = new Beneficiary(beneficiaryRequest);
        beneficiary = beneficiaryRepository.save(beneficiary);
        if (beneficiary.getDocument() != null && !beneficiary.getDocument().isEmpty()) {
        	documentSpringDataJpaRepository.saveAll(beneficiary.getDocument());
        }
        
        return BeneficiaryResponse.builder()
            .idBeneficiary(beneficiary.getIdBeneficiary())
            .build();
    }

	@Override
	public List<BeneficiaryListResponse> list() {
		List<Beneficiary> list = beneficiaryRepository.list();
		return BeneficiaryListResponse.converte(list);
	}

	@Override
	public List<DocumentListResponse> list(UUID idBeneficiary) {
	    List<Document> documents = beneficiaryRepository.list(idBeneficiary);
	    return DocumentListResponse.convert(documents);
	}

	@Override
	public void update(UUID idBeneficiary, BeneficiaryUpdateRequest beneficiaryUpdateRequest) {
	    Beneficiary beneficiary = beneficiaryRepository.findById(idBeneficiary)
	        .orElseThrow(() -> ApiExceptions.build(HttpStatus.NOT_FOUND, "ID Beneficiary not found " + idBeneficiary));
	    
	    beneficiary.update(beneficiaryUpdateRequest);
	    beneficiaryRepository.save(beneficiary);
	}

	@Override
	public void delete(UUID idBeneficiary) {
	    beneficiaryRepository.delete(idBeneficiary);
	}
}
