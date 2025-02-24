package com.augustobellinaso.casadocodigo.detalhecompra;

import com.augustobellinaso.casadocodigo.detalhelivro.DetalheSiteLivroResponse;
import com.augustobellinaso.casadocodigo.finalizacompra.ItemPedido;

import java.math.BigDecimal;

public class DetalheCompraItemPedidoResponse {

    private DetalheSiteLivroResponse detalheSiteLivroResponse;
    private int quantidade;
    private BigDecimal precoMomento;

    public DetalheCompraItemPedidoResponse(ItemPedido itemPedido) {
        this.detalheSiteLivroResponse = new DetalheSiteLivroResponse(itemPedido.getLivro());
        this.quantidade = itemPedido.getQuantidade();
        this.precoMomento = itemPedido.getPrecoMomento();
    }

    public DetalheSiteLivroResponse getDetalheSiteLivroResponse() {
        return detalheSiteLivroResponse;
    }

    public BigDecimal getPrecoMomento() {
        return precoMomento;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
