package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrolivro.Livro;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemPedido {

    @ManyToOne
    private @NotNull Livro livro;
    private @Positive int quantidade;
    private @Positive BigDecimal precoMomento;

    @Deprecated(since = "1.0.0")
    public ItemPedido() {}

    public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemPedido that)) return false;
        return Objects.equals(livro, that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(livro);
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                ", precoMomento=" + precoMomento +
                '}';
    }

    public BigDecimal total() {
        return precoMomento.multiply(new BigDecimal(quantidade));
    }
}
