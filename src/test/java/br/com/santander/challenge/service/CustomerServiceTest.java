package br.com.santander.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import br.com.santander.challenge.exception.CpfFormatoInvalidoException;
import br.com.santander.challenge.exception.EntidadeExistenteException;
import br.com.santander.challenge.exception.NegocioException;
import br.com.santander.challenge.model.Customer;
import br.com.santander.challenge.service.impl.CustomerServiceImpl;
import br.com.santander.challenge.dto.CustomerAddressDto;
import br.com.santander.challenge.dto.AddressDto;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class CustomerServiceTest {

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
	public static final BigDecimal SALARIO = new BigDecimal("1550.00");
	public static final String CEP = "13280-172";
	public static final String ENDERECO = "RUA JOAO JOSE PESCARINI";
	public static final String NUMERO = "100";
	public static final String BAIRRO = "CENTRO";
	public static final String CIDADE = "VINHEDO";
	public static final String ESTADO = "SP";

	@Autowired
	private CustomerServiceImpl customerService;

	@Test
	public void deveCadastrarUmCustomer() {

		CustomerAddressDto customer = new CustomerAddressDto();
		customer.setNome(NOME_CUSTOMER);
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf(CPF_CUSTOMER);
		customer.setRg(RG_CUSTOMER);
		customer.setDataNasc(DATA_NASC_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefoneFixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		AddressDto address = new AddressDto();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);

		Customer customerRetorno = customerService.salvar(customer);

		assertThat(customerRetorno.getId()).isNotNull();
	}

	@Test
	public void deveFalharAoCadastrarUmCustomerComCpfExistente() {
		CustomerAddressDto customer = new CustomerAddressDto();
		customer.setNome(NOME_CUSTOMER);
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf("12345678909");
		customer.setRg(RG_CUSTOMER);
		customer.setDataNasc(DATA_NASC_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefoneFixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		AddressDto address = new AddressDto();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);

		customerService.salvar(customer);

		CustomerAddressDto repetido = new CustomerAddressDto();
		repetido.setNome(NOME_CUSTOMER);
		repetido.setIdade(IDADE_CUSTOMER_MAIOR_18);
		repetido.setCpf("12345678909");
		repetido.setRg(RG_CUSTOMER);
		repetido.setDataNasc(DATA_NASC_CUSTOMER);
		repetido.setSexo(SEXO_CUSTOMER);
		repetido.setMae(MAE_CUSTOMER);
		repetido.setPai(PAI_CUSTOMER);
		repetido.setTelefoneFixo(TEL_FIXO);
		repetido.setCelular(CELULAR);
		repetido.setSalario(SALARIO);
		address = new AddressDto();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		repetido.setAddress(address);

		assertThrows(EntidadeExistenteException.class, () -> {
			customerService.salvar(repetido);
		});
	}

	@Test
	public void deveFalharAoCadastrarUmCustomerComCpfInvalido() {
		CustomerAddressDto customer = new CustomerAddressDto();
		customer.setNome(NOME_CUSTOMER);
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf("12345678908");
		customer.setRg(RG_CUSTOMER);
		customer.setDataNasc(DATA_NASC_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefoneFixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		AddressDto address = new AddressDto();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);

		assertThrows(CpfFormatoInvalidoException.class, () -> {
			customerService.salvar(customer);
		});
	}

	@Test
	public void deveFalharAoCadastrarUmCustomerComCpfNoFormatoInvalido() {
		CustomerAddressDto customer = new CustomerAddressDto();
		customer.setNome(NOME_CUSTOMER);
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf("123456789008");
		customer.setRg(RG_CUSTOMER);
		customer.setDataNasc(DATA_NASC_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefoneFixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		AddressDto address = new AddressDto();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);

		assertThrows(CpfFormatoInvalidoException.class, () -> {
			customerService.salvar(customer);
		});
	}

	@Test
	public void deveFalharAoCadastrarUmCustomerSemNome() {
		CustomerAddressDto customer = new CustomerAddressDto();
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf(CPF_CUSTOMER);
		customer.setRg(RG_CUSTOMER);
		customer.setDataNasc(DATA_NASC_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefoneFixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		AddressDto address = new AddressDto();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);

		assertThrows(NegocioException.class, () -> {
			customerService.salvar(customer);
		});
	}

	@Test
	public void deveFalharAoCadastrarUmCustomerSemDataDeNascimento() {
		CustomerAddressDto customer = new CustomerAddressDto();
		customer.setNome(NOME_CUSTOMER);
		customer.setIdade(IDADE_CUSTOMER_MAIOR_18);
		customer.setCpf(CPF_CUSTOMER);
		customer.setRg(RG_CUSTOMER);
		customer.setSexo(SEXO_CUSTOMER);
		customer.setMae(MAE_CUSTOMER);
		customer.setPai(PAI_CUSTOMER);
		customer.setTelefoneFixo(TEL_FIXO);
		customer.setCelular(CELULAR);
		customer.setSalario(SALARIO);
		AddressDto address = new AddressDto();
		address.setCep(CEP);
		address.setEndereco(ENDERECO);
		address.setNumero(NUMERO);
		address.setBairro(BAIRRO);
		address.setCidade(CIDADE);
		address.setEstado(ESTADO);
		customer.setAddress(address);

		assertThrows(NegocioException.class, () -> {
			customerService.salvar(customer);
		});
	}

	@Test
	public void devePassarAoCalcularIdadeMaiorQue18Anos() {
		Customer customer = new Customer();
		customer.setDataNasc(LocalDate.of(1984, 03, 03));

		assertTrue(customer.isMaiorQueDezoitoAnos());
	}

	@Test
	public void deveFalharAoCalcularIdadeMenorQue18Anos() {
		Customer customer = new Customer();
		customer.setDataNasc(LocalDate.of(2015, 8, 29));

		assertFalse(customer.isMaiorQueDezoitoAnos());
	}

}
