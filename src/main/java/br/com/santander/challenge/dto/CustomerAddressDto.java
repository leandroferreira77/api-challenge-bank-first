package br.com.santander.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "nome", "idade", "cpf", "rg", "dataNasc", "sexo", "mae", "pai", "cep", "numero", "bairro",
		"cidade", "estado", "telefoneFixo", "celular", "salario", "address" })
public class CustomerAddressDto {

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("idade")
	private Integer idade;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("rg")
	private String rg;

	@JsonProperty("dataNasc")
	private LocalDate dataNasc;

	@JsonProperty("sexo")
	private String sexo;

	@JsonProperty("mae")
	private String mae;

	@JsonProperty("pai")
	private String pai;

	@JsonProperty("telefoneFixo")
	private String telefoneFixo;

	@JsonProperty("celular")
	private String celular;

	@JsonProperty("salario")
	private BigDecimal salario;

	@JsonProperty("address")
	AddressDto address;

	@JsonProperty("nome")
	public String getNome() {
		return nome;
	}

	@JsonProperty("nome")
	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonProperty("idade")
	public Integer getIdade() {
		return idade;
	}

	@JsonProperty("idade")
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@JsonProperty("cpf")
	public String getCpf() {
		return cpf;
	}

	@JsonProperty("cpf")
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@JsonProperty("rg")
	public String getRg() {
		return rg;
	}

	@JsonProperty("rg")
	public void setRg(String rg) {
		this.rg = rg;
	}

	@JsonProperty("dataNasc")
	public LocalDate getDataNasc() {
		return dataNasc;
	}

	@JsonProperty("dataNasc")
	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	@JsonProperty("sexo")
	public String getSexo() {
		return sexo;
	}

	@JsonProperty("sexo")
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@JsonProperty("mae")
	public String getMae() {
		return mae;
	}

	@JsonProperty("mae")
	public void setMae(String mae) {
		this.mae = mae;
	}

	@JsonProperty("pai")
	public String getPai() {
		return pai;
	}

	@JsonProperty("pai")
	public void setPai(String pai) {
		this.pai = pai;
	}

	@JsonProperty("telefoneFixo")
	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	@JsonProperty("telefoneFixo")
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	@JsonProperty("celular")
	public String getCelular() {
		return celular;
	}

	@JsonProperty("celular")
	public void setCelular(String celular) {
		this.celular = celular;
	}

	@JsonProperty("salario")
	public BigDecimal getSalario() {
		return salario;
	}

	@JsonProperty("salario")
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@JsonProperty("address")
	public AddressDto getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(AddressDto address) {
		this.address = address;
	}

}
