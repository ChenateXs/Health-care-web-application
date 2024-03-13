package it.engineering.nc.app.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.entity.ExaminationEntity;
import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.entity.PatientEntity;
import it.engineering.nc.app.entity.PractitionerEntity;
import it.engineering.nc.app.enums.ExaminationStatusType;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.ExaminationStillRunningException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.mapper.OrganizationMapper;
import it.engineering.nc.app.repository.ExaminationRepository;
import it.engineering.nc.app.repository.OrganizationRepository;
import it.engineering.nc.app.repository.PatientRepository;
import it.engineering.nc.app.repository.PractitionerRepository;
import it.engineering.nc.app.service.OrganizationService;
import it.engineering.nc.app.specification.GenericFilterSpecification;
import it.engineering.nc.app.specification.SearchCriteria;
import it.engineering.nc.app.specification.SearchOperation;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private final OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);
	
	private final OrganizationRepository organizationRepository;
	private final PractitionerRepository practitionerRepository;
	private final ExaminationRepository examinationRepository;
	private final PatientRepository patientRepository;
	
	public OrganizationServiceImpl(
			OrganizationRepository organizationRepository,
			PractitionerRepository practitionerRepository, 
			ExaminationRepository examinationRepository,
			PatientRepository patientRepository) {
		super();
		this.organizationRepository = organizationRepository;
		this.practitionerRepository = practitionerRepository;
		this.examinationRepository = examinationRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	public List<OrganizationDto> findAll() {
		List<OrganizationEntity> entities = organizationRepository.findAll();
		return entities.stream().map(entity -> {
			return organizationMapper.toDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<OrganizationDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		Page<OrganizationEntity> entities = organizationRepository.findAll(pageable);
		return entities.map(organizationMapper::toDto);
	}
	
	@Override
	public Page<OrganizationDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		
		GenericFilterSpecification<OrganizationEntity> specification = new GenericFilterSpecification<OrganizationEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
       
        return organizationRepository.findAll(specification,pageable).map(organizationMapper::toDto);
	}
	

	@Override
	public Long count(List<SearchCriteria> searchCriteria) {
		GenericFilterSpecification<OrganizationEntity> specification = new GenericFilterSpecification<OrganizationEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
        return organizationRepository.count(specification);
	}
	
	
	@Override
	public Optional<OrganizationDto> findById(Long id) {
		Optional<OrganizationEntity> entity = organizationRepository.findById(id);
		if(entity.isPresent())
			return Optional.of(organizationMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}
	
	@Override
	public Optional<OrganizationDto> findByIdentifier(String identifier) {
		Optional<OrganizationEntity> entity = organizationRepository.findByIdentifier(identifier);
		if(entity.isPresent())
			return Optional.of(organizationMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}

	@Override
	public OrganizationDto save(OrganizationDto organizationDto) throws EntityExistsException {
		Optional<OrganizationEntity> entity = organizationRepository.findById(organizationDto.getIdOrganization());
		if (entity.isPresent()) 
			throw new EntityExistsException(entity.get(), "Organization already exists!");
		
		OrganizationEntity organization = organizationMapper.toEntity(organizationDto);
		organization.setActive(true);
		organization = organizationRepository.save(organization);
		return organizationMapper.toDto(organization);
	}

	@Transactional
	@Override
	public void bulkImport(List<OrganizationDto> organizations) throws EntityExistsException {
		for(OrganizationDto organizationDto: organizations) {
			Optional<OrganizationEntity> entity = organizationRepository.findById(organizationDto.getIdOrganization());
			if (entity.isPresent()) 
				throw new EntityExistsException(entity.get(), "Organization already exists!");
		
			OrganizationEntity organization = organizationMapper.toEntity(organizationDto);
			organization.setActive(true);
			organization = organizationRepository.save(organization);
		}
	}
	
	@Override
	public OrganizationDto update(OrganizationDto organizationDto) throws InvalidEntityException {
		Optional<OrganizationEntity> entity = organizationRepository.findById(organizationDto.getIdOrganization());
		if (!entity.isPresent()) {
			throw new InvalidEntityException("Organization with id "+organizationDto.getIdOrganization() +" does not exist!");
		}
		OrganizationEntity organization = organizationMapper.toEntity(organizationDto);
		organization.setActive(true);
		organization = organizationRepository.save(organization);
		return organizationMapper.toDto(organization);
	}
	
	@Transactional
	@Override
	public void delete(Long id) throws InvalidEntityException, ExaminationStillRunningException  {
		Optional<OrganizationEntity> entity = organizationRepository.findById(id);
		if (!entity.isPresent()) 
			throw new InvalidEntityException("Organization with id "+id +" does not exist!");
		
		GenericFilterSpecification<ExaminationEntity> specification = new GenericFilterSpecification<ExaminationEntity>();
		specification.add(new SearchCriteria("organization",id ,SearchOperation.EQUAL));
		boolean exists = examinationRepository.exists(specification);
		
		if(exists)
			throw new ExaminationStillRunningException("Examination is still running practitioner with id: "+id+" can not be deleted!");
		
		patientRepository.removeAllOrganizationsWithIdfromPatients(entity.get());
		practitionerRepository.removeAllOrganizationsWithIdfromPractitioners(entity.get());
		
		entity.get().setActive(false);
		organizationRepository.save(entity.get());
	}

	
}
