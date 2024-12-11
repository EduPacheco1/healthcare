package br.com.healthcare.application.api.beneficiary;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.healthcare.application.api.document.DocumentListResponse;
import br.com.healthcare.util.MediaType;

@RestController
@RequestMapping("v1/beneficiary")
public interface BeneficiaryAPI {

	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
				 produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@ResponseStatus(code = HttpStatus.CREATED)
	BeneficiaryResponse save(@RequestBody BeneficiaryRequest beneficiaryRequest);
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@ResponseStatus(code = HttpStatus.OK)
	List<BeneficiaryListResponse> getAllBeneficiary();
	
	@GetMapping(value = "/documents/{idBeneficiary}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@ResponseStatus(code = HttpStatus.OK)
	List<DocumentListResponse> getAllDocumentsBeneficiary(@PathVariable UUID idBeneficiary);
	
	@PatchMapping(value = "update/{idBeneficiary}",
				  consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
				  produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void update(@PathVariable UUID  idBeneficiary, @RequestBody BeneficiaryUpdateRequest beneficiaryUpdateRequest);
	
	@DeleteMapping(value = "/{idBeneficiary}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void delete(@PathVariable UUID idBeneficiary);
}
