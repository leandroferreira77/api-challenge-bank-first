package br.com.santander.challenge.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.santander.challenge.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("select c from Account c where conta = :conta")
	Account findByAgenciaAndConta(Integer conta);
}