package br.com.santander.challenge.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.santander.challenge.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCpf(String cpf);
	
}
