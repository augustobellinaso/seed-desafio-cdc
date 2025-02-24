package com.augustobellinaso.casadocodigo.detalhecompra;

import com.augustobellinaso.casadocodigo.compartilhado.Documento;
import com.augustobellinaso.casadocodigo.finalizacompra.Compra;
import com.augustobellinaso.casadocodigo.finalizacompra.CupomAplicado;
import com.augustobellinaso.casadocodigo.finalizacompra.ItemPedido;
import com.augustobellinaso.casadocodigo.paisestado.Estado;
import com.augustobellinaso.casadocodigo.paisestado.Pais;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DetalheCompraResponse {

    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String telefone;
    private DetalheCompraEnderecoResponse detalheEndereco;
    private DetalheCompraPedidoResponse detalhePedido;
    private BigDecimal valorOriginal;
    private boolean temCupom;
    private BigDecimal valorComDesconto;

    public DetalheCompraResponse(Compra compra) {
        this.email = compra.getEmail();
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.documento = compra.getDocumento();
        this.telefone = compra.getTelefone();
        this.detalheEndereco = new DetalheCompraEnderecoResponse(compra);
        this.detalhePedido = new DetalheCompraPedidoResponse(compra.getPedido());

        this.valorOriginal = compra.getPedido().getItens().stream().map(ItemPedido::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.temCupom = compra.temCupom();
        if (this.temCupom) {
            CupomAplicado cupomAplicado = compra.getCupomAplicado();
            this.valorComDesconto = valorOriginal.subtract(valorOriginal.multiply(cupomAplicado.getPercentualDescontoMomento().divide(new BigDecimal("100"), RoundingMode.UNNECESSARY)));
        }
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public DetalheCompraEnderecoResponse getDetalheEndereco() {
        return detalheEndereco;
    }

    public DetalheCompraPedidoResponse getDetalhePedido() {
        return detalhePedido;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public boolean isTemCupom() {
        return temCupom;
    }

    public BigDecimal getValorComDesconto() {
        return valorComDesconto;
    }
}
