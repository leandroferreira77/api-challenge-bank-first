package br.com.santander.challenge.exception;

public class CustomerNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public CustomerNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CustomerNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de cliente com o código %d", id));
	}	

}
