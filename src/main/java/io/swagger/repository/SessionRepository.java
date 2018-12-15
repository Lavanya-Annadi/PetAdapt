package io.swagger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.model.Address;
import io.swagger.model.Session;
import io.swagger.model.User;
@Transactional
public interface SessionRepository extends CrudRepository<Session, Long>,JpaRepository<Session, Long> {
	
	Session findBySessionId(String session);

}
