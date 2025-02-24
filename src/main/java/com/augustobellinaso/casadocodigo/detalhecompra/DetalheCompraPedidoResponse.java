package com.augustobellinaso.casadocodigo.detalhecompra;

import com.augustobellinaso.casadocodigo.finalizacompra.ItemPedido;
import com.augustobellinaso.casadocodigo.finalizacompra.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalheCompraPedidoResponse {

    private List<DetalheCompraItemPedidoResponse> itens = new ArrayList<>();
    public DetalheCompraPedidoResponse(Pedido pedido) {
        this.itens = pedido.getItens().stream().map(DetalheCompraItemPedidoResponse::new)
                .toList();

    }

    public List<DetalheCompraItemPedidoResponse> getItens() {
        return itens;
    }
}
