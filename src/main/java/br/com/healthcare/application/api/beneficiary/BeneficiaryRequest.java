package br.com.healthcare.application.api.beneficiary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.healthcare.application.api.document.DocumentRequest;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BeneficiaryRequest {

	private String name;
	private String tel;
	private LocalDate dateBirth;
	private List<DocumentRequest> documents = new ArrayList<>();
}
