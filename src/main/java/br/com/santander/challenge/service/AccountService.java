package br.com.santander.challenge.service;

import br.com.santander.challenge.dto.AccountDetailsDto;
import br.com.santander.challenge.model.Account;

import java.util.List;

public interface AccountService {

	public Account salvar(Account account);

	public AccountDetailsDto findByAgenciaAndConta(Integer conta);
	
	public void apagar (Integer numeroConta);
	
	List<Account> getAllAccounts();

}
