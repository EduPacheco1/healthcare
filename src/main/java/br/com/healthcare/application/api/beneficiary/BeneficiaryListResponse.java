package br.com.healthcare.application.api.beneficiary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.healthcare.domain.Beneficiary;
import lombok.Value;

@Value
public class BeneficiaryListResponse {

	private UUID idBeneficiary;
	private String name;
	private String tel;
	private LocalDate dateBirth;
	private LocalDateTime dateRegistration;
	private LocalDateTime dateChange;
	
	public static List<BeneficiaryListResponse> converte(List<Beneficiary> beneficiaries){
		return beneficiaries.stream().map(BeneficiaryListResponse::new).collect(Collectors.toList());
	}
	
	public BeneficiaryListResponse(Beneficiary beneficiary) {
		this.idBeneficiary = beneficiary.getIdBeneficiary();
		this.name = beneficiary.getName();
		this.tel = beneficiary.getTel();
		this.dateBirth = beneficiary.getDateBirth();
		this.dateRegistration = beneficiary.getDateRegistration();
		this.dateChange = beneficiary.getDateChange();
	}
}
