package com.augustobellinaso.casadocodigo.paisestado;

import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Pais.class)
    private String nome;

    public NovoPaisRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
