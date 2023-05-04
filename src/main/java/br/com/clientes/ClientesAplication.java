package br.com.clientes;

import br.com.clientes.model.entity.Cliente;
import br.com.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesAplication {

    @Bean
    public CommandLineRunner run(@Autowired ClienteRepository repository){
        return args -> {
            Cliente cliente1 = Cliente.builder().cpf("54189819514").nome("Fulano").telefone1("999999999")
                    .ddd("11").rg("478579851").tipoDePessoa("PF").status(true).build();
            Cliente cliente2 = Cliente.builder().cpf("23638636151").nome("Ciclano Freitas").telefone1("8888888")
                    .ddd("11").rg("256574859").tipoDePessoa("PF").status(true).build();
            Cliente cliente3 = Cliente.builder().cpf("48799040905").nome("David Santana").telefone1("954773858")
                    .ddd("11").rg("685874859").tipoDePessoa("PF").status(true).build();
            repository.save(cliente1);
            repository.save(cliente2);
            repository.save(cliente3);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(ClientesAplication.class, args);
    }
}
