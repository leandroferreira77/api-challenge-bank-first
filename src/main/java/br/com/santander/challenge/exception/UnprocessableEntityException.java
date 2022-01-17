package br.com.santander.challenge.exception;

public class UnprocessableEntityException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UnprocessableEntityException(String mensagem) {
		super(mensagem);
	}
	
	public UnprocessableEntityException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
