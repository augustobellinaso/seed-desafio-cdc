package com.augustobellinaso.casadocodigo.finalizacompra;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NovoPedidoRequest {

    @NotNull
    @Positive
    private BigDecimal total;

    @Valid
    @Size(min = 1)
    private List<NovoPedidoItemRequest> itens = new ArrayList<>();

    public NovoPedidoRequest(@Valid @Size(min = 1) List<NovoPedidoItemRequest> itens,
                             @NotNull @Positive BigDecimal total) {
        this.itens = itens;
        this.total = total;
    }

    public List<NovoPedidoItemRequest> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "NovoPedidoRequest{" +
                "total=" + total +
                ", itens=" + itens +
                '}';
    }

    public Function<Compra, Pedido> toModel(EntityManager manager) {
        Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(manager))
                .collect(Collectors.toSet());

        return (compra) -> {
            Pedido pedido = new Pedido(compra, itensCalculados);
            Assert.isTrue(pedido.totalIgual(total), "O total enviado não corresponde ao total real");
            return pedido;
        };
    }
}
