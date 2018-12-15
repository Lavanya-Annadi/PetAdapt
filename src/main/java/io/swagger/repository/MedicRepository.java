package io.swagger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.model.Medic;
import io.swagger.model.User;
@Transactional(isolation=Isolation.SERIALIZABLE)
public interface MedicRepository extends CrudRepository<Medic, Long>,JpaRepository<Medic, Long> {

	Medic findByEmail(String email);

	
}
