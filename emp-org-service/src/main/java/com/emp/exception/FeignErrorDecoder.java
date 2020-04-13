package com.emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
		case 400:
			// Do Something
			//return new BadRequestException();
			return new ResponseStatusException(HttpStatus.valueOf(response.status()),"You should append the Authentication token before sending any request");
		case 404:

			if(methodKey.contains("getEmployeeList"))
			{
				return new ResponseStatusException(HttpStatus.valueOf(response.status()),"Employee lists are not found");
			}
			break;
		default:
			return new Exception(response.reason());
		}
		return null;
	}

}
