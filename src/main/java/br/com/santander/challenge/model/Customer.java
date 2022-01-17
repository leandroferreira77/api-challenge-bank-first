package br.com.santander.challenge.model;

import javax.persistence.*;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.Period;
import java.math.BigDecimal;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = -9018650829796223850L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Integer idade;

	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String rg;

	@Column(name = "data_nasc", nullable = false)
	private LocalDate dataNasc;

	@Column(nullable = false)
	private String sexo;

	@Column(nullable = false)
	private String mae;

	@Column(nullable = false)
	private String pai;

	@Column(nullable = true)
	private String telefone_fixo;

	@Column(nullable = false)
	private String celular;

	@Column(nullable = false)
	private BigDecimal salario;

	@OneToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(unique = true)
	private Address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isMaiorQueDezoitoAnos() {
		if (getDataNasc() == null)
			return false;
		int anos = Period.between(getDataNasc(), LocalDate.now()).getYears();
		return anos > 18;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mae == null) ? 0 : mae.hashCode());
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
		Customer other = (Customer) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mae == null) {
			if (other.mae != null)
				return false;
		} else if (!mae.equals(other.mae))
			return false;
		return true;
	}

}
