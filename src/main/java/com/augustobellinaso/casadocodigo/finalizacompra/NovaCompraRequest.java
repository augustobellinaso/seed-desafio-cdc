package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrocupom.Cupom;
import com.augustobellinaso.casadocodigo.compartilhado.Documento;
import com.augustobellinaso.casadocodigo.compartilhado.ExistsId;
import com.augustobellinaso.casadocodigo.paisestado.Estado;
import com.augustobellinaso.casadocodigo.paisestado.Pais;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Function;

public class NovaCompraRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Documento
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExistsId(fieldName = "id", domainClass = Pais.class)
    private Long idPais;

    @ExistsId(fieldName = "id", domainClass = Estado.class)
    private Long idEstado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @Valid
    private NovoPedidoRequest pedido;

    @ExistsId(fieldName = "codigo", domainClass = Cupom.class)
    private String codigoCupom;

    public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome,
                             @NotBlank String sobrenome, @NotBlank @Documento String documento,
                             @NotBlank String endereco, @NotBlank String complemento,
                             @NotBlank String cidade, @NotNull Long idPais,
                             @NotBlank String telefone, @NotBlank String cep,
                             @NotNull @Valid NovoPedidoRequest pedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = pedido;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public Optional<String> getCodigoCupom() {
        return Optional.ofNullable(codigoCupom);
    }

    @Override
    public String toString() {
        return "NovaCompraRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", idPais=" + idPais +
                ", idEstado=" + idEstado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", codigoCupom='" + codigoCupom + '\'' +
                '}';
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public boolean temEstado() {
        return idEstado != null;
    }

    public NovoPedidoRequest getPedido() {
        return pedido;
    }

    public Compra toModel(EntityManager manager, CupomRepository cupomRepository) {
        @NotNull Pais pais = manager.find(Pais.class, idPais);

        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        Compra compra = new Compra(email, nome, sobrenome,
                documento, endereco, complemento, cidade, pais,
                telefone, cep, funcaoCriacaoPedido);

        if (temEstado()) {
            compra.setEstado(manager.find(Estado.class, idEstado));
        }

        if (StringUtils.hasText(codigoCupom)) {
            Cupom cupom = cupomRepository.getByCodigo(codigoCupom);
            compra.aplicaCupom(cupom);
        }

        return compra;
    }
}
