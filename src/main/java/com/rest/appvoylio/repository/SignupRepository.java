package com.rest.appvoylio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.appvoylio.entity.SignupEntity;

@Repository
public interface SignupRepository extends JpaRepository<SignupEntity, Integer>  {

	Optional<SignupEntity> findByUsername(String username);
	Boolean existsByUsername(String username);
	

}
