package br.com.santander.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.challenge.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
