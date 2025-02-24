package com.augustobellinaso.casadocodigo.detalhecompra;

import com.augustobellinaso.casadocodigo.finalizacompra.Compra;
import com.augustobellinaso.casadocodigo.paisestado.Estado;
import com.augustobellinaso.casadocodigo.paisestado.Pais;

public class DetalheCompraEnderecoResponse {

    private String endereco;
    private String complemento;
    private String cidade;
    private String cep;
    private String pais;
    private String estado;

    public DetalheCompraEnderecoResponse(Compra compra) {
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.cidade = compra.getCidade();
        this.cep = compra.getCep();
        this.pais = compra.getPais().getNome();
        if (compra.temEstado()) {
            this.estado = compra.getEstado().getNome();
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }
}
