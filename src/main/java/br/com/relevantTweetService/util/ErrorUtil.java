package br.com.relevantTweetService.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;

public class ErrorUtil {

	public static ResponseEntity<Object> sendError(int error, Exception e) {
		return ResponseEntity.status(error).body(new ObjectError(e.getCause().toString(), e.getMessage()));
	}

}
