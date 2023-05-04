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
        Boolean exists = repository.existsByCpf(cliente.getCpf());
        if(exists && cliente.getCpf() != null) {
            throw new ClienteCadastradoException(cliente.getCpf());
        }
        Boolean exists2 = repository.existsBycnpj(cliente.getCnpj());
        if(exists2 && cliente.getCnpj() != null) {
            throw new ClienteCadastradoException(cliente.getCnpj());
        }
        return repository.save(cliente);
    }

}
