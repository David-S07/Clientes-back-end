package br.com.clientes.rest;

import br.com.clientes.model.entity.Cliente;
import br.com.clientes.model.repository.ClienteRepository;
import br.com.clientes.rest.exception.ClienteCadastradoException;
import br.com.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    private final ClienteRepository repository;
    private final ClienteService service;


    @Autowired
    public ClienteController(ClienteRepository repository, ClienteService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Cliente> obterTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar (@RequestBody @Valid Cliente cliente) {
        try{
           return service.salvar(cliente);
        } catch (ClienteCadastradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND , "Cliente não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar (@PathVariable Integer id) {
         repository.findById(id).
                map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        repository.findById(id).
                map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setTipoDePessoa(clienteAtualizado.getTipoDePessoa());
                    cliente.setRg(clienteAtualizado.getRg());
                    cliente.setDdd(clienteAtualizado.getDdd());
                    cliente.setTelefone1(clienteAtualizado.getTelefone1());
                    cliente.setTelefone2(clienteAtualizado.getTelefone2());
                    cliente.setStatus(clienteAtualizado.getStatus());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    cliente.setCnpj(clienteAtualizado.getCnpj());
                    return repository.save(cliente);
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        }
}
