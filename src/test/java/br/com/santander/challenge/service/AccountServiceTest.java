package br.com.santander.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import br.com.santander.challenge.dto.AccountDetailsDto;
import br.com.santander.challenge.model.Account;
import br.com.santander.challenge.model.Address;
import br.com.santander.challenge.model.Customer;
import br.com.santander.challenge.repository.CustomerRepository;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class AccountServiceTest {

	public static final String NOME_CUSTOMER = "Customer Test";
	public static final Integer IDADE_CUSTOMER_MAIOR_18 = 19;
	public static final Integer IDADE_CUSTOMER_MENOR_18 = 17;
	public static final String CPF_CUSTOMER = "17846533819";
	public static final String RG_CUSTOMER = "28.236.679";
	public static final LocalDate DATA_NASC_CUSTOMER = LocalDate.of(1984, 03, 03);
	public static final String SEXO_CUSTOMER = "Masculino";
	public static final String MAE_CUSTOMER = "MARLENE APARECIDA FERREIRA";
	public static final String PAI_CUSTOMER = "PEDRO JOSE FERREIRA";
	public static final String TEL_FIXO = "(11)3421-3900";
	public static final String CELULAR = "(11)96340-3886";
	public static final BigDecimal SALARIO = new BigDecimal("1500.00");
	public static final String CEP = "13280-172";
	public static final String ENDERECO = "RUA JOAO JOSE PESCARINI";
	public static final String NUMERO = "100";
	public static final String BAIRRO = "CENTRO";
	public static final String CIDADE = "VINHEDO";
	public static final String ESTADO = "SP";

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void deveCadastrarUmaConta() {

		Customer customer = new Customer();
		customer.setNome(NOME_CUSTOMER);
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf(CPF_CUSTOMER);
		customer.setRg(RG_CUSTOMER);
		customer.setDataNasc(DATA_NASC_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefone_fixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		Address address = new Address();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);
		
		Customer retorno = customerRepository.save(customer);

		Account account = new Account();
		account.setCustomer(retorno);
		
		accountService.salvar(account);

		assertThat(account.getId()).isNotNull();
	}
	
	@Test
	public void deveConsultaContaPeloNumero() {

		Customer customer = new Customer();
		customer.setNome(NOME_CUSTOMER);
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf(CPF_CUSTOMER);
		customer.setRg(RG_CUSTOMER);
		customer.setDataNasc(DATA_NASC_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefone_fixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		Address address = new Address();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);
		
		Customer retorno = customerRepository.save(customer);

		Account account = new Account();
		account.setCustomer(retorno);
		
		Account accountRetorno = accountService.salvar(account);

		assertThat(accountRetorno.getConta()).isNotNull();
		
		AccountDetailsDto dto = accountService.findByAgenciaAndConta(accountRetorno.getConta());
		
		assertEquals(accountRetorno.getConta(), dto.getConta());
	}
}
