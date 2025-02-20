package com.augustobellinaso.casadocodigo.novoautor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotBlank @Email String email;
    private @NotBlank @Size(max = 400) String descricao;
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @Deprecated(since = "1.0.0")
    public Autor() {
        //Construtor padrao anotado como deprecated por causa do hibernate
    }

    //As anotaçoes aqui sao para fins de facilitar a visualizacao das validacoes, já uqe nao tem como serem validadas
    public Autor(@NotBlank String nome, @NotBlank @Email String email,
                 @NotBlank @Size(max = 400) String descricao) {
        Assert.hasLength(nome, "O nome é obrigatório");
        Assert.hasLength(email, "O email é obrigatório");
        Assert.hasLength(descricao, "A descricao é obrigatório");

        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }
}
