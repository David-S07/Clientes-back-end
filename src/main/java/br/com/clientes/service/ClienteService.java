package br.com.clientes.service;

import br.com.clientes.model.entity.Cliente;
import br.com.clientes.model.repository.ClienteRepository;
import br.com.clientes.rest.exception.ClienteCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        boolean exists = repository.existsByCpf(cliente.getCpf());
        if(exists) {
            throw new ClienteCadastradoException(cliente.getCpf());
        }
        return repository.save(cliente);
    }

}
