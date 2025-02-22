package com.augustobellinaso.casadocodigo.cadastrolivro;

import com.augustobellinaso.casadocodigo.cadastrocategoria.Categoria;
import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import com.augustobellinaso.casadocodigo.novoautor.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    @NotBlank
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroPaginas;

    @NotBlank
    private String isbn;

    @NotNull
    @Future
    private LocalDate dataDePublicacao;

    @NotNull
    @Valid
    @ManyToOne
    private Autor autor;

    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;

    @Deprecated(since = "1.0.0")
    public Livro() {}

    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
                 @NotBlank String sumario, @NotNull @Min(20) BigDecimal preco,
                 @NotNull @Min(100) Integer numeroPaginas,
                 @NotBlank String isbn, @Future @NotNull LocalDate dataDePublicacao,
                 @NotNull @Valid Autor autor, @NotNull @Valid Categoria categoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPaginas=" + numeroPaginas +
                ", isbn='" + isbn + '\'' +
                ", dataDePublicacao=" + dataDePublicacao +
                ", autor=" + autor +
                ", categoria=" + categoria +
                '}';
    }
}
