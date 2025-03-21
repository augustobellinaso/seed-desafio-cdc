package com.augustobellinaso.casadocodigo.novoautor;

import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NovoAutorRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @UniqueValue(fieldName = "email", domainClass = Autor.class)
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public NovoAutorRequest(@NotBlank String nome,
                            @NotBlank @Email String email,
                            @NotBlank @Size(max = 400) String descricao) {
        super();
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return this.email;
    }
}
