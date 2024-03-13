package it.engineering.nc.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.dto.PatientDto;
import it.engineering.nc.app.dto.PractitionerDto;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.exception.MyProjectException;
import it.engineering.nc.app.service.PatientService;
import it.engineering.nc.app.specification.SearchCriteria;


@RestController
@RequestMapping("patient")
public class PatientRestController {

	private final PatientService patientService;

	public PatientRestController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	
	@GetMapping
	public List<PatientDto> findAll() {
		return patientService.findAll();
	}
	
	@GetMapping("filter")
	public Page<PatientDto> getByPage(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, 
			@RequestParam(defaultValue = "ASC") String direction, 
			@RequestParam(defaultValue = "identifier") String sortBy) {
		return patientService.getByPage(pageNo,pageSize,direction,sortBy);
	}
	
	@PostMapping("filter")
	public ResponseEntity<Page<PatientDto>> getByPageFiltered(
    		@RequestBody List<SearchCriteria> searchCriteria,
    		@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, 
			@RequestParam(defaultValue = "ASC") String direction, 
			@RequestParam(defaultValue = "identifier") String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(
        		patientService.getByPageFiltered(pageNo,pageSize,direction,sortBy,searchCriteria));
    }
	
	@PostMapping("count")
	public Long getByPage(@RequestBody List<SearchCriteria> searchCriteria) {
		return patientService.count(searchCriteria);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<PatientDto> practitioner = patientService.findById(id);
		if (practitioner.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(practitioner.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid organization id!");
	}
	
	@GetMapping("identifier/{identifier}")
	public ResponseEntity<Object> findByIdentifier(@PathVariable String identifier) {
		
		Optional<PatientDto> patient = patientService.findByIdentifier(identifier);
		if (patient.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(patient.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid organization id!");
	}
	
	@PostMapping()
	public ResponseEntity<Object> save(@Valid @RequestBody PatientDto patientDto) {
		try {
			System.out.println(patientDto);
			return ResponseEntity.status(HttpStatus.OK).body(patientService.save(patientDto));
		} catch (EntityExistsException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PostMapping("bulk")
	public ResponseEntity<String> bulkImport(@RequestBody List<PatientDto> patients) {
		try {
			patientService.bulkImport(patients);
			return ResponseEntity.status(HttpStatus.OK).body("Everything imported.");
		} catch (EntityExistsException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PatientDto patientDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(patientService.update(patientDto));
		} catch (InvalidEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			patientService.delete(id);
			return ResponseEntity.ok("Deleted");
		} catch (MyProjectException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
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
