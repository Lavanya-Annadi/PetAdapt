package io.swagger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.model.Provider;
import io.swagger.model.User;
@Transactional
public interface ProviderRepository extends CrudRepository<Provider, Long>,JpaRepository<Provider, Long> {

	Provider findByEmail(String email);

}
