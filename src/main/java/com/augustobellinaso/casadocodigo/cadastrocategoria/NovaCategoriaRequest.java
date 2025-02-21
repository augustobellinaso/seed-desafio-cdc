package com.augustobellinaso.casadocodigo.cadastrocategoria;

import jakarta.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    private String nome;

    public NovaCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
