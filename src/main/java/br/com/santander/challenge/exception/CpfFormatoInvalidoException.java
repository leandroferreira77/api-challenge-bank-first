package br.com.santander.challenge.exception;

public class CpfFormatoInvalidoException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public CpfFormatoInvalidoException(String cpf) {
		super(String.format("O CPF %s deve estar no formato 99999999999", cpf));
	}	

}
