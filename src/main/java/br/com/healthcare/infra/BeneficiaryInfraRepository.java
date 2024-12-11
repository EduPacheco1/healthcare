package br.com.healthcare.infra;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.healthcare.application.repository.BeneficiaryRepository;
import br.com.healthcare.domain.Beneficiary;
import br.com.healthcare.domain.Document;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BeneficiaryInfraRepository implements BeneficiaryRepository {

	private final BeneficiarySpringDataJpaRepository beneficiarySpringDataJpaRepository;

	@Override
	public Beneficiary save(Beneficiary beneficiary) {
		beneficiarySpringDataJpaRepository.save(beneficiary);
		return beneficiary;
	}

	@Override
	public List<Beneficiary> list() {
		List<Beneficiary> list = beneficiarySpringDataJpaRepository.findAll();
		return list;
	}

	@Override
	public List<Document> list(UUID idBeneficiary) {
		return beneficiarySpringDataJpaRepository.findById(idBeneficiary)
				.map(Beneficiary::getDocument)
				.orElse(Collections.emptyList());
	}

	@Override
	public Optional<Beneficiary> findById(UUID idBeneficiary) {
	    return beneficiarySpringDataJpaRepository.findById(idBeneficiary);
	}

	@Override
	public void delete(UUID idBeneficiary) {
	    beneficiarySpringDataJpaRepository.deleteById(idBeneficiary);
	}
}
