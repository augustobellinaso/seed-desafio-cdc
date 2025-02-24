package com.augustobellinaso.casadocodigo.finalizacompra;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private @NotNull @Valid Compra compra;

    @ElementCollection
    private @Size(min = 1) Set<ItemPedido> itens = new HashSet<>();

    @Deprecated(since = "1.0.0")
    public Pedido(){}

    public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(), "Todo pedido deve ter pelo menos um item");
        this.compra = compra;
        this.itens.addAll(itens);
    }

    public boolean totalIgual(@NotNull @Positive BigDecimal total) {
        BigDecimal totalPedido = itens.stream().map(ItemPedido::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPedido.doubleValue() == total.doubleValue();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "compra=" + compra +
                ", itens=" + itens +
                '}';
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }
}
