package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrocupom.Cupom;
import com.augustobellinaso.casadocodigo.compartilhado.Documento;
import com.augustobellinaso.casadocodigo.paisestado.Estado;
import com.augustobellinaso.casadocodigo.paisestado.Pais;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

import java.util.function.Function;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @Embedded
    private CupomAplicado cupomAplicado;

    @Deprecated(since = "1.0.0")
    public Compra() {}

    public Compra(@NotBlank @Email String email, @NotBlank String nome,
                  @NotBlank String sobrenome, @NotBlank @Documento String documento,
                  @NotBlank String endereco, @NotBlank String complemento,
                  @NotBlank String cidade, @NotNull Pais pais,
                  @NotBlank String telefone, @NotBlank String cep,
                  @NotNull Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.pais = pais;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", pais=" + pais +
                ", estado=" + estado +
                ", cupomAplicado=" + cupomAplicado +
                '}';
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

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public CupomAplicado getCupomAplicado() {
        return cupomAplicado;
    }

    public void setEstado(@NotNull @Valid Estado estado) {
        Assert.notNull(pais, "Não é possível associar um estado se o país for nulo");
        Assert.isTrue(estado.pertenceAPais(pais), "O estado informado não pertence ao país associado a compra");
        this.estado = estado;
    }

    public void aplicaCupom(Cupom cupom) {
        Assert.isTrue(cupom.valido(), "O cupom que está sendo aplicado não está mais válido");
        Assert.isNull(cupomAplicado, "Não é possível trocar um cupom de uma compra");
        this.cupomAplicado = new CupomAplicado(cupom);
    }

    public boolean temEstado() {
        return estado != null;
    }

    public boolean temCupom() {
        return cupomAplicado != null;
    }


}
