package it.engineering.nc.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.engineering.nc.app.dto.OrganizationTypeDto;
import it.engineering.nc.app.dto.ServiceTypeDto;
import it.engineering.nc.app.service.ServiceTypeService;

@RestController
@RequestMapping("service-type")
public class ServiceTypeRestController {

	private final ServiceTypeService serviceTypeService;
	
	public ServiceTypeRestController(ServiceTypeService serviceTypeService) {
		super();
		this.serviceTypeService = serviceTypeService;
	}
	
	@GetMapping
	public List<ServiceTypeDto> findAll() {
		return serviceTypeService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ServiceTypeDto> organization = serviceTypeService.findById(id);
		if (organization.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(organization.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid organization type id!");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
