package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.compartilhado.Documento;
import com.augustobellinaso.casadocodigo.paisestado.Estado;
import com.augustobellinaso.casadocodigo.paisestado.Pais;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

public class Compra {

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

    @Deprecated(since = "1.0.0")
    public Compra() {}

    public Compra(@NotBlank @Email String email, @NotBlank String nome,
                  @NotBlank String sobrenome, @NotBlank @Documento String documento,
                  @NotBlank String endereco, @NotBlank String complemento,
                  @NotBlank String cidade, @NotNull Pais pais,
                  @NotBlank String telefone, @NotBlank String cep
                  ) {
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
    }

    @Override
    public String toString() {
        return "Compra{" +
                "email='" + email + '\'' +
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
                '}';
    }

    public void setEstado(@NotNull @Valid Estado estado) {
        Assert.notNull(pais, "Não é possível associar um estado se o país for nulo");
        Assert.isTrue(estado.pertenceAPais(pais), "O estado informado não pertence ao país associado a compra");
        this.estado = estado;
    }
}
