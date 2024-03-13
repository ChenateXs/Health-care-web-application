package it.engineering.nc.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.entity.PractitionerEntity;

@Repository
public interface PractitionerRepository extends JpaRepository<PractitionerEntity, Long>,JpaSpecificationExecutor<PractitionerEntity>{

	@Modifying
	@Query("UPDATE PractitionerEntity p SET p.organization = null WHERE p.organization =:organization")
	void removeAllOrganizationsWithIdfromPractitioners(OrganizationEntity organization);
	
	Optional<PractitionerEntity> findByIdentifier(String identifier);
}
