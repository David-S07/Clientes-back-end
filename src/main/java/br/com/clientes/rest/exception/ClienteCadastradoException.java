package br.com.clientes.rest.exception;

public class ClienteCadastradoException extends RuntimeException {

    public ClienteCadastradoException (String cpf) {
        super("CPF " + cpf + " já cadastrado ! ");
    }
}
