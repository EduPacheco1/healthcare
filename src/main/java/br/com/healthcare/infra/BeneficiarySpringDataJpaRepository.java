package br.com.healthcare.infra;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.healthcare.domain.Beneficiary;
import br.com.healthcare.domain.Document;

public interface BeneficiarySpringDataJpaRepository extends JpaRepository<Beneficiary, UUID>{

	List<Document> findAllByIdBeneficiary(UUID idBeneficiary);

}
