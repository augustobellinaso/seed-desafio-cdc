package com.augustobellinaso.casadocodigo.cadastrocategoria;

import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public NovaCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
