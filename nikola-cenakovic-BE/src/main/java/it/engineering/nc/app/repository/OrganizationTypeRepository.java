package it.engineering.nc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.engineering.nc.app.entity.OrganizationTypeEntity;

@Repository
public interface OrganizationTypeRepository extends JpaRepository<OrganizationTypeEntity, Long>{

}
