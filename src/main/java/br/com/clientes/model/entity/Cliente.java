package br.com.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, length = 2)
    private String tipoDePessoa;
    @Column(nullable = false)
    private Boolean status = true;

    @Column(unique = true, nullable = false, length = 11)
    @CPF(message = "{campo.cpf.invalido}")
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    private String cpf;
    @Column(length = 14)
    @CNPJ(message = "{campo.cnpj.invalido}")
    private String cnpj;
    @Column(nullable = false, length = 9)
    private String rg;

    @NotEmpty(message = "{campo.ddd.obrigatorio}")
    @Column(nullable = false, length = 2)
    private String ddd;
    @Column(nullable = false, length = 9)
    private String telefone1;
    @Column(length = 9)
    private String telefone2;

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }
}
