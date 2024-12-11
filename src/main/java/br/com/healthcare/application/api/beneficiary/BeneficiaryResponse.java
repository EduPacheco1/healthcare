package br.com.healthcare.application.api.beneficiary;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BeneficiaryResponse {

	private UUID idBeneficiary;
}
