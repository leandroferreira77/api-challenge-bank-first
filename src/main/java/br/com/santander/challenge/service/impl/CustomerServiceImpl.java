package br.com.santander.challenge.service.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import br.com.santander.challenge.dto.CustomerAddressDto;
import br.com.santander.challenge.exception.CustomerNaoEncontradoException;
import br.com.santander.challenge.exception.CpfFormatoInvalidoException;
import br.com.santander.challenge.exception.EntidadeExistenteException;
import br.com.santander.challenge.exception.NegocioException;
import br.com.santander.challenge.model.Account;
import br.com.santander.challenge.model.Customer;
import br.com.santander.challenge.repository.CustomerRepository;
import br.com.santander.challenge.service.AccountService;
import br.com.santander.challenge.validator.CpfValidator;

@Service
public class CustomerServiceImpl {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private ModelMapper modelMapper;

	public Customer buscarOuFalhar(final Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNaoEncontradoException(id));
	}

	public Customer salvar(final CustomerAddressDto dto) {

		validarCustomer(dto, null);
		
		Customer customer = customerRepository.save(mapToEntity(dto));
		
		Account account = new Account();
		account.setCustomer(customer);
		accountService.salvar(account);
		
		return customer;
	}

	public List<Customer> getAllCustomers() {

		List<Customer> customers = customerRepository.findAll();

		return customers;
	}

	public void apagar(final Long id, final Integer numeroConta) {
		
		accountService.apagar(numeroConta);
		
		Customer customer = buscarOuFalhar(id);

		if (nonNull(customer)) {
			customerRepository.delete(customer);

		}

	}
	
	public Customer alterar(final CustomerAddressDto dto, final Long id) {
		
		Customer customer = new Customer();
		
		Optional<Customer> op = customerRepository.findById(id);
		
		if(op.isPresent()) {
			
			validarCustomer(dto, id);
			
			customer = op.get();
			customer = customerRepository.saveAndFlush(mapToEntity(dto));
			
		}

		return customer;
	}

	private Customer mapToEntity(final CustomerAddressDto dto) {

		Customer customer = modelMapper.map(dto, Customer.class);

		return customer;

	}

	@SuppressWarnings("static-access")
	private void validarCustomer(final CustomerAddressDto customer, final Long id) {

		if (isNull(customer.getNome()) || customer.getNome().isEmpty()) {
			throw new NegocioException("Campo obrigatorio Nome nao informado");
		}

		if (isNull(customer.getIdade()) || customer.getIdade() <= 0) {
			throw new NegocioException("Campo obrigatorio Idade nao informado ou menor ou igual a zero");
		}

		CpfValidator cpfCnpjUtils = new CpfValidator();

		if (isNull(customer.getCpf()) || customer.getCpf().isEmpty()) {
			throw new CpfFormatoInvalidoException("Campo obrigatorio CPF nao informado");
		}

		if (!isInteger(customer.getCpf())) {
			throw new CpfFormatoInvalidoException("Campo CPF aceita somente numeros");
		}

		if (!cpfCnpjUtils.isValid(customer.getCpf())) {
			throw new CpfFormatoInvalidoException(customer.getCpf());
		}
		
		if (isNull(id)) {
			
			Optional<Customer> optional = customerRepository.findByCpf(customer.getCpf());

			if (optional.isPresent()) {
				throw new EntidadeExistenteException(String.format("O cpf %s já está cadastrado", customer.getCpf()));
			}
		}

		if (isNull(customer.getRg()) || customer.getRg().isEmpty()) {
			throw new NegocioException("Campo obrigatorio RG nao informado");
		}

		if (isNull(customer.getDataNasc())) {
			throw new NegocioException("Campo obrigatorio Data Nascimento nao informado");
		}

		if (isNull(customer.getSexo()) || customer.getSexo().isEmpty()) {
			throw new NegocioException("Campo obrigatorio Sexo nao informado");
		}

		if (isNull(customer.getMae()) || customer.getMae().isEmpty()) {
			throw new NegocioException("Campo obrigatorio Mae nao informado");
		}

		if (isNull(customer.getPai()) || customer.getPai().isEmpty()) {
			throw new NegocioException("Campo obrigatorio Pai nao informado");
		}

		if (isNull(customer.getSalario()) || BigDecimal.ZERO.equals(customer.getSalario())) {
			throw new NegocioException("Campo obrigatorio Salario nao informado ou igual a Zero");
		}

	}

	private static boolean isInteger(final String customerNumber) {
		return customerNumber != null && customerNumber.matches("[0-9]*");
	}

}
