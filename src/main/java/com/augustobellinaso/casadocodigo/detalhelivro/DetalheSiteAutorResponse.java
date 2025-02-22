package com.augustobellinaso.casadocodigo.detalhelivro;

import com.augustobellinaso.casadocodigo.novoautor.Autor;

public class DetalheSiteAutorResponse {

    private String nome;
    private String descricao;

    public DetalheSiteAutorResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
