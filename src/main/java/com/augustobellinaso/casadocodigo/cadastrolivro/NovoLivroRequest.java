package com.augustobellinaso.casadocodigo.cadastrolivro;

import com.augustobellinaso.casadocodigo.cadastrocategoria.Categoria;
import com.augustobellinaso.casadocodigo.compartilhado.ExistsId;
import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import com.augustobellinaso.casadocodigo.novoautor.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank
    @UniqueValue(fieldName = "titulo", domainClass = Livro.class)
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
    @UniqueValue(fieldName = "isbn", domainClass = Livro.class)
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDePublicacao;

    @NotNull
    @ExistsId(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoria;

    @NotNull
    @ExistsId(fieldName = "id", domainClass = Autor.class)
    private Long idAutor;

    public NovoLivroRequest(@NotNull Long idAutor, @NotNull Long idCategoria,
                            @NotNull @Future LocalDate dataDePublicacao,
                            @NotBlank String isbn, @NotNull @Min(100) Integer numeroPaginas,
                            @NotNull @Min(20) BigDecimal preco, @NotBlank String sumario,
                            @NotBlank @Size(max = 500) String resumo, @NotBlank String titulo) {
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
        this.dataDePublicacao = dataDePublicacao;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.preco = preco;
        this.sumario = sumario;
        this.resumo = resumo;
        this.titulo = titulo;
    }

    public Livro toModel(EntityManager entityManager) {
        @NotNull Autor autor = entityManager.find(Autor.class, idAutor);
        Assert.state(autor != null, "O autor para o qual voce deseja cadastra o livro não existe no banco de dados " + idAutor);

        @NotNull Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        Assert.state(categoria != null, "A categoria para a qual voce deseja cadastra o livro não existe no banco de dados " + idCategoria);

        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn,
                dataDePublicacao, autor, categoria);
    }
}
