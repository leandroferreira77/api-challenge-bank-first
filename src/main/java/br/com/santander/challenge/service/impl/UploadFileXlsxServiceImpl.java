package br.com.santander.challenge.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.santander.challenge.helper.ExcelHelper;
import br.com.santander.challenge.model.Account;
import br.com.santander.challenge.model.Customer;
import br.com.santander.challenge.repository.CustomerRepository;
import br.com.santander.challenge.service.AccountService;

@Service
public class UploadFileXlsxServiceImpl {
	
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	private AccountService accountService;

	public void save(MultipartFile file) {
		
		try {
			
			List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
			
			List<Customer> listCustomersRetorno = repository.saveAll(customers);
			
			for (Customer customer : listCustomersRetorno) {
				
				Account account = new Account();
				account.setCustomer(customer);
				accountService.salvar(account);
			}
			
		} catch (IOException e) {
			throw new RuntimeException("erro dados no excel: " + e.getMessage());
		}
	}
}
