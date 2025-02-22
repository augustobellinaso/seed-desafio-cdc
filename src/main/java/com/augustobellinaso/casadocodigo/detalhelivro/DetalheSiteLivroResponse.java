package com.augustobellinaso.casadocodigo.detalhelivro;

import com.augustobellinaso.casadocodigo.cadastrolivro.Livro;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class DetalheSiteLivroResponse {

    private String titulo;
    private String isbn;
    private int numeroPaginas;
    private BigDecimal preco;
    private String resumo;
    private String sumario;
    private String dataDePublicacao;
    private DetalheSiteAutorResponse autor;

    public DetalheSiteLivroResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.isbn = livro.getIsbn();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.preco = livro.getPreco();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.dataDePublicacao = livro.getDataDePublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.autor = new DetalheSiteAutorResponse(livro.getAutor());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public String getDataDePublicacao() {
        return dataDePublicacao;
    }

    public DetalheSiteAutorResponse getAutor() {
        return autor;
    }
}
