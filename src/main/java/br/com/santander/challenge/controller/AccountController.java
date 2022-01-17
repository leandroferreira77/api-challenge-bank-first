package br.com.santander.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

import java.util.List;

import br.com.santander.challenge.dto.AccountDetailsDto;
import br.com.santander.challenge.model.Account;
import br.com.santander.challenge.service.AccountService;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/{numeroConta}")
	public ResponseEntity<AccountDetailsDto> buscar(@PathVariable final Integer numeroConta){
		AccountDetailsDto response = accountService.findByAgenciaAndConta(numeroConta);
		
		if (isNull(response)) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		
	    try {
	    	
	      List<Account> accounts = accountService.getAllAccounts();

	      if (accounts.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(accounts, HttpStatus.OK);
	      
	    } catch (Exception e) {
	    	
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	  }
	
}
