package com.augustobellinaso.casadocodigo.finalizacompra;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
}
