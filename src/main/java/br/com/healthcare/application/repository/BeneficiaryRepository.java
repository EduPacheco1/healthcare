package br.com.healthcare.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.healthcare.domain.Beneficiary;
import br.com.healthcare.domain.Document;

public interface BeneficiaryRepository {

	Beneficiary save(Beneficiary beneficiary);

	List<Beneficiary> list();

	List<Document> list(UUID idBeneficiary);

	Optional<Beneficiary> findById(UUID idBeneficiary);

	void delete(UUID idBeneficiary);

}
