package br.com.santander.challenge.exception;

public class AccountNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public AccountNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public AccountNaoEncontradoException(Integer numeroConta) {
		this(String.format("Não existe uma conta com este numero %d", numeroConta));
	}	

}
