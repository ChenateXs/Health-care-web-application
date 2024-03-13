package it.engineering.nc.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.engineering.nc.app.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
