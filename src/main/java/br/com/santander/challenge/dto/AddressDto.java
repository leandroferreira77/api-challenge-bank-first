package br.com.santander.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cep", "endereco", "numero", "bairro", "cidade", "estado" })
public class AddressDto {

	@JsonProperty("cep")
	private String cep;
	
	@JsonProperty("endereco")
	private String endereco;

	@JsonProperty("numero")
	private String numero;

	@JsonProperty("bairro")
	private String bairro;

	@JsonProperty("cidade")
	private String cidade;

	@JsonProperty("estado")
	private String estado;

	@JsonProperty("cep")
	public String getCep() {
		return cep;
	}

	@JsonProperty("cep")
	public void setCep(String cep) {
		this.cep = cep;
	}

	@JsonProperty("endereco")
	public String getEndereco() {
		return endereco;
	}

	@JsonProperty("endereco")
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@JsonProperty("numero")
	public String getNumero() {
		return numero;
	}

	@JsonProperty("numero")
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@JsonProperty("bairro")
	public String getBairro() {
		return bairro;
	}

	@JsonProperty("bairro")
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@JsonProperty("cidade")
	public String getCidade() {
		return cidade;
	}

	@JsonProperty("cidade")
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@JsonProperty("estado")
	public String getEstado() {
		return estado;
	}

	@JsonProperty("estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
