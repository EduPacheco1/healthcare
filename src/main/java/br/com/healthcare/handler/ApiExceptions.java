package br.com.healthcare.handler;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class ApiExceptions extends RuntimeException {
	
	private HttpStatus statusException;
	private ErrorApiResponse bodyException;
	
	private ApiExceptions(HttpStatus statusException, String message, Exception e) {
		super(message, e);
		this.statusException = statusException;
		this.bodyException = ErrorApiResponse.builder()
				.message(message)
				.description(getDescription(e))
				.build();
	}
	

	public static ApiExceptions build(HttpStatus statusException, String message) {
		return new ApiExceptions(statusException, message, null);
	}
	
	public static ApiExceptions build(HttpStatus statusException, String message, Exception e) {
		log.error("Exception: ", e);
		return new ApiExceptions(statusException, message, e);
	}
	
	private String getDescription(Exception e) {
		return Optional.ofNullable(e)
				.map(ApiExceptions::getMessageCause).orElse(null);
	}
	
	private static String getMessageCause(Exception e) {
		return e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
	}
	
	public ResponseEntity<ErrorApiResponse> buildErrorResponseEntity(){
		return ResponseEntity
				.status(statusException)
				.body(bodyException);
	}

	private static final long serialVersionUID = 1L;
	
}
