package it.engineering.nc.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.entity.PatientEntity;
import it.engineering.nc.app.entity.PractitionerEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>,JpaSpecificationExecutor<PatientEntity> {
	
	@Modifying
	@Query("UPDATE PatientEntity p SET p.careProvider = null WHERE p.careProvider =:careProvider")
	void removeAllPractitionersWithIdfromPatients(PractitionerEntity careProvider);
	
	@Modifying
	@Query("UPDATE PatientEntity p SET p.organization = null WHERE p.organization =:organization")
	void removeAllOrganizationsWithIdfromPatients(OrganizationEntity organization);
	

	Optional<PatientEntity> findByIdentifier(String identifier);
}
