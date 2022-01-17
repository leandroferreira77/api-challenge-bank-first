package br.com.santander.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.challenge.model.Customer;
import br.com.santander.challenge.service.impl.CustomerServiceImpl;
import br.com.santander.challenge.dto.CustomerAddressDto;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@PostMapping
	public ResponseEntity<Customer> criar(@RequestBody @Valid CustomerAddressDto request) {
		
		Customer customer = customerService.salvar(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> alterar(@Valid @RequestBody final CustomerAddressDto dto,  @Valid @PathVariable("id") final Long id) {
		
		Customer customer = customerService.alterar(dto, id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@DeleteMapping("/{id}/{numeroConta}")
	public ResponseEntity<Long> apagar(@Valid @PathVariable("id") final Long id, @PathVariable("numeroConta") final Integer numeroConta) {
		
		customerService.apagar(id, numeroConta);
		
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		
	    try {
	    	
	      List<Customer> customers = customerService.getAllCustomers();

	      if (customers.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(customers, HttpStatus.OK);
	      
	    } catch (Exception e) {
	    	
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	  }
}
