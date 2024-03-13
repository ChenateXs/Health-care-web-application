package it.engineering.nc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.engineering.nc.app.entity.ExaminationEntity;

@Repository
public interface ExaminationRepository extends JpaRepository<ExaminationEntity, Long>,JpaSpecificationExecutor<ExaminationEntity>{

}
