package br.com.santander.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "nome", "agencia", "conta", "salario", "valorLimiteCartaoInicial" })
public class AccountDetailsDto {

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("agencia")
	private Integer agencia;

	@JsonProperty("conta")
	private Integer conta;

	@JsonProperty("salario")
	private BigDecimal salario;

	@JsonProperty("valorLimiteCartaoInicial")
	private BigDecimal valorLimiteCartaoInicial;

	@JsonProperty("nome")
	public String getNome() {
		return nome;
	}

	@JsonProperty("nome")
	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonProperty("agencia")
	public Integer getAgencia() {
		return agencia;
	}

	@JsonProperty("agencia")
	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	@JsonProperty("conta")
	public Integer getConta() {
		return conta;
	}

	@JsonProperty("conta")
	public void setConta(Integer conta) {
		this.conta = conta;
	}

	@JsonProperty("salario")
	public BigDecimal getSalario() {
		return salario;
	}

	@JsonProperty("salario")
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@JsonProperty("valorLimiteCartaoInicial")
	public BigDecimal getValorLimiteCartaoInicial() {
		return valorLimiteCartaoInicial;
	}

	@JsonProperty("valorLimiteCartaoInicial")
	public void setValorLimiteCartaoInicial(BigDecimal valorLimiteCartaoInicial) {
		this.valorLimiteCartaoInicial = valorLimiteCartaoInicial;
	}

}
