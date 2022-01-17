package br.com.santander.challenge.model;

import javax.persistence.*;

import java.io.Serializable;

import java.util.Random;

import java.math.BigDecimal;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = -9018650829796223850L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer agencia;

	@Column(nullable = false)
	private Integer conta;
	
	@Column(nullable = false)
	private BigDecimal valorLimiteCartaoInicial;

	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(unique = true)
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public BigDecimal getValorLimiteCartaoInicial() {
		return valorLimiteCartaoInicial;
	}

	public void setValorLimiteCartaoInicial(BigDecimal valorLimiteCartaoInicial) {
		this.valorLimiteCartaoInicial = valorLimiteCartaoInicial;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void gerarConta() {
		this.gerarAgencia();
		this.gerarNumeroConta();
	}

	private void gerarAgencia() {
		String numero = "";
		for (int i = 0; i < 4; i++) {
			numero += String.valueOf(numeroRandomico());
		}
		agencia = Integer.valueOf(numero);
	}

	private void gerarNumeroConta() {
		String numeroConta = "";
		for (int i = 0; i < 8; i++) {
			numeroConta += String.valueOf(numeroRandomico());
		}
		conta = Integer.valueOf(numeroConta);
	}

	private int numeroRandomico() {
		return new Random().nextInt(9 - 1) + 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agencia;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (agencia != other.agencia)
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
