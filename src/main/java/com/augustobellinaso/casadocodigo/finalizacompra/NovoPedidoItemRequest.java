package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrolivro.Livro;
import com.augustobellinaso.casadocodigo.compartilhado.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class NovoPedidoItemRequest {

    @NotNull
    @ExistsId(fieldName = "id", domainClass = Livro.class)
    private Long idLivro;

    @Positive
    private int quantidade;

    public NovoPedidoItemRequest(@NotNull Long idLivro, @Positive int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "NovoPedidoItemRequest{" +
                "idLivro=" + idLivro +
                ", quantidade=" + quantidade +
                '}';
    }

    public Long getIdLivro() {
        return this.idLivro;
    }

    public ItemPedido toModel(EntityManager manager) {
        @NotNull Livro livro = manager.find(Livro.class, idLivro);
        return new ItemPedido(livro, quantidade);

    }
}
