package br.com.santander.challenge.service.impl;

import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.challenge.dto.AccountDetailsDto;
import br.com.santander.challenge.model.Account;
import br.com.santander.challenge.repository.AccountRepository;
import br.com.santander.challenge.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	public Account buscarOuFalhar(final Integer numeroConta) {
		return accountRepository.findByAgenciaAndConta(numeroConta);
	}
	
	@Override
	public Account salvar(Account account) {

		account.gerarConta();

		account.setValorLimiteCartaoInicial(criarValorLimiteCartaoInicial(account.getCustomer().getSalario()));

		return accountRepository.save(account);
	}

	private BigDecimal criarValorLimiteCartaoInicial(BigDecimal salario) {

		BigDecimal valorLimiteCartaoInicial = BigDecimal.ZERO;

		BigDecimal salarioInicialBase = new BigDecimal("1500.00");

		if (salario.doubleValue() <= salarioInicialBase.doubleValue()) {

			valorLimiteCartaoInicial = new BigDecimal("450.00");

		} else {

			valorLimiteCartaoInicial = new BigDecimal("500.00");
		}

		return valorLimiteCartaoInicial;
	}
	
	@Override
	public AccountDetailsDto findByAgenciaAndConta(Integer conta) {
		
		AccountDetailsDto dto = null;
		
		Account account = accountRepository.findByAgenciaAndConta(conta);
		
		if (!isNull(account)) {
			
			dto = new AccountDetailsDto();
			
			dto.setNome(account.getCustomer().getNome());
			dto.setConta(account.getConta());
			dto.setAgencia(account.getAgencia());
			dto.setSalario(account.getCustomer().getSalario());
			dto.setValorLimiteCartaoInicial(account.getValorLimiteCartaoInicial());
		}
		
		return dto;
	}

	@Override
	public void apagar(Integer numeroConta) {
		Account account = buscarOuFalhar(numeroConta);
		
		if (nonNull(account)) {
			accountRepository.delete(account);

		}
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}
}
