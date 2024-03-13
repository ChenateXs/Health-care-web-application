package it.engineering.nc.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.engineering.nc.app.entity.OrganizationEntity;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long>,JpaSpecificationExecutor<OrganizationEntity>{
	Optional<OrganizationEntity> findByIdentifier(String identifier);
}