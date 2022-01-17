package br.com.santander.challenge.exception;

public class EntidadeExistenteException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeExistenteException(String mensagem) {
		super(mensagem);
	}

	public EntidadeExistenteException(String cpf, String email) {
		this(String.format("Já existe um cadastro com este CPF %s!", cpf));
	}	

}
