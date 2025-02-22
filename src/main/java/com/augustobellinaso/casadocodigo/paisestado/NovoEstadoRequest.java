package com.augustobellinaso.casadocodigo.paisestado;

import com.augustobellinaso.casadocodigo.compartilhado.ExistsId;
import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Estado.class)
    private String nome;

    @NotNull
    @ExistsId(fieldName = "id", domainClass = Pais.class)
    private Long idPais;

    public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    @Override
    public String toString() {
        return "NovoEstadoRequest{" +
                "nome='" + nome + '\'' +
                ", idPais=" + idPais +
                '}';
    }

    public Estado toModel(EntityManager manager) {
        return new Estado(nome, manager.find(Pais.class, idPais));
    }
}
